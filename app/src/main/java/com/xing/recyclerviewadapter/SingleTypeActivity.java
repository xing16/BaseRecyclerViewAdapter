package com.xing.recyclerviewadapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xing.baserecycleradapter.BaseRecyclerAdapter;
import com.xing.baserecycleradapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SingleTypeActivity extends BaseActivity {

    private List<String> list;
    private BaseRecyclerAdapter adapter;

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("测试数据 = " + i);
        }
        adapter = new BaseRecyclerAdapter<String>(list, R.layout.item_one_pic, R.layout.item_one_pic) {

            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                baseViewHolder.setText(R.id.tv_title, data)
                        .setImageDrawable(R.id.iv_image, new ColorDrawable(getColor()))
                        .setVisibility(R.id.tv_followup, position % 2 == 0)
                        .setChildViewsClickListener(R.id.tv_followup, R.id.tv_no_interest);  // 为 item 中的子view设置点击监听
            }
        };
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_headaer, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180);
        headerView.setLayoutParams(lp);
        // 添加 header
        adapter.addHeaderView(headerView);
        // item click
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(SingleTypeActivity.this, "position = " + position + "; " + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        // item longclick
        adapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(SingleTypeActivity.this, "position = " + position + "; " + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        // item 中子 view 点击回调
        adapter.setOnChildViewClickListener(new BaseRecyclerAdapter.OnChildViewClickListener() {
            @Override
            public void onChildViewClick(BaseRecyclerAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_followup:
                        Toast.makeText(SingleTypeActivity.this, "【关注】 " + "position = " + position + "; " + list.get(position), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_no_interest:
                        Toast.makeText(SingleTypeActivity.this, "【不感兴趣】 " + "position = " + position + "; " + list.get(position), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        // item 中子 view 长按回调
        adapter.setOnChildViewLongClickListener(new BaseRecyclerAdapter.OnChildViewLongClickListener() {
            @Override
            public boolean onChildViewLongClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(SingleTypeActivity.this, "child view long click" + list.get(position), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                list.remove(1);
                adapter.addItem(1, new String("add data"));
                break;
            case R.id.menu_del:
                if (list.size() > 0) {
                    adapter.removeItem(0);
                }
                break;
        }
        return true;
    }
}