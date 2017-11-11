package com.xiaoqi.vitrualapkplugin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.utils.PluginUtil;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

	/**
	 * 这是一个VirtualApk插件
	 */
	private TextView tvContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plugin);
		initView();
		Context context = PluginManager.getInstance(getApplicationContext()).getHostContext().getApplicationContext();
		Class<Context> contextClass = (Class<Context>) context.getClass();
		try {
			Field field = contextClass.getField("i");
			int a = field.getInt(context);
			tvContent.setText("" + a);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void initView() {
		tvContent = (TextView) findViewById(R.id.tv_content);
		tvContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClassName("com.xiaoqi.virtualapkdemo", "com.xiaoqi.virtualapkdemo.MainActivity");
				startActivity(intent);
			}
		});
	}
}
