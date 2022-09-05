package Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.th5.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import AdapterRecycle.AdapterLive;
import object.ItemLive;
import object.User;

public class LiveFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterLive adapterLive;
    private  ArrayList<ItemLive> itemLives = new ArrayList<>();
    private ProgressBar progressBar;
    TextView txt,txt1,txt2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, container, false);
        recyclerView = view.findViewById(R.id.recycleViewlive);
        progressBar = view.findViewById(R.id.progressBarlive);
        txt = view.findViewById(R.id.txt_live);
        txt1 = view.findViewById(R.id.txt_live2);
        txt2 = view.findViewById(R.id.txt_live3);
        setRecycleView();
        return view;
    }

    private void setRecycleView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterLive = new AdapterLive(itemLives, getContext());
        recyclerView.setAdapter(adapterLive);
        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            //	Thông báo toàn bộ dữ liệu thay đổi
            adapterLive.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                    String url = "https://www.premierleague.com/";
                    Document doc = Jsoup.connect(url).get();
                    Elements data = doc.select("a.matchAbridged");
                    int size = data.size();
                    Log.i(".test", data + "");
                    for(int i = 0 ; i < size; i++){
                        String txt1 = data.select("span.teamName:eq(0)")
                                .eq(i)
                                .text();
                        Log.i(".test", txt1 + "");
                        String txt2 = data.select("a.matchAbridged")
                                .select("span.teamName:eq(4)")
                                .eq(i)
                                .text();
                        Log.i(".test", txt2 + "");
                        String txt3 = data.select("time.renderKOContainer:eq(2)")
                                .eq(i)
                                .text();
                        Log.i(".test", txt3 + "");
                        String imgUrl1 = data.select("span.badge:eq(1)")
                                .select("img")
                                .eq(i)
                                .attr("src");
                        Log.i(".test", imgUrl1 + "");
                        String imgUrl2 = data.select("span.badge:eq(3)")
                                .select("img")
                                .eq(i)
                                .attr("src");
                        Log.i(".test", imgUrl2 + "");
                        itemLives.add(new ItemLive(txt1,txt2,imgUrl1,imgUrl2,"?-?"));
                        Log.i(".test" , "txt1" + txt1 + "txt2" + txt2);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
