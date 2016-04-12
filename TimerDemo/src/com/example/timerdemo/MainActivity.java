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
				Toast.makeText(MainActivity.this, "�ڶ���timer" + (++i), Toast.LENGTH_SHORT).show();
			}
		};
		/**
		 * Timer���÷�������ָ������ʼʱ�� ������ʱ��ִ��
		 * 
		 * ����timer��messageһ������д�� task.when = when; task.period = period;
		 * task.fixedRate = fixed; ��һ����һ����
		 * 
		 * MessageQueue �� Ҳ��TimerHeap����һ������
		 * 
		 * Threadlocal���� Values ����ʲô˼�룿��
		 */
		timer.scheduleAtFixedRate(timerTask, 1000, 8000);
		// timer.schedule(timerTask, 5000, 8000);

		MessageQueue messageQueue;
		ThreadLocal<Object> local;
		
		//timerִ�����¿����߳� ��һ���ܹ������߳���ֻ��
		
	}
}
