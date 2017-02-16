package com.kuyue.daynightmode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sen young on 2017/2/16 10:36.
 * 邮箱:595327086@qq.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private final View.OnClickListener mSimpleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, AuthorActivity.class);
            context.startActivity(intent);
        }
    };

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rootView = inflater.inflate(R.layout.item_myadapter_layout, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(mSimpleClickListener);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
