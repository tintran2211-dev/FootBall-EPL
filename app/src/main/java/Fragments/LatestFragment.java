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

import com.example.th5.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import AdapterRecycle.Adapter;
import object.User;

public class LatestFragment extends Fragment {
                RecyclerView recyclerView;
                Adapter adapter;
                TextView textView;
                private ArrayList<User> users = new ArrayList<>();
                private ProgressBar progressBar;

                @Nullable
                @Override
                public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                    View view =inflater.inflate(R.layout.fragment_latest,container,false);
                    //Recycleview
                    recyclerView = view.findViewById(R.id.recycleView);
                    progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
                    textView = view.findViewById(R.id.txt_frg_lt);
                    SetRecycleView();
                    return view;
                }

                private void SetRecycleView() {
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new Adapter(users,getContext());
                    recyclerView.setAdapter(adapter);
                    Content content = new Content();
                    content.execute();
                }
                private class Content extends AsyncTask<Void, Void, Void> {
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
                    adapter.notifyDataSetChanged();
                }
                @Override
                protected void onCancelled() {
                    super.onCancelled();
                }
                @Override
                protected Void doInBackground(Void... voids) {
                    try {
                        String url = "https://www.premierleague.com/";
                        Log.i(".LatestFragment", url +"");
                        Document doc = Jsoup.connect(url).get();

                        Elements elements = doc.select("a.thumbnail");
                        Log.i(".LatestFragment", elements +"");
                        int size = elements.size();
                        for (int i = 0; i < size; i++) {
                            String imgUrl = elements.select("a.thumbnail")
                                    .select("img")
                                    .eq(i)
                                    .attr("src");
                            Log.i(".LatestFragment", imgUrl +"");
                            String title = elements.select("figcaption")
                                    .select("span.title")
                                    .eq(i)
                                    .text();
                            Log.i(".LatestFragment", title +"");
                            String urldetail = elements.select("li.article-thumb")
                                    .select("a.thumbnail")
                                    .eq(i)
                                    .attr("href");
                            Log.i(".LatestFragment", urldetail +"");
                            users.add(new User(imgUrl, title,urldetail));
                            Log.i("id", "img" + imgUrl + ". title: " + title);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
        }
    }
}
