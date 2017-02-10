package com.example.ivandokov.filterednews;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RetrieveFeedTask().execute("http://www.dnevnik.bg/rss/?page=index");

    }


    class RetrieveFeedTask extends AsyncTask<String, Void, List<RssItem>> {

        private Exception exception;

        @Override
        protected List<RssItem> doInBackground(String... urls) {
            try {
                RssReader rssReader = new RssReader(urls[0]);
                return rssReader.getItems();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<RssItem> rssItems) {
            // TODO: 2/10/17  
        }
    }
}
