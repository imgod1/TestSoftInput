package com.example.gk.testsoftinput;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.lang.reflect.Method;

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

        dontUseSystemSoftInput(edit);//把所有的edittext都设置为不调用系统的输入法
        dontUseSystemSoftInput(edit1);
        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboardUtil(act, ctx, edit).showKeyboard();
                return false;
            }
        });

        edit1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (null == keyboardUtil2) {
                    keyboardUtil2 = new KeyboardUtil(act, ctx, edit1);
                }
                keyboardUtil2.showKeyboard();
                return false;
            }
        });
    }

    private KeyboardUtil keyboardUtil2;


    private void dontUseSystemSoftInput(EditText editText) {
        int currentSdkVersion = Build.VERSION.SDK_INT;
        if (currentSdkVersion >= 11) {//这个解决了 之前每次touch edittext光标总是回到第一个位置的问题
            Class<EditText> cls = EditText.class;
            Method setShowSoftInputOnFocus = null;
            try {
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(false);
                setShowSoftInputOnFocus.invoke(editText, false);
                setShowSoftInputOnFocus.invoke(editText, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            editText.setInputType(InputType.TYPE_NULL);
        }
    }
}
