package au.com.innovus.univods.helper;

import android.util.Log;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by jorge on 21/02/15.
 */
public class VideoXmlParser {
    private static final String TAG = "VideoXmlParser";

    public List parse(String xmlString) throws ParserConfigurationException, SAXException, IOException {

        ArrayList<VideoItem> videoItems = new ArrayList<>();
        String title = "";
        String pubDate = "";
        String URL = "";

        DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = builder.newDocumentBuilder();

        InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes(Charset.forName("UTF-8")));

        Document xmlDocument = documentBuilder.parse(inputStream);

        Element rootElement = xmlDocument.getDocumentElement();

        Log.d(TAG, rootElement.getTagName());

        NodeList items = rootElement.getElementsByTagName("item");

        Node currentItem = null;
        NodeList itemChildern = null;
        Node currentChild = null;
        for (int i = 0; i < items.getLength(); i++) {
            currentItem = items.item(i);
            itemChildern = currentItem.getChildNodes();
            for (int j = 0; j < itemChildern.getLength(); j++) {
                currentChild = itemChildern.item(j);

                if (currentChild.getNodeName().equals("title")){
                    title = currentChild.getTextContent();
                    Log.d(TAG, title);
                }
                if (currentChild.getNodeName().equals("pubDate")){
                    pubDate = currentChild.getTextContent();
                    Log.d(TAG, pubDate);
                }
                if (currentChild.getNodeName().equals("guid")){
                    URL = currentChild.getTextContent();
                    Log.d(TAG, URL);
                }

            }
            videoItems.add(new VideoItem(title,pubDate,URL));
        }
        return videoItems;
    }
}
