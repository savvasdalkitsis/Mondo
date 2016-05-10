package com.savvasdalkitsis.mondo.android.widget;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.savvasdalkitsis.mondo.R;

public class MondoToolbar extends Toolbar {

    private int maxTranslation;

    public MondoToolbar(Context context) {
        super(context);
    }

    public MondoToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MondoToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int expandedHeight = pixels(R.dimen.toolbar_extended_height);
        int toolbarHeight = pixels(R.dimen.toolbar_collapsed_height);
        maxTranslation = toolbarHeight - expandedHeight;
    }

    public OnScrollListener scrollListener(LinearLayoutManager layoutManager) {
        return new ScrollListener(layoutManager);
    }

    private int pixels(@DimenRes int id) {
        return getResources().getDimensionPixelSize(id);
    }

    private class ScrollListener extends OnScrollListener {

        private LinearLayoutManager layoutManager;

        public ScrollListener(LinearLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int first = layoutManager.findFirstVisibleItemPosition();
            if (first > 0) {
                setTranslationY(maxTranslation);
            } else {
                setTranslationY(Math.max(recyclerView.getChildAt(0).getTop() / 2f, maxTranslation));
            }
        }
    }
}
