package com.example.th5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import object.User;

public class latest2 extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView,textView1;
    private String detailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest2);

        imageView = findViewById(R.id.imageViewlt2);
        textView = findViewById(R.id.txtlt2);
        textView1 = findViewById(R.id.detailurl);

        textView.setText(getIntent().getStringExtra("title"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(imageView);
        Content content = new Content();
        content.execute();
    }
    private class Content extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView1.setText(detailString);
        }
        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String baseurl = "https://www.premierleague.com/";
                String detaiUrl = getIntent().getStringExtra("detailurl");
                String url = baseurl + detaiUrl;

                Document doc = Jsoup.connect(url).get();
                Elements elements = doc.select("section.standardArticle  ");

                 detailString = elements.select("h4.subHeader")
                        .text();
                 Log.i("latest2",detailString +"");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}