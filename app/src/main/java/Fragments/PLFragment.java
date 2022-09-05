package Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import AdapterRecycle.AdapterPL;
import object.ItemPL;

public class PLFragment extends Fragment {
                    RecyclerView recyclerView;
                    AdapterPL adapterPL;

                    private ArrayList<ItemPL> itemPLs;
                    private String url = "https://www.premierleague.com/";

                    @Nullable
                    @Override
                    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                        View view = inflater.inflate(R.layout.fragment_pl, container, false);
                        //Tạo view để hiển thị Recycleview
                        recyclerView = view.findViewById(R.id.recycleView_pl);
                        SetRecycleview();
                        return view;
                    }

                    private void SetRecycleview() {
                        recyclerView.setHasFixedSize(true);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        itemPLs = new ArrayList<>();
                        adapterPL = new AdapterPL(itemPLs, getContext());
                        recyclerView.setAdapter(adapterPL);

                        //Su dung thu vien Volley de nhan du lieu tu server
                        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String html) {
                                String vitri = "";
                                String clb = "";
                                String sotran = "";
                                String hieuso = "";
                                String diem = "";
                //                Log.i("PLFragment",html.toString());

                                //Khai bao biến lưu trữ dữ liệu đổ về bằng Jsoup
                                Document doc = Jsoup.parse(html);
                                Log.i("==> PLFragment: ", doc + "");


                                //Kiem tra dieu kien cho cho biến lưu trữ
                                if (doc != null) {
                                    //Vòng lặp để lấy dữ liệu từ URL
                                    for (Element table : doc.select("tbody.standingEntriesContainer")) {
                                        for (Element row : table.select("tr")) {
                                            Elements elementtd = row.select("td:eq(0)");
                                            Log.i("==> PLFragment: ", elementtd + "");
                                            Elements elementclb = row.select("td:eq(1)");
                                            Log.i("==> PLFragment: ", elementclb + "");
                                            Elements elementst = row.select("td:eq(2)");
                                            Log.i("==> PLFragment: ", elementst + "");
                                            Elements elemenths = row.select("td:eq(3)");
                                            Log.i("==> PLFragment: ", elemenths + "");
                                            Elements elementdiem = row.select("td:eq(4)");
                                            Log.i("==> PLFragment: ", elementdiem + "");
                                            if (vitri != null) {
                                                vitri = elementtd.text();
                                            }
                                            if (clb != null) {
                                                clb = elementclb.text();
                                            }
                                            if (sotran != null) {
                                                sotran = elementst.text();
                                            }
                                            if (hieuso != null) {
                                                hieuso = elemenths.text();
                                            }
                                            if (diem != null) {
                                                diem = elementdiem.text();
                                            }
                                            //Đô dữ liệu lấy được về vào ArrayList với đối tượng ItemPL được tạo trước.
                                            itemPLs.add(new ItemPL(vitri, clb, sotran, hieuso, diem));
                                            Log.i("==> PLFragment: ", itemPLs + "");
                                            //Thông báo toàn bộ dữ liệu thay đổi
                                            adapterPL.notifyDataSetChanged();
                                        }
                                    }
                                }

                            }
                        }, error -> {
                        });
                        requestQueue.add(stringRequest);
                    }
}
