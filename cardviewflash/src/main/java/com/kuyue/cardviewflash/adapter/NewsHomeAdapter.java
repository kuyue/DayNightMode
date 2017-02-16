package com.kuyue.cardviewflash.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.kuyue.cardviewflash.NewsHomePagerViewHolder;
import com.kuyue.cardviewflash.entity.NewsHomePager;
import com.kuyue.cardviewflash.util.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sen young on 2017/2/16 18:13.
 * 邮箱:595327086@qq.com.
 */

public class NewsHomeAdapter extends BaseListAdapter {

    public static final int a = 0;
    public static final int b = 1;
    public static final int i = 2;
    public static final int j = 3;
    public static final int k = 4;
    public static final String l = "big";
    public static final String m = "small";
    public static final String n = "column";
    public String o;
    public int p;
    public Map<Integer, String> q = new HashMap();
    private View.OnClickListener r;
//    private List<NewsHome> s;
    private List<NewsHomePager> t;
//    private List<NewsUpdate> u;

    public NewsHomeAdapter(Context context, View.OnClickListener onClickListener, boolean z) {
        super(context, z);
        this.r = onClickListener;
//        this.s = new ArrayList();
        this.t = new ArrayList();
//        this.u = new ArrayList();
    }

//    public void setNewsHomeList(List<NewsHome> list) {
//        if (!g.isEmpty((List) list)) {
//            for (int i = 0; i < list.size(); i++) {
//                if (TextUtils.isEmpty(((NewsHome) list.get(i)).type)) {
//                    list.remove(i);
//                }
//            }
//            this.o = ((NewsHome) list.get(list.size() - 1)).id;
//            this.q.clear();
//            this.s.clear();
//            this.s.addAll(list);
//            for (NewsHome newsHome : this.s) {
//                newsHome.index_number = this.s.indexOf(newsHome);
//            }
//            notifyDataSetChanged();
//        }
//    }

//    public void addNewsHomeList(List<NewsHome> list) {
//        if (!g.isEmpty((List) list)) {
//            for (int i = 0; i < list.size(); i++) {
//                if (TextUtils.isEmpty(((NewsHome) list.get(i)).type)) {
//                    list.remove(i);
//                }
//            }
//            this.o = ((NewsHome) list.get(list.size() - 1)).id;
//            this.s.addAll(list);
//            for (NewsHome newsHome : this.s) {
//                newsHome.index_number = this.s.indexOf(newsHome);
//            }
//            notifyDataSetChanged();
//        }
//    }

    public void setNewsHomePagerList(List<NewsHomePager> list) {
        if (!CommonUtils.isEmpty((List) list)) {
            this.t.clear();
            this.t.addAll(list);
            notifyDataSetChanged();
        }
    }

//    public void setNewsUpdateList(List<NewsUpdate> list) {
//        if (!g.isEmpty((List) list)) {
//            this.u.clear();
//            this.u.addAll(list);
//            notifyItemChanged(1);
//        }
//    }

//    public void setFollowTags(String str) {
//        for (NewsHome newsHome : this.s) {
//            if (newsHome.id.equals(str)) {
//                if (newsHome.is_favorite) {
//                    newsHome.is_favorite = false;
//                } else {
//                    newsHome.is_favorite = true;
//                }
//                notifyDataSetChanged();
//            }
//        }
//        notifyDataSetChanged();
//    }

//    public void setSize(List<NewsHome> list) {
//        this.p = list == null ? 0 : list.size();
//    }

    protected int a() {
        // TODO: 2017/2/16
//        return (this.s == null || this.s.size() <= 0) ? 2 : this.s.size() + 3;
        return 2;
    }

    protected int a(int i) {
//        if (this.s.size() < 0 || i >= this.s.size() + 2) {
//            return -5;
//        }
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 4;
        }
//        if (l.equals(((NewsHome) this.s.get(i - 2)).type)) {
//            return 1;
//        }
//        if (m.equals(((NewsHome) this.s.get(i - 2)).type)) {
//            return 2;
//        }
        return 3;
    }

    protected BaseViewHolder a(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new NewsHomePagerViewHolder(this.c, viewGroup, this.r);
//            case 1:
//                return new NewsHomeLargeArticleViewHolder(this.c, viewGroup, this.r);
//            case 2:
//                return new NewsHomeSmallArticleViewHolder(this.c, viewGroup, this.r);
//            case 3:
//                return new NewsHomeLabelViewHolder(this.c, viewGroup, this.r, this);
//            case 4:
//                return new NewsHomeFlashViewHolder(this.c, viewGroup, this.r);
            default:
                return null;
        }
    }

    protected void a(BaseViewHolder baseViewHolder, int i) {
        switch (getItemViewType(i)) {
            case 0:
                baseViewHolder.bind(this.t);
                return;
//            case 1:
//                baseViewHolder.bind(this.s.get(i - 2));
//                return;
//            case 2:
//                baseViewHolder.bind(this.s.get(i - 2));
//                if ((baseViewHolder instanceof NewsHomeSmallArticleViewHolder) && i - 1 < this.s.size()) {
//                    boolean z = l.equals(((NewsHome) this.s.get(i + -1)).type) || n.equals(((NewsHome) this.s.get(i - 1)).type);
//                    ((NewsHomeSmallArticleViewHolder) baseViewHolder).setDividerHide(z);
//                    return;
//                }
//                return;
//            case 3:
//                ((NewsHome) this.s.get(i - 2)).position = i;
//                baseViewHolder.bind(this.s.get(i - 2));
//                return;
//            case 4:
//                baseViewHolder.bind(this.u);
//                return;
            default:
                return;
        }
    }

    public void onViewAttachedToWindow(BaseViewHolder baseViewHolder) {
        super.onViewAttachedToWindow(baseViewHolder);
        if (baseViewHolder instanceof NewsHomePagerViewHolder) {
            ((NewsHomePagerViewHolder) baseViewHolder).startAutoScroll();
        }
    }

    public void onViewDetachedFromWindow(BaseViewHolder baseViewHolder) {
        super.onViewDetachedFromWindow(baseViewHolder);
        if (baseViewHolder instanceof NewsHomePagerViewHolder) {
            ((NewsHomePagerViewHolder) baseViewHolder).stopAutoScroll();
        }
    }


}
