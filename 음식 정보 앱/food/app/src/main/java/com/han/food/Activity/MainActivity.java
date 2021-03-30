package com.han.food.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.han.food.Adapter.TemplateAdapter;
import com.han.food.Interface.OneButtonDialogCallback;
import com.han.food.Interface.TwoButtonDialogCallback;
import com.han.food.R;
import com.han.food.Util.Global;
import com.han.food.Util.Logg;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements TemplateAdapter.AdapterCallback{

    Context mContext;


    @BindView(R.id.template_recycler)
    RecyclerView template_recycler;

    ArrayList<String> full_info = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterknife init
        mContext = this;
        ButterKnife.bind(this);

        jsonParsing(getJsonString());
    }



    //RecyclerView
    @Override
    public void DoSomeThing(int posionion){
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra("info",full_info.get(posionion));
        intent.putExtra("position",posionion);
        startActivity(intent);
    }



    private String getJsonString()
    {
        String json = "";

        try {
            InputStream is = getAssets().open("food.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        Log.e("HTJ","return: ");
        return json;
    }

    private void jsonParsing(String json)
    {
        Logg.e(Global.USER_HTJ,"movieObject: ");
        try{
            JSONObject jsonObject = new JSONObject(json);

            JSONArray foodArray = jsonObject.getJSONArray("food");

            ArrayList<String> list = new ArrayList<>();

            for(int i=0; i<foodArray.length(); i++)
            {
                String info = "";
                Logg.e(Global.USER_HTJ,"for start");
                JSONObject foddObject = foodArray.getJSONObject(i);
                Logg.e(Global.USER_HTJ,"movieObject.getString(): "+foddObject.getString("이름"));
                list.add(Integer.toString(i+1)+"위: "+foddObject.getString("이름")) ;

                info = "이름: "+foddObject.getString("이름")+"\n"+"종류: "+foddObject.getString("종류")+"\n"
                        +"나트륨: "+foddObject.getString("나트륨")+"\n"+"탄수화물: "+foddObject.getString("탄수화물")+"\n"
                        +"단백질: "+foddObject.getString("단백질")+"\n"+"지방: "+foddObject.getString("지방")+"\n"
                        +"칼로리: "+foddObject.getString("칼로리")+"\n"+"총내용량: "+foddObject.getString("총내용량")+"\n"
                        +"장점: "+foddObject.getString("장점")+"\n"+"단점: "+foddObject.getString("단점");
                full_info.add(info);
            }
            template_recycler.setLayoutManager(new LinearLayoutManager(this)) ;
            TemplateAdapter adapter = new TemplateAdapter(list,this) ;
            template_recycler.setAdapter(adapter) ;
            //RecylerView();
            Logg.e(Global.USER_HTJ,"end");
        }catch (JSONException e) {
            Log.e("HTJ","e: "+e);
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(this, "Back button pressed.", Toast.LENGTH_SHORT).show();
        //super.onBackPressed();
        FinishDialog();
    }

    @OnClick(R.id.tv_board) void goBoard(){
        startActivity(new Intent(mContext,WebPageActivity.class));
    }

    public void FinishDialog(){
        AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
                android.R.style.Theme_DeviceDefault_Light_Dialog);

        oDialog.setMessage("앱을 종료하시겠습니까?")
                .setPositiveButton("아니오", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("예", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setCancelable(false) // 백버튼으로 팝업창이 닫히지 않도록 한다.
                .show();
    }

}