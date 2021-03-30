package com.han.food.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.han.food.R;
import com.han.food.Util.Global;
import com.han.food.Util.Logg;
import java.util.ArrayList;

public class TemplateAdapter extends RecyclerView.Adapter<TemplateAdapter.ViewHolder> {

    //
    public interface AdapterCallback {
        void DoSomeThing(int position);
    }

    private ArrayList<String> mData = null ;
    private AdapterCallback mAdapterCallback;

    public TemplateAdapter(ArrayList<String> list,TemplateAdapter.AdapterCallback AdapterCallback) {
        mData = list ;
        mAdapterCallback = AdapterCallback;
    }


    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1 ;
        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView) ;
            textView1 = itemView.findViewById(R.id.tv_text) ;
            imageView = itemView.findViewById(R.id.iv_src);

            textView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAdapterCallback != null) {
                        Logg.e(Global.USER_HTJ,"mSelectedIndex: "+getLayoutPosition());
                        mAdapterCallback.DoSomeThing(getLayoutPosition());
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAdapterCallback != null) {
                        Logg.e(Global.USER_HTJ,"mSelectedIndex: "+getLayoutPosition());
                        mAdapterCallback.DoSomeThing(getLayoutPosition());
                    }
                }
            });

        }

    }

    @Override
    public TemplateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.template_adapter_item, parent, false) ;
        TemplateAdapter.ViewHolder vh = new TemplateAdapter.ViewHolder(view) ;
        return vh ;
    }

    @Override
    public void onBindViewHolder(TemplateAdapter.ViewHolder holder, int position) {
        String text = mData.get(position) ;
        holder.textView1.setText(text) ;
        if(position==0) holder.imageView.setImageResource(R.drawable.img_0);
        else if(position==1) holder.imageView.setImageResource(R.drawable.img_1);
        else if(position==2) holder.imageView.setImageResource(R.drawable.img_2);
        else if(position==3) holder.imageView.setImageResource(R.drawable.img_3);
        else if(position==4) holder.imageView.setImageResource(R.drawable.img_4);
        else if(position==5) holder.imageView.setImageResource(R.drawable.img_5);
        else if(position==6) holder.imageView.setImageResource(R.drawable.img_6);
        else if(position==7) holder.imageView.setImageResource(R.drawable.img_7);
        else if(position==8) holder.imageView.setImageResource(R.drawable.img_8);
        else if(position==9) holder.imageView.setImageResource(R.drawable.img_9);
        else if(position==10) holder.imageView.setImageResource(R.drawable.img_10);
        else if(position==11) holder.imageView.setImageResource(R.drawable.img_11);
    }

    @Override
    public int getItemCount() {
        return mData.size() ;
    }

}