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
                Logg.e(Global.USER_HTJ,"movieObject.getString(): "+foddObject.getString("??????"));
                list.add(Integer.toString(i+1)+"???: "+foddObject.getString("??????")) ;

                info = "??????: "+foddObject.getString("??????")+"\n"+"??????: "+foddObject.getString("??????")+"\n"
                        +"?????????: "+foddObject.getString("?????????")+"\n"+"????????????: "+foddObject.getString("????????????")+"\n"
                        +"?????????: "+foddObject.getString("?????????")+"\n"+"??????: "+foddObject.getString("??????")+"\n"
                        +"?????????: "+foddObject.getString("?????????")+"\n"+"????????????: "+foddObject.getString("????????????")+"\n"
                        +"??????: "+foddObject.getString("??????")+"\n"+"??????: "+foddObject.getString("??????");
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

        oDialog.setMessage("?????? ?????????????????????????")
                .setPositiveButton("?????????", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        //Toast.makeText(getApplicationContext(), "??????", Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("???", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                })
                .setCancelable(false) // ??????????????? ???????????? ????????? ????????? ??????.
                .show();
    }

}