package au.com.innovus.univods.helper;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 21/02/15.
 */
public class VideoXmlParser {
    private static final String TAG = "VideoXmlParser";

    public List parse(String xmlString) throws XmlPullParserException, IOException {

        ArrayList<VideoItem> videoItems = new ArrayList<>();

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(xmlString));
            parser.nextTag();

            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();

                if (name.compareToIgnoreCase("item") == 0) {
                    getVideoItem(parser);
                }


            }

        } finally {

        }
        return videoItems;
    }

    private VideoItem getVideoItem(XmlPullParser parser) throws IOException, XmlPullParserException {

        String title = "";
        String pubDate = "";
        String URL = "";


        parser.require(XmlPullParser.START_TAG, null, "item");

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();

            if (name.equals("title")) {
                title = readTitle(parser);
                Log.d(TAG, title);
            }
            if (name.equals("guid")) {
                URL = readURL(parser);
                Log.d(TAG, URL);
            }
            if (name.equals("pubDate")) {
                pubDate = readpubDate(parser);
                Log.d(TAG, pubDate);
            }
        }

        return null;
    }

    // Processes title tags in the feed.
    private String readTitle(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "title");
        String title = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "title");
        return title;
    }

    // Processes title tags in the feed.
    private String readURL(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "guid");
        String URL = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "guid");
        return URL;
    }

    // Processes title tags in the feed.
    private String readpubDate(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, "pubDate");
        String pubDate = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, "pubDate");
        return pubDate;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
