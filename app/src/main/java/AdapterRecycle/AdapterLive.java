package AdapterRecycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th5.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import object.ItemLive;

public class AdapterLive extends RecyclerView.Adapter<AdapterLive.ViewHolder> {
    private Context context;
    private ArrayList<ItemLive> itemLivelist;

    public AdapterLive(ArrayList<ItemLive> liveList,Context context){
        this.context = context;
        this.itemLivelist = liveList;
    }

    @NonNull
    @Override
    public AdapterLive.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterLive.ViewHolder holder, int position) {
        ItemLive itemLive = itemLivelist.get(position);
        holder.txt.setText(itemLive.getTeam1());
        holder.txt2.setText(itemLive.getTime());
        holder.txt3.setText(itemLive.getTeam2());
        Picasso.get().load(itemLive.getImg1()).into(holder.imageView);
        Picasso.get().load(itemLive.getImg2()).into(holder.imageView1);
    }

    @Override
    public int getItemCount() { return itemLivelist.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView,imageView1;
        private TextView txt,txt2,txt3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView1 = itemView.findViewById(R.id.imageView1);
            txt = itemView.findViewById(R.id.txt_live);
            txt2 = itemView.findViewById(R.id.txt_live2);
            txt3 = itemView.findViewById(R.id.txt_live3);
        }
    }
}
