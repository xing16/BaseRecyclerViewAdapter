package com.xing.recyclerviewadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void single(View view) {
        startActivity(new Intent(this, SingleTypeActivity.class));
    }

    public void multiple(View view) {
        startActivity(new Intent(this, MultiTypeActivity.class));
    }

    public void multirecycler(View view) {
        startActivity(new Intent(this, MultiRecyclerActivity.class));
    }
}
