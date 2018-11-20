/**
 * Copyright (C) 2018 DANS - Data Archiving and Networked Services (info@dans.knaw.nl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.knaw.dans.bridge.plugin.lib;

import net.sf.saxon.s9api.SaxonApiException;
import nl.knaw.dans.bridge.plugin.lib.util.BridgeHelper;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.*;
import org.apache.commons.io.FileUtils;
import org.apache.http.entity.BasicHttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Unit test for BridgeHelperTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BridgeHelper.class})
public class BridgeHelperTest {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static String bodyPart;
    private static final String actualDdmFileName = "src/test/resources/xml/hdl-101204-hkdsa-files-location-result.xml";

    @Before
    public void before() {
        mockStatic(BridgeHelper.class);
        bodyPart = "<feed xmlns=\"http://www.w3.org/2005/Atom\"><id>https://act.easy.dans.knaw.nl/sword2/statement"
                + "/f649d944-fdc3-473a-9b9d-08e832390b52</id><title type=\"text\">Deposit f649d944-fdc3-473a-9b9d-08e832390b52</title>"
                + "<author><name>DANS-EASY</name></author><link href=\"https://act.easy.dans.knaw.nl/sword2/statement/f649d944-fdc3-473a-9b9d-08e832390b52\"/>"
                + "<category term=\"DRAFT\" scheme=\"http://purl.org/net/sword/terms/state\" label=\"State\">Deposit is open for additional data</category>"
                + "<entry><id>urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52</id><title type=\"text\">Resource urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52</title>"
                + "<summary type=\"text\">Resource Part</summary><content type=\"multipart/related\" src=\"urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52\"></content></entry></feed>";

        File actualDdmXmlFile = new File(actualDdmFileName);
        if (actualDdmXmlFile.exists())
            actualDdmXmlFile.delete();
    }

    @Test
    public void parseTest() {
        Abdera abdera = new Abdera();
        Feed feed = abdera.newFeed();

        feed.setId("https://act.easy.dans.knaw.nl/sword2/statement/f649d944-fdc3-473a-9b9d-08e832390b52");
        feed.setTitle("Deposit f649d944-fdc3-473a-9b9d-08e832390b52");
        feed.addAuthor("DANS-EASY");
        feed.addLink("https://act.easy.dans.knaw.nl/sword2/statement/f649d944-fdc3-473a-9b9d-08e832390b52");
        Category category = feed.addCategory("");
        category.setTerm("DRAFT");
        category.setScheme("http://purl.org/net/sword/terms/state");
        category.setLabel("State");
        category.setText("Deposit is open for additional data");
        Entry entry = feed.addEntry();
        entry.setId("urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52");
        entry.setTitle("Resource urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52");
        entry.setSummary("Resource Part");
        entry.setContent("");
        Content c = entry.getContentElement();
        c.setSrc("urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52");
        c.setMimeType("multipart/related");

        when(BridgeHelper.parse(Mockito.anyString())).thenReturn(feed);
        Element el = BridgeHelper.parse(bodyPart);
        assertEquals(bodyPart, el.toString());
    }

    @Test
    public void readEntityAsStringTest() throws IOException {
        BasicHttpEntity he = new BasicHttpEntity();
        // Given
        he.setContent(new ByteArrayInputStream(bodyPart.getBytes()));
        when(BridgeHelper.readEntityAsString(Mockito.anyObject())).thenCallRealMethod();
        // When
        String s = BridgeHelper.readEntityAsString(he);
        // Then
        assertEquals(bodyPart, s);

    }

    @Test
    public void readChunkTest() throws IOException {
        byte[] input = bodyPart.getBytes(StandardCharsets.UTF_8);
        // Given
        when(BridgeHelper.readChunk(Mockito.anyObject(), Mockito.anyInt())).thenCallRealMethod();
        // When
        byte[] bytes = BridgeHelper.readChunk(new ByteArrayInputStream(input), bodyPart.length());
        // Then
        assertArrayEquals(input, bytes);
    }

    @Test
    public void transformJsonToXml() throws IOException, SaxonApiException {
        final File expectedDdmXmlFile = new File("src/test/resources/xml/hdl-101204-hkdsa-files-location.xml");
        // Given
        when(BridgeHelper.transformJsonToXml(Mockito.anyObject(), Mockito.anyObject())).thenReturn(
                FileUtils.readFileToString(expectedDdmXmlFile, StandardCharsets.UTF_8));
        // When
        String ddmXmlExpected = FileUtils.readFileToString(expectedDdmXmlFile, StandardCharsets.UTF_8);
        String ddmXmlTransformationResult = BridgeHelper.transformJsonToXml(Mockito.anyObject(), Mockito.anyObject());
        // Then
        assertEquals(ddmXmlExpected, ddmXmlTransformationResult);

        final File jsonSourceFile = new File("src/test/resources/json/hdl-101204-hkdsa.json");
        final File xsltPathFile = new File("src/test/resources/xsl/dataverseJson-to-files-location.xsl");
        final String initialXsltTemplate = "initialTemplate";
        final String paramJson = "dvnJson";
        // Given
        when(BridgeHelper.transformJsonToXml(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject(), Mockito.anyObject())).thenCallRealMethod();
        // When
        String ddmXmlTransformationResultRealCall = BridgeHelper.transformJsonToXml(jsonSourceFile.toURI().toURL(), xsltPathFile.toURI().toURL(),
                initialXsltTemplate, paramJson);
        // Then
        LOG.info("expected\n{}", ddmXmlExpected);
        LOG.info("actual\n{}", ddmXmlTransformationResultRealCall);
        File actualDdmXmlFile = new File(actualDdmFileName);

        FileUtils.writeStringToFile(actualDdmXmlFile, ddmXmlTransformationResultRealCall, StandardCharsets.UTF_8);

        assertTrue(FileUtils.contentEqualsIgnoreEOL(expectedDdmXmlFile, actualDdmXmlFile, StandardCharsets.UTF_8.name()));
    }
}
