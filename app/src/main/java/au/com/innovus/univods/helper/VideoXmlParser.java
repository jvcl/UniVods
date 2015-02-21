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


        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(xmlString));
            parser.nextTag();
            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    parser.next();
                    continue;
                }
                String name = parser.getName();

                if (name.compareToIgnoreCase("item") == 0) {
                    Log.d(TAG, name);
                }
                parser.next();
            }

        } finally {

        }
        return new ArrayList();
    }
}
