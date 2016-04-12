package com.example.timerdemo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.MessageQueue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	int i;
	Timer timer = new Timer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(MainActivity.this, "timer" + (++i), Toast.LENGTH_SHORT).show();
					}
				});
			}
		};
		TimerTask timerTask2 = new TimerTask() {

			@Override
			public void run() {
				Toast.makeText(MainActivity.this, "第二个timer" + (++i), Toast.LENGTH_SHORT).show();
			}
		};
		/**
		 * Timer简单用法，这里指根据起始时间 进行算时间执行
		 * 
		 * 发现timer与message一样都有写法 task.when = when; task.period = period;
		 * task.fixedRate = fixed; 上一个下一个等
		 * 
		 * MessageQueue 中 也有TimerHeap这样一个类像
		 * 
		 * Threadlocal中有 Values 这是什么思想？？
		 */
		timer.scheduleAtFixedRate(timerTask, 1000, 8000);
		// timer.schedule(timerTask, 5000, 8000);

		MessageQueue messageQueue;
		ThreadLocal<Object> local;
		
		//timer执行是新卡你线程 不一定能够哎主线程中只想
		
	}
}
