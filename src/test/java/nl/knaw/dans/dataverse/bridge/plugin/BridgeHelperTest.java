package nl.knaw.dans.dataverse.bridge.plugin;

import nl.knaw.dans.dataverse.bridge.plugin.util.BridgeHelper;
import org.apache.abdera.Abdera;
import org.apache.abdera.model.*;
import org.apache.http.HttpEntity;
import org.apache.http.entity.BasicHttpEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Unit test for BridgeHelperTest.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({BridgeHelper.class})
public class BridgeHelperTest{
    private static String bodyPart;

    @Before
    public void before(){
        mockStatic(BridgeHelper.class);
        bodyPart = "<feed xmlns=\"http://www.w3.org/2005/Atom\"><id>https://act.easy.dans.knaw.nl/sword2/statement" +
                "/f649d944-fdc3-473a-9b9d-08e832390b52</id><title type=\"text\">Deposit f649d944-fdc3-473a-9b9d-08e832390b52</title>" +
                "<author><name>DANS-EASY</name></author><link href=\"https://act.easy.dans.knaw.nl/sword2/statement/f649d944-fdc3-473a-9b9d-08e832390b52\"/>" +
                "<category term=\"DRAFT\" scheme=\"http://purl.org/net/sword/terms/state\" label=\"State\">Deposit is open for additional data</category>" +
                "<entry><id>urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52</id><title type=\"text\">Resource urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52</title>" +
                "<summary type=\"text\">Resource Part</summary><content type=\"multipart/related\" src=\"urn:uuid:f649d944-fdc3-473a-9b9d-08e832390b52\"></content></entry></feed>";

    }

    @Test
    public void parseTest(){
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
        he.setContent(new ByteArrayInputStream(bodyPart.getBytes()));
        when(BridgeHelper.readEntityAsString(Mockito.anyObject())).thenCallRealMethod();
        String s = BridgeHelper.readEntityAsString(he);
        assertEquals(bodyPart, s);

    }

    @Test
    public void readChunkTest() throws IOException {
        byte[] input = bodyPart.getBytes(StandardCharsets.UTF_8);
        when(BridgeHelper.readChunk(Mockito.anyObject(), Mockito.anyInt())).thenCallRealMethod();
        byte[] bytes = BridgeHelper.readChunk(new ByteArrayInputStream(input), bodyPart.length());

        assertArrayEquals(input, bytes);
    }
}
