package com.example.ipcbeidiaoyong;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class BeiDiaoYongService extends Service {
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("beidiaoyong hhh");
	}

	public class ServiceBindl extends IBeiDiaoYong.Stub {

		@Override
		public String diaoyong(int i) throws RemoteException {
			Toast.makeText(getApplicationContext(), "ServiceBindl", Toast.LENGTH_SHORT).show();
			return "我是被调用的"+i;
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("bandding");
		// TODO Auto-generated method stub
		return new ServiceBindl();
	}

}
