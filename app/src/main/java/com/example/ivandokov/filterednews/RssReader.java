package com.example.ivandokov.filterednews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public List<RssItem> getItems() throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();

        RssHandler handler = new RssHandler();
        saxParser.parse(url, handler);
        return handler.getRssItemList();
    }
}
