package com.example.ivandokov.filterednews;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivandokov on 2/10/17.
 */

public class RssHandler extends DefaultHandler {
    public static final String ITEM_TAG = "item";
    public static final String TITLE_TAG = "title";
    public static final String LINK_TAG = "link";
    public static final String DESCRIPTION_TAG = "description";
    private List<RssItem> rssItemList;
    private RssItem currentItem;
    private boolean parsingTitle;
    private boolean parsingLink;
    private boolean parsingDescription;

    public RssHandler() {
        rssItemList = new ArrayList<RssItem>();
    }

    public List<RssItem> getRssItemList() {
        return rssItemList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ITEM_TAG)) {
            currentItem = new RssItem();
        } else if (qName.equals(TITLE_TAG)) {
            parsingTitle = true;
        } else if (qName.equals(LINK_TAG)) {
            parsingLink = true;
        } else if (qName.equals(DESCRIPTION_TAG)) {
            parsingDescription = true;
        } else if (qName.equals("media:thumbnail") || qName.equals("media:content") || qName.equals("image")) {
            if (attributes.getValue("url") != null) {
                currentItem.setImageUrl(attributes.getValue("url"));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(ITEM_TAG)) {
            rssItemList.add(currentItem);
            currentItem = null;
        } else if (qName.equals(TITLE_TAG)) {
            parsingTitle = false;
        } else if (qName.equals(LINK_TAG)) {
            parsingLink = false;
        } else if (qName.equals(DESCRIPTION_TAG)) {
            parsingDescription = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentItem != null) {
            if (parsingTitle) {
                currentItem.setTitle(new String(ch, start, length));
            } else if (parsingLink){
                currentItem.setLink(new String(ch, start, length));
            } else if (parsingDescription) {
                currentItem.setDescription(new String(ch, start, length));
            }
        }
    }
}
