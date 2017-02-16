package com.example.ivandokov.filterednews.rss;

/**
 * Created by ivandokov on 2/10/17.
 */

public class RssItem {

    private String title;
    private String description;
    private String link;
    private String imageUrl;

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    private String pubDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return String.format("title: %s, description: %s, link: %s, imageUrl: %s", title, description, link, imageUrl);
    }
}
