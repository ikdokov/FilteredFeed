package com.example.ivandokov.filterednews.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ivandokov.filterednews.R;
import com.example.ivandokov.filterednews.rss.RssItem;
import com.example.ivandokov.filterednews.rss.RssReader;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView newsRecyclerView;
    private NewsRecyclerAdapter newsRecyclerAdapter;
    private RecyclerView.LayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        newsRecyclerView.setHasFixedSize(true);
        newsRecyclerView.setItemAnimator(new DefaultItemAnimator());

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        newsRecyclerView.setLayoutManager(linearLayoutManager);

        newsRecyclerAdapter = new NewsRecyclerAdapter(new ArrayList<RssItem>());
        newsRecyclerView.setAdapter(newsRecyclerAdapter);

        new RetrieveFeedTask().execute("http://www.dnevnik.bg/rss/?page=index");
    }

    public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {

        private ArrayList<RssItem> rssItems;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView title;
            public ViewHolder(View itemView) {
                super(itemView);
                this.title = (TextView) itemView.findViewById(R.id.info_text);
            }
        }

        public NewsRecyclerAdapter(ArrayList<RssItem> rssItems) {
            this.rssItems = rssItems;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_card_view, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            RssItem rssItem = rssItems.get(position);
            holder.title.setText(rssItem.getTitle());
        }

        @Override
        public int getItemCount() {
            return rssItems.size();
        }

        public void setRssItems(ArrayList<RssItem> rssItems) {
            this.rssItems = rssItems;
        }
    }

    class RetrieveFeedTask extends AsyncTask<String, Void, ArrayList<RssItem>> {

        private Exception exception;

        @Override
        protected ArrayList<RssItem> doInBackground(String... urls) {
            try {
                RssReader rssReader = new RssReader(urls[0]);
                return rssReader.getItems();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> rssItems) {
            newsRecyclerAdapter.setRssItems(rssItems);
            newsRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
