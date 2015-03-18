package com.example.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	private LinearLayout liner;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		liner=(LinearLayout) findViewById(R.id.liner);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				打开相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//				Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");     
				startActivityForResult(intent,2);
				
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
		Uri uri=null;
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			if(resultCode==RESULT_OK){
//			String path=Environment.getExternalStorageDirectory() +"/image/"+"Camera/";
//            BitmapFactory.Options opt = new BitmapFactory.Options();
//            opt.inPreferredConfig = Bitmap.Config.RGB_565;
//            opt.inPurgeable = true;
//            opt.inInputShareable = true;
//            Bitmap bitmap = BitmapFactory.decodeFile(path, opt) ;
//            uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
//            Uri outputUri = Uri.fromFile(new File(path, UUID.randomUUID().toString() + ".jpg"));
//			Intent intent =new Intent();
//			intent.setData(uri);
//			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
//				获取到相册中图片的uri
				uri=data.getData();
				ContentResolver cr=getContentResolver();

					try {
						InputStream in=cr.openInputStream(uri);
//						byte[] b=null;
//						int c=-1;
//						int i=0;
//						in.read(b);
						LayoutParams lp=new LayoutParams(200, 100);
						ImageView imageView=new ImageView(this);
						imageView.setLayoutParams(lp);
						Bitmap bit =BitmapFactory.decodeStream(in);
						imageView.setImageBitmap(bit);
						liner.addView(imageView);
//						im.setImageURI(uri);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

//				Toast.makeText(MainActivity.this,uri.toString(),Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(MainActivity.this,"取消上传",Toast.LENGTH_LONG).show();
				
			}
			break;
		case 2:
			uri=data.getData();
			if(uri==null){
				Bundle bundle=data.getExtras();
				if(bundle!=null){
					 Bitmap  photo = (Bitmap) bundle.get("data"); 
						LayoutParams lp=new LayoutParams(200, 100);
						ImageView imageView=new ImageView(this);
						imageView.setLayoutParams(lp);
						imageView.setImageBitmap(photo);
						liner.addView(imageView);
				}
			}

			break;
		default:
			break;
		}
		
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
