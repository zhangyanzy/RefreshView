package com.zhangyan.refreshview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

    private List<String> mDatas;
    private onItemClickListener mItemClickListener;

    public MainRecycleViewAdapter(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_view, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mDatas.get(position));
        holder.mItemImage.setImageResource(R.mipmap.ic_launcher);
        //itemView整个Item的View
        if (mItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addItem(int position) {
        mDatas.add(position, "AddItem" + position);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public interface onItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(onItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mItemImage;
        private TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView != null) {
                mItemImage = (ImageView) itemView.findViewById(R.id.item_image);
                mTitle = (TextView) itemView.findViewById(R.id.item_title);
            }
        }
    }
}
