package com.han.food.Activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.food.R;


public class DetailActivity extends AppCompatActivity {


    private Context mContext;
    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_back)
    TextView tv_back;
    @BindView(R.id.tv_info)
    TextView tv_info;
    String info;
    int posionion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext  = this;

        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        posionion = intent.getIntExtra("position",0);
        tv_info.setText(info);
        ImagSetting(posionion);
    }


    void ImagSetting(int position){
        if(position==0) iv_img.setImageResource(R.drawable.img_0);
        else if(position==1) iv_img.setImageResource(R.drawable.img_1);
        else if(position==2) iv_img.setImageResource(R.drawable.img_2);
        else if(position==3) iv_img.setImageResource(R.drawable.img_3);
        else if(position==4) iv_img.setImageResource(R.drawable.img_4);
        else if(position==5) iv_img.setImageResource(R.drawable.img_5);
        else if(position==6) iv_img.setImageResource(R.drawable.img_6);
        else if(position==7) iv_img.setImageResource(R.drawable.img_7);
        else if(position==8) iv_img.setImageResource(R.drawable.img_8);
        else if(position==9) iv_img.setImageResource(R.drawable.img_9);
        else if(position==10) iv_img.setImageResource(R.drawable.img_10);
        else if(position==11) iv_img.setImageResource(R.drawable.img_11);
    }



    @OnClick(R.id.tv_back) void Back(){
        finish();
    }

    @OnClick(R.id.tv_board) void goBoard(){
        startActivity(new Intent(mContext,WebPageActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}