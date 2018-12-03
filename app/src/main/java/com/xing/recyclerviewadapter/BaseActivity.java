package com.xing.recyclerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.Random;

public abstract class BaseActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        recyclerView = findViewById(R.id.recycler_view);
        init();
    }

    protected abstract void init();

    protected int getColor() {
        int red = new Random().nextInt(255) << 16;
        int green = new Random().nextInt(255) << 8;
        int blue = new Random().nextInt(255);
        int color = red | green | blue | 0xff000000;
        return color;
    }
}
