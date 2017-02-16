package com.example.ivandokov.filterednews.rss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by ivandokov on 2/10/17.
 */

public class RssReader {

    private String url;

    public RssReader(String url) {
        this.url = url;
    }

    public ArrayList<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssHandler handler = new RssHandler();
        saxParser.parse(url, handler);
        return handler.getRssItemList();
    }
}
