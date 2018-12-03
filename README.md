### 效果图：





### 使用方法

#### 1. 实现 Adapter

##### （1） 单类型布局 ：继承 BaseRecyclerAdapter 类, 实现 bind() 方法，在其中实现子控件的数据绑定；

```java
BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<String>(list, R.layout.item_one_pic) {
    @Override
    protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
      baseViewHolder.setText(R.id.tv_title, data)
        .setImageDrawable(R.id.iv_image, new ColorDrawable(getColor()))
        .setVisibility(R.id.tv_followup, position % 2 == 0)
        .setChildViewsClickListener(R.id.tv_followup, R.id.tv_no_interest);  // 为 item 中的子view设置点击监听
    }
};
recyclerView.setAdapter(adapter);
```

##### （2） 多类型布局：继承 MultiTypeRecyclerAdapter  类, 实现  getItemType() 和 bind() 方法，其中 getItemType() 方法返回值取值必须小于多类型布局数组的长度 length ，否则出现数组越界异常；

```java
MultiTypeRecyclerAdapter adapter = new MultiTypeRecyclerAdapter<String>(list, R.layout.item_one_pic, R.layout.item_three_pic) {

            @Override
            protected int getItemType(int position) {
                return position % 2;
            }

            @Override
            protected void bind(BaseRecyclerAdapter<String> adapter, BaseViewHolder baseViewHolder, String data, int position) {
                /**
                 * 在 item_one_pic.xml 中是没有 iv_left,iv_center,iv_right 控件，没有出现空指针，是因为在 baseViewHolder.setImageDrawable
                 * 中进行了判断，这里就可以一直链式调用
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

```

#### 2. 事件回调

#####  （1）item click, longclick 回调

```java
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
```

#####  （2）item 中子 view 回调，需要在 bind() 方法中为指定 id 子控件设置监听  setChildViewsClickListener()

```java
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
```



#### 3. 添加 header,footer (支持多个)；

```java
View headerView = LayoutInflater.from(this).inflate(R.layout.item_headaer, null);
RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 180);
headerView.setLayoutParams(lp);
// 添加 header
adapter.addHeaderView(headerView);
// adapter.addFooterView(footerView);
```










