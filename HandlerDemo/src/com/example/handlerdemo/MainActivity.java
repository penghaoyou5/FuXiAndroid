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
		 * 问题：
		 * 为什么会在主线程中执行
		 * 
		 */
		
		//新建looper 以及与之相对应的messageQueue
		//看源码理解   这是什么设计模式？？？？   理解ThreadLocal
		Looper.prepare();
		
		/**
		 * 进行message消息的循环 queue.next();
		 * 以及发送消息给对应的handler   msg.target.dispatchMessage(msg);
		 */
		Looper.loop();
		
		Looper myLooper = Looper.myLooper();
		
		/**
		 * 创建handler 对象并绑定looper
		 * 绑定messagequeue 
		 * 回掉？
		 * 是否异步？
		 * 
		 * 
		 * 回掉方法有很多种   可以参考
		 * dispatchMessage(msg);
		 * 这里有回掉顺序设置
		 */
		Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
			}
		};
		
		/**
		 * handler 通过post与send来发送消息
		 * post实际上最后形成 message sendMessage;
		 * 
		 * 
		 * 最后调用enqueueMessage(方法
		 * msg.target = this;
		 * msg加到消息队列还没好好看懂
		 * 
		 * 
		 */
		handler.postAtTime(r, uptimeMillis);
		handler.sendMessageDelayed(msg, delayMillis);
		
		/**
		 * 思路：   由简单的不包含其他对象的类，到包含更多依赖对象的类
		 * 显示message消息为中心
		 * 然后messagequeue中封装了对消息队列的处理
		 * 
		 * 然后Looper中进行对message的处理，同时有很重要的ThreadLocal
		 * 然后由handle暴露出来让我用   这是封装 动作核心是looper 传递对像与对象处理是message  ,让我们用的我们需要关心的是handler
		 * 
		 * looper控制了线程的唯一性，操作，message的Message msg = queue.next()阻塞等待
		 * 阻塞等待祥看http://blog.csdn.net/ashqal/article/details/32107099
		 */
	}
}
