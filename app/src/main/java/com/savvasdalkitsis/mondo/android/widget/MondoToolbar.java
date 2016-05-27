/**
 * Copyright (C) 2016 Savvas Dalkitsis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.savvasdalkitsis.mondo.android.widget;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.savvasdalkitsis.mondo.R;

public class MondoToolbar extends Toolbar {

    private int maxTranslation;
    private View appLogoNoLetters;
    private View appLogo;
    private View balance;
    private View balanceTitle;
    private int balanceOffset;

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
        balanceOffset = pixels(R.dimen.toolbar_balance_offset);
        maxTranslation = toolbarHeight - expandedHeight;
        appLogoNoLetters = findViewById(R.id.view_app_logo_no_letters);
        balance = findViewById(R.id.view_balance);
        balanceTitle = findViewById(R.id.view_balance_title);
        appLogo = findViewById(R.id.view_app_logo);
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
            float translationY = maxTranslation;
            if (first < 2) {
                translationY = Math.max(recyclerView.getChildAt(1 - first).getTop() / 2f, maxTranslation);
            }

            float progress = translationY / maxTranslation;
            setTranslationY(translationY);
            appLogo.setTranslationY(-translationY);
            appLogoNoLetters.setTranslationY(-translationY);
            appLogo.setAlpha(1 - progress);
            balance.setTranslationX(balanceOffset * progress);
            balanceTitle.setTranslationX(balanceOffset * progress);
        }
    }
}
