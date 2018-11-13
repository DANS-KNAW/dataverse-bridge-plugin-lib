package nl.knaw.dans.bridge.plugin.lib.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.sf.saxon.s9api.*;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Element;
import org.apache.abdera.parser.Parser;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.text.DecimalFormat;

public class BridgeHelper {
    private static final String BAGIT_URI = "http://purl.org/net/sword/package/BagIt";
   // static final BagFactory bagFactory = new BagFactory();
    private static final Logger LOG = LoggerFactory.getLogger(BridgeHelper.class);

    public static void zipDirectory(File dir, File zipFile) throws ZipException {
        if (zipFile.exists()) zipFile.delete();
        ZipFile zf = new ZipFile(zipFile);
        ZipParameters parameters = new ZipParameters();
        zf.addFolder(dir, parameters);
    }


    public static CloseableHttpClient createHttpClient(URI uri, String uid, String pw, int timeout) {
        LOG.info("Create HttpClient for " + uri);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(uid, pw));
        return HttpClients.custom().setDefaultCredentialsProvider(credsProv).setDefaultRequestConfig(config).build();
    }

    public static CloseableHttpResponse post(URI uri, String mimeType, String input, CloseableHttpClient http, NameValuePair... nvps) throws IOException {
        HttpUriRequest request = RequestBuilder.create("POST").setUri(uri).setConfig(RequestConfig.custom()
                .setExpectContinueEnabled(true).build())
                .addParameters(nvps)
                .setEntity(new ByteArrayEntity(input.getBytes(), ContentType.create(mimeType))) //
                .build();
        return http.execute(request);
    }

    public static CloseableHttpResponse sendChunk(DigestInputStream dis, int size, String method, URI uri, String filename, String mimeType, CloseableHttpClient http, boolean inProgress) throws IOException {
        LOG.info("Send chunk file with filename: " + filename + " with size: " + size + " to " + uri);
        byte[] chunk = readChunk(dis, size);
        String md5 = new String(Hex.encodeHex(dis.getMessageDigest().digest()));
        HttpUriRequest request = RequestBuilder.create(method).setUri(uri).setConfig(RequestConfig.custom()
        /*
         * When using an HTTPS-connection EXPECT-CONTINUE must be enabled, otherwise buffer overflow may follow
         */
                .setExpectContinueEnabled(true).build()) //
                .addHeader("Content-Disposition", String.format("attachment; filename=%s", filename)) //
                .addHeader("Content-MD5", md5) //
                .addHeader("Packaging", BAGIT_URI) //
                .addHeader("In-Progress", Boolean.toString(inProgress)) //
                .setEntity(new ByteArrayEntity(chunk, ContentType.create(mimeType))) //
                .build();
        return http.execute(request);
    }

    public static byte[] readChunk(InputStream is, int size) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[size];
        int c = is.read(bytes);
        bos.write(bytes, 0, c);
        return bos.toByteArray();
    }

    public static String readEntityAsString(HttpEntity entity) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(entity.getContent(), bos);
        return new String(bos.toByteArray(), StandardCharsets.UTF_8);
    }

    public static <T extends Element> T parse(String text) {
        Abdera abdera = Abdera.getInstance();
        Parser parser = abdera.getParser();
        Document<T> receipt = parser.parse(new StringReader(text));
         return receipt.getRoot();
    }

    public static String formatFileSize(long size) {
        String hrSize;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }

    public static String transform(org.w3c.dom.Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }


    public static String transform(Templates cachedXsl, org.w3c.dom.Document doc) throws TransformerException {
        Transformer transformer = cachedXsl.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(writer));
        return writer.toString();
    }

    public static org.w3c.dom.Document buildDocumentFromString(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        LOG.debug("xmlString: {}", xmlString);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        return factory.newDocumentBuilder().parse(new InputSource(new StringReader(xmlString)));
    }

    /*
    Requirements:
    - XSLT 3.0
    - Saxon HE 9.8
     */
    public static String transformJsonToXml(URL jsonSourceUrl, URL xsltSourceUrl) throws SaxonApiException, IOException {
        LOG.debug("jsonMetadataSourceUrlxsl: {} \t jsonMetadataSourceUrlL {}", jsonSourceUrl, xsltSourceUrl);
        LOG.debug("initialXsltTemplate: {}, paramJson: {}", "initialTemplate", "dvnJson");
        return transformJsonToXml(jsonSourceUrl, xsltSourceUrl, "initialTemplate", "dvnJson");
    }

    /*
    Requirements:
    - XSLT 3.0
    - Saxon HE 9.8
     */
    public static String transformJsonToXml(URL jsonSourceUrl, URL xsltSourceUrl, String initialXsltTemplate, String paramJson) throws SaxonApiException, IOException {
        LOG.debug("xsltSourceUrl: {} \tdvnJsonMetadataSourceUrl: {}", xsltSourceUrl, jsonSourceUrl);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Processor processor = new Processor(false);
        Serializer serializer = processor.newSerializer(baos);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable executable = compiler.compile(new StreamSource(IOUtils.toInputStream(IOUtils.toString(xsltSourceUrl), StandardCharsets.UTF_8)));
        XsltTransformer transformer = executable.load();
        transformer.setInitialTemplate(new QName(initialXsltTemplate));
        transformer.setParameter(new QName(paramJson), new XdmAtomicValue(IOUtils.toString(jsonSourceUrl, StandardCharsets.UTF_8)));
        transformer.setDestination(serializer);
        transformer.transform();
        return baos.toString();
    }
}