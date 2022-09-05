package AdapterRecycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th5.R;

import java.util.ArrayList;

import object.ItemPL;

public class AdapterPL extends RecyclerView.Adapter<AdapterPL.ViewHolder> {

                private Context context;
                private ArrayList<ItemPL> itemPLlist;

                public AdapterPL(ArrayList<ItemPL> listitem,Context context){
                    this.context = context;
                    this.itemPLlist = listitem;
                }

                @NonNull
                @Override
                public AdapterPL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pl,parent,false);
                    return new ViewHolder(view);
                }

                @Override
                public int getItemCount() { return itemPLlist.size(); }

                @Override
                public void onBindViewHolder(@NonNull AdapterPL.ViewHolder holder, int position) {
                    ItemPL itemPL = itemPLlist.get(position);
                    holder.textView1.setText(itemPL.getPos());
                    holder.textView2.setText(itemPL.getClub());
                    holder.textView3.setText(itemPL.getMatch());
                    holder.textView4.setText(itemPL.getGd());
                    holder.textView5.setText(itemPL.getPts());
                }

                public class ViewHolder extends RecyclerView.ViewHolder {
                    private TextView textView1;
                    private TextView textView2;
                    private TextView textView3;
                    private TextView textView4;
                    private TextView textView5;
                    public ViewHolder(@NonNull View itemView) {
                        super(itemView);
                        textView1 = itemView.findViewById(R.id.txt_pl1);
                        textView2 = itemView.findViewById(R.id.txt_pl2);
                        textView3 = itemView.findViewById(R.id.txt_pl3);
                        textView4 = itemView.findViewById(R.id.txt_pl4);
                        textView5 = itemView.findViewById(R.id.txt_pl5);
                    }
                }
}
