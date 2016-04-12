package com.example.ipcdiaoyong;

import java.util.List;

import com.example.ipcbeidiaoyong.IBeiDiaoYong;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {

	private TextView et_inttye;
	private View btn_diaoyong;
	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_inttye = (TextView) findViewById(R.id.et_inttye);
		btn_diaoyong = findViewById(R.id.btn_diaoyong);
		btn_diaoyong.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_diaoyong:
			System.out.println("ComponentNamebtn_diaoyong");
			Toast.makeText(getApplicationContext(), "dianji", Toast.LENGTH_SHORT).show();
			// 绑定到服务
			Intent intent = new Intent("com.example.ipcbeidiaoyong.BeiDiaoYongService");
//			intent.setPackage(getPackageName());
//			intent.setPackage("com.example.ipcbeidiaoyong");
			intent = getExplicitIntent(getApplicationContext(), intent);
			this.bindService(intent, this, Service.BIND_AUTO_CREATE);

			break;

		default:
			break;
		}

	}

	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		System.out.println("ComponentName");
		IBeiDiaoYong asInterface = IBeiDiaoYong.Stub.asInterface(service);
		i = Integer.parseInt(et_inttye.getText().toString());
		final String diaoyong;
		try {
			 diaoyong = asInterface.diaoyong(i);
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), diaoyong, Toast.LENGTH_SHORT).show();
					
				}
			});
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onServiceDisconnected(ComponentName name) {

	}

	public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
		// Retrieve all services that can match the given intent
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
		// Make sure only one match was found
		if (resolveInfo == null || resolveInfo.size() != 1) {
			return null;
		}
		// Get component info and create ComponentName
		ResolveInfo serviceInfo = resolveInfo.get(0);
		String packageName = serviceInfo.serviceInfo.packageName;
		String className = serviceInfo.serviceInfo.name;
		ComponentName component = new ComponentName(packageName, className);
		// Create a new intent. Use the old one for extras and such reuse
		Intent explicitIntent = new Intent(implicitIntent);
		// Set the component to be explicit
		explicitIntent.setComponent(component);
		return explicitIntent;
	}

}
