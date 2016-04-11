package com.example.handlerdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * ���⣺
		 * Ϊʲô�������߳���ִ��
		 * 
		 */
		
		//�½�looper �Լ���֮���Ӧ��messageQueue
		//��Դ�����   ����ʲô���ģʽ��������   ���ThreadLocal
		Looper.prepare();
		
		/**
		 * ����message��Ϣ��ѭ�� queue.next();
		 * �Լ�������Ϣ����Ӧ��handler   msg.target.dispatchMessage(msg);
		 */
		Looper.loop();
		
		Looper myLooper = Looper.myLooper();
		
		/**
		 * ����handler ���󲢰�looper
		 * ��messagequeue 
		 * �ص���
		 * �Ƿ��첽��
		 * 
		 * 
		 * �ص������кܶ���   ���Բο�
		 * dispatchMessage(msg);
		 * �����лص�˳������
		 */
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}
		};
		
		/**
		 * handler ͨ��post��send��������Ϣ
		 * postʵ��������γ� message sendMessage;
		 * 
		 * 
		 * ������enqueueMessage(����
		 * msg.target = this;
		 * msg�ӵ���Ϣ���л�û�úÿ���
		 * 
		 * 
		 */
		handler.postAtTime(r, uptimeMillis);
		handler.sendMessageDelayed(msg, delayMillis);
		
		/**
		 * ˼·��   �ɼ򵥵Ĳ���������������࣬���������������������
		 * ��ʾmessage��ϢΪ����
		 * Ȼ��messagequeue�з�װ�˶���Ϣ���еĴ���
		 * 
		 * Ȼ��Looper�н��ж�message�Ĵ���ͬʱ�к���Ҫ��ThreadLocal
		 * Ȼ����handle��¶����������   ���Ƿ�װ ����������looper ���ݶ������������message  ,�������õ�������Ҫ���ĵ���handler
		 * 
		 * looper�������̵߳�Ψһ�ԣ�������message��Message msg = queue.next()�����ȴ�
		 * �����ȴ��鿴http://blog.csdn.net/ashqal/article/details/32107099
		 */
	}
}
