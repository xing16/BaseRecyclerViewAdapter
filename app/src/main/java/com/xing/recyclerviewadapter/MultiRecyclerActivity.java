package com.xing.recyclerviewadapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.xing.baserecycleradapter.BaseRecyclerAdapter;
import com.xing.baserecycleradapter.BaseViewHolder;
import com.xing.baserecycleradapter.MultiTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiRecyclerActivity extends BaseActivity {

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("test data  = " + i);
        }
        MultiTypeRecyclerAdapter adapter = new MultiTypeRecyclerAdapter<String>(list, R.layout.item_banner, R.layout.item_section_horizontal, R.layout.item_section_grid) {
            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                initHorizontalRecyclerView(baseViewHolder);
                initGridRecyclerView(baseViewHolder);
            }

            @Override
            protected int getItemType(int position) {
                if (position == 0) {
                    return 0;
                } else if (position == 1) {
                    return 1;
                } else if (position == 2) {
                    return 2;
                }
                return 2;
            }
        };
        recyclerView.setAdapter(adapter);

    }

    private void initGridRecyclerView(BaseViewHolder baseViewHolder) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("test - " + i);
        }
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<String>(list, R.layout.item_recycler_vertical) {
            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                baseViewHolder.setImageDrawable(R.id.iv_image, new ColorDrawable(getColor()));

            }
        };
        baseViewHolder.setRecycler(R.id.rv_grid, gridLayoutManager, adapter);
    }

    private void initHorizontalRecyclerView(BaseViewHolder baseViewHolder) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list.add("test - " + i);
        }
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<String>(list, R.layout.item_recycler_horizontal) {
            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                baseViewHolder.setImageDrawable(R.id.iv_image, new ColorDrawable(getColor()));

            }
        };
        baseViewHolder.setRecycler(R.id.rv_horizontal, linearLayoutManager, adapter);
    }
}
