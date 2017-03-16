package com.example.gk.testsoftinput;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Context ctx;
    private Activity act;
    private EditText edit;
    private EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        ctx = this;
        act = this;

        edit = (EditText) this.findViewById(R.id.edit);


        edit1 = (EditText) this.findViewById(R.id.edit1);
        edit.setInputType(InputType.TYPE_NULL);//先在获取焦点的edittext上禁用输入方式.这样就不会调起系统的输入法

        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inputback = edit.getInputType();
                edit.setInputType(InputType.TYPE_NULL);
                new KeyboardUtil(act, ctx, edit).showKeyboard();
                edit.setInputType(inputback);
                return false;
            }
        });

        edit1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int inputback = edit1.getInputType();
                edit1.setInputType(InputType.TYPE_NULL);
                new KeyboardUtil(act, ctx, edit1).showKeyboard();
                edit1.setInputType(inputback);
                return false;
            }
        });
    }
}
