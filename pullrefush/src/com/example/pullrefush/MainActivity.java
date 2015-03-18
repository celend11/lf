package com.example.pullrefush;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	int [] da=new int[5];
	ListView list;
	LayoutInflater inflater;
	LinearLayout linearlayout;
	int linelayoutheght=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list=(ListView) findViewById(R.id.list1);
//		inflater=LayoutInflater.from(MainActivity.this);
//		linearlayout=(LinearLayout) findViewById(R.layout.shuaxin);
//		linelayoutheght =linearlayout.getMeasuredHeight();
//		headContentWidth = headView.getMeasuredWidth();

//		linearlayout.setPadding(0, -1 * 200, 0, 0);
//		linearlayout.invalidate();
//		list.addHeaderView(linearlayout);
		for(int i=0;i<5;i++){
			da[i]=i;
		}
		Mydapter adpter=new Mydapter(MainActivity.this,da);
//		TextView tv2=new TextView(MainActivity.this);
//		LayoutParams lp=new LayoutParams(1,-100);
//		tv2.setLayoutParams(lp);
//		tv2.setText("ÕýÔÚË¢ÐÂ¡£¡£¡£¡£");
//		list.addHeaderView(tv2);
		list.setAdapter(adpter);
		linelayoutheght=list.getMeasuredHeight();
		Toast.makeText(MainActivity.this,linelayoutheght+"",Toast.LENGTH_LONG).show();

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public class Mydapter extends BaseAdapter{

		Context context;
		int[] da;
		public Mydapter(Context context, int[] da) {
			super();
			this.context = context;
			this.da = da;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return da.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return da[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView=LayoutInflater.from(context).inflate(R.layout.item, parent,false);
			TextView tv=(TextView) convertView.findViewById(R.id.tv);
			tv.setText(da[position]+"");
			return convertView;
		}
		
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
