package com.teamtreehouse.asyncexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        try {
            URL url = new URL("https://placeimg.com/640/480/any");
            new MyAsyncTask().execute(url, url, url, url, url, url, url, url, url, url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class MyAsyncTask extends AsyncTask<URL, Integer, Bitmap[]> {

        @Override
        protected Bitmap[] doInBackground(URL... urls) {
            Bitmap[] bitmaps = new Bitmap[urls.length];
            for (int i = 0; i < urls.length; i++) {
                try {
                    bitmaps[i] = BitmapFactory.decodeStream(urls[i].openConnection().getInputStream());
                    int percentProgress = (int)((i / (urls.length - 1f)) * 100);
                    publishProgress(percentProgress);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmaps;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            progressBar.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(Bitmap[] bitmaps) {
            progressBar.setVisibility(View.GONE);
            myAdapter.bitmaps = bitmaps;
            myAdapter.notifyDataSetChanged();
        }
    }
}
