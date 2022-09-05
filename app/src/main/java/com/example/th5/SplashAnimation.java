package com.example.th5;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import static android.animation.ValueAnimator.INFINITE;

public class SplashAnimation extends AppCompatActivity {
    ImageView top,center,bottom,animation;
    TextView textView;
    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_animation);
        test();
        top = findViewById(R.id.imgtop);
        center = findViewById(R.id.img1);
        bottom = findViewById(R.id.imgbottom);
        animation = findViewById(R.id.img2);
        textView = findViewById(R.id.txt);

        //Load GIF

        Glide.with(this)
                .asGif()
                .load(R.drawable.source)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(animation);
    }
    public void test(){
        top = findViewById(R.id.imgtop);
        center = findViewById(R.id.img1);
        bottom = findViewById(R.id.imgbottom);
        animation = findViewById(R.id.img2);
        textView = findViewById(R.id.txt);
        //Set fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animation1 = AnimationUtils.loadAnimation(this
                ,R.anim.top_wave);
        //start Top animation
        top.setAnimation(animation1);

        //Khởi tạo hoạt cảnh đối tượng
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                center,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );
        //set duration
        objectAnimator.setDuration(500);
        //set repeat count
        objectAnimator.setRepeatCount(INFINITE);
        //SET REPEAT MODE
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //START ANIMATION
        objectAnimator.start();
        //Set animatedText
        animatedText("Premier League");


        Animation animation2  = AnimationUtils.loadAnimation(this
                ,R.anim.bottom_wave);
        //start Top animation
        bottom.setAnimation(animation2);
        // Initialize handle
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Chuyển hướng đến hoạt động chính
                startActivity(new Intent(SplashAnimation.this
                        ,Login.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        },4000);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Lúc Runnable chạy
            //Set text
            textView.setText(charSequence.subSequence(0,index++));
            //kiểm tra điều kiện
            if(index <= charSequence.length()){
                //when index is equal to text length
                //Run handle
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void animatedText(CharSequence cs){
        //Set Text
        charSequence = cs;
        //Xóa index
        index = 0 ;
        //Xóa Text
        textView.setText("");
        //Remove callback
        handler.removeCallbacks(runnable);
        //Chạy Handle
        handler.postDelayed(runnable,delay);
    }
}