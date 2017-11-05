package com.example.emmalady.androidrikkeilesson9usejson.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.emmalady.androidrikkeilesson9usejson.R;

public class ItemDetailActivity extends AppCompatActivity {
    public static final String KEY_BODY = "Body";

    private TextView tvContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        tvContent = (TextView) findViewById(R.id.tvBody);
        Intent getIntent = getIntent();
        String content =getIntent.getStringExtra(KEY_BODY);
        tvContent.setText(content);
    }
}
