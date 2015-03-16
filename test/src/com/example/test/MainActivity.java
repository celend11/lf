package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				打开相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivity(intent);
				
			}
		});
		findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				打开相册
                Intent intent = new Intent(Intent.ACTION_PICK,null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent,1);
				
			}
		});
		
//		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//			String path=Environment.getExternalStorageDirectory()+"/";
//			
//			File file=new File(path+"a.txt");
//		Toast.makeText(MainActivity.this,path,Toast.LENGTH_LONG).show();
//		if(!file.exists()){
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
