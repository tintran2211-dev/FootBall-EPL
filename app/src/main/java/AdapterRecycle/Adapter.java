package AdapterRecycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th5.R;
import com.example.th5.latest2;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import object.User;

//lớp dùng để gán / cập nhật dữ liệu vào các phần tử.
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
            //tao context de truy cap
            private Context context;//Tạo biến context dễ dàng truy cập
            //Tao danh user
            private ArrayList<User> userList;

            public Adapter(ArrayList<User> list,Context context) {
                this.context = context;
                this.userList = list;
            }

            //Tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                //tạo 1 biến View chuyển layout file xml thành view javacode
                // Tao layout cho View biểu diễn phần tử item
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item,parent,false);

                //return ra viewholder với biến view được tạo
                return new ViewHolder(view);
            }

            //Cho biết số phần tử của mảng dữ liệu
            @Override
            public int getItemCount() {
                    return userList.size();
            }

            //chuyển dữ liệu phần tử vào ViewHolder
            @Override
            public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                User user = userList.get(position);
                //set du lieu cho view qua phần tử User
                holder.textView.setText(user.getName());
                Picasso.get().load(user.getSourceID()).into(holder.imageView);
            }

            // Lớp nắm giữ cấu trúc view
            //khai báo viewholder hiển thị dữ liệu từ CardView.
            public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                private ImageView imageView;
                private TextView textView;
                 public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    imageView = itemView.findViewById(R.id.imageView);
                    textView = itemView.findViewById(R.id.txt);
                    itemView.setOnClickListener(this);
            }
            //xu ly su kien onclicl
                @Override
                public void onClick(View v) {
                     int position = getBindingAdapterPosition();
                     User user = userList.get(position);

                     Intent intent = new Intent(context, latest2.class);
                    intent.putExtra("title",user.getName());
                    intent.putExtra("image",user.getSourceID());
                    intent.putExtra("detailurl",user.getDetailUrl());
                    context.startActivity(intent);
                }
            }
}
