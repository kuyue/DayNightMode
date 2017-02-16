package com.kuyue.cardviewflash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by sen young on 2017/2/16 17:18.
 * 邮箱:595327086@qq.com.
 */

public abstract class BaseViewHolder<E> extends RecyclerView.ViewHolder {
    public View.OnClickListener d;
    public Context e;

    public abstract void bind(E e);

    public BaseViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public BaseViewHolder(Context context, View view) {
        super(view);
        this.e = context;
        ButterKnife.bind(this, view);
    }

    public BaseViewHolder(Context context, int i, ViewGroup viewGroup) {
        this(context, i, viewGroup, null);
    }

    public BaseViewHolder(Context context, int i, ViewGroup viewGroup, View.OnClickListener onClickListener) {
        this(context, i, viewGroup, onClickListener, true);
    }

    public BaseViewHolder(Context context, int i, ViewGroup viewGroup, View.OnClickListener onClickListener, boolean z) {
        super(LayoutInflater.from(context).inflate(i, viewGroup, false));
        this.e = context;
        ButterKnife.bind(this, this.itemView);
        if (onClickListener != null) {
            this.d = onClickListener;
            if (z && this.itemView != null) {
                this.itemView.setOnClickListener(onClickListener);
            }
        }
    }

    public final void bindByPayloads(E e, List<Object> list) {
        if (list.isEmpty()) {
            bind(e);
        } else {
            bindLocal(e, list);
        }
    }

    public void bindLocal(E e, List<Object> list) {
        bind(e);
    }

}
