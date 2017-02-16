package com.kuyue.cardviewflash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sen young on 2017/2/16 17:17.
 * 邮箱:595327086@qq.com.
 */

public abstract class BaseListAdapter  extends RecyclerView.Adapter<BaseViewHolder> {

    private View.OnClickListener a;
    protected Context c;
    public boolean d = false;
    protected boolean e = false;
    protected String f = null;
    public boolean g;
    protected boolean h = true;

    protected abstract int a();

    protected abstract int a(int i);

    protected abstract BaseViewHolder a(ViewGroup viewGroup, int i);

    protected abstract void a(BaseViewHolder baseViewHolder, int i);

    public BaseListAdapter(Context context, boolean z) {
        this.c = context;
        this.h = z;
    }

    public void setErrorRetryListener(View.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
//            case -5:
//                return new LoadMoreHolder(this.c, viewGroup);
//            case -4:
//                return new LoadingViewHolder(this.c, viewGroup);
//            case -3:
//                return new ErrorViewHolder(this.c, viewGroup);
//            case -2:
//                return new EmptyViewHolder(this.c, viewGroup);
            default:
                return a(viewGroup, i);
        }
    }

    public void onBindViewHolder(BaseViewHolder baseViewHolder, int i) {
//        if ((baseViewHolder instanceof EmptyViewHolder) || (baseViewHolder instanceof ErrorViewHolder) || (baseViewHolder instanceof LoadingViewHolder)) {
//            baseViewHolder.bind(this.f);
//            if (baseViewHolder instanceof ErrorViewHolder) {
//                ((ErrorViewHolder) baseViewHolder).setRetryListener(this.a);
//            }
//        } else if (!(baseViewHolder instanceof LoadMoreHolder)) {
//            a(baseViewHolder, i);
//        } else if (this.g) {
//            ((LoadMoreHolder) baseViewHolder).bind(LoadMoreHolder.a);
//        } else {
//            ((LoadMoreHolder) baseViewHolder).bind(null);
//        }
    }

    public int getItemViewType(int i) {
        if (this.d) {
            return -3;
        }
        if (this.e) {
            return -2;
        }
        if (this.h) {
            return -4;
        }
        return a(i);
    }

    public int getItemCount() {
        if (this.e || this.d || this.h) {
            return 1;
        }
        return a();
    }

    public void setError(boolean z, String str) {
        this.d = z;
        this.e = false;
        this.h = false;
        this.f = str;
        notifyDataSetChanged();
    }

    public void setEmpty(boolean z, String str) {
        this.e = z;
        this.d = false;
        this.h = false;
        this.f = str;
        notifyDataSetChanged();
    }

    public void reset() {
        this.e = false;
        this.d = false;
        this.h = false;
        notifyDataSetChanged();
    }

    public void setLoading() {
        this.e = false;
        this.d = false;
        this.h = true;
        notifyDataSetChanged();
    }


}
