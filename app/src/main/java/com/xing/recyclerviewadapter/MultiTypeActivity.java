package com.xing.recyclerviewadapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xing.baserecycleradapter.BaseRecyclerAdapter;
import com.xing.baserecycleradapter.BaseViewHolder;
import com.xing.baserecycleradapter.MultiTypeRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeActivity extends BaseActivity {

    private List<String> list;

    @Override
    protected void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("测试数据 = " + i);
        }
        MultiTypeRecyclerAdapter adapter = new MultiTypeRecyclerAdapter<String>(list, R.layout.item_one_pic, R.layout.item_three_pic) {

            @Override
            protected int getItemType(int position) {
                return position % 2;
            }

            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                /**
                 * 在 item_one_pic.xml 中是没有 iv_left,iv_center,iv_right 控件，没有出现空指针，是因为在 baseViewHolder.setImageDrawable
                 * 中进行了判断，避免在这里进行判断
                 */
                baseViewHolder.setText(R.id.tv_title, data)
                        .setImageDrawable(R.id.iv_image, new ColorDrawable(getColor()))
                        .setImageDrawable(R.id.iv_left, new ColorDrawable(getColor()))
                        .setImageDrawable(R.id.iv_center, new ColorDrawable(getColor()))
                        .setImageDrawable(R.id.iv_right, new ColorDrawable(getColor()))
                        .setVisibility(R.id.tv_followup, position % 2 == 0)
                        .setChildViewsClickListener(R.id.tv_followup, R.id.tv_no_interest, R.id.iv_left);
            }
        };
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_headaer, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180);
        headerView.setLayoutParams(lp);
        adapter.addHeaderView(headerView);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(MultiTypeActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(MultiTypeActivity.this, "item long click position = " + position, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnChildViewClickListener(new BaseRecyclerAdapter.OnChildViewClickListener() {
            @Override
            public void onChildViewClick(BaseRecyclerAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_followup:
                        Toast.makeText(MultiTypeActivity.this, "followup  position = " + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_no_interest:
                        Toast.makeText(MultiTypeActivity.this, "no interest  position = " + position, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_left:
                        Toast.makeText(MultiTypeActivity.this, "left image  position = " + position, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        adapter.setOnChildViewLongClickListener(new BaseRecyclerAdapter.OnChildViewLongClickListener() {
            @Override
            public boolean onChildViewLongClick(BaseRecyclerAdapter adapter, View view, int position) {
                Toast.makeText(MultiTypeActivity.this, "child view long click position = " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
