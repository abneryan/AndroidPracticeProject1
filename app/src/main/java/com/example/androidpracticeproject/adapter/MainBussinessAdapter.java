package com.example.androidpracticeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidpracticeproject.R;
import com.example.androidpracticeproject.callback.ItemClickListener;

/**
 * @Auther: yanguoqing
 * @Date: 2023/7/1 21:41
 * @Description:
 */
public class MainBussinessAdapter extends RecyclerView.Adapter<MainBussinessAdapter.MainViewHodler> {
    private final LayoutInflater inflater;
    private String[] mData;
    private Context mContext;
    private ItemClickListener mItemClickListener;

    public MainBussinessAdapter(Context context, String[] data, ItemClickListener itemClickListener) {
        mData = data;
        mContext = context;
        mItemClickListener = itemClickListener;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public MainViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainViewHodler(inflater.inflate(R.layout.main_bussiness_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHodler holder, int position) {
        holder.titleView.setText(mData[position]);
        holder.itemView.setOnClickListener((view)-> mItemClickListener.onItemClick(position));

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    class MainViewHodler extends RecyclerView.ViewHolder {
        public AppCompatTextView titleView;
        public MainViewHodler(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.tv_bs_titel);
        }
    }
}
