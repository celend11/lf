package com.example.pullrefush;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class pushlistview extends ListView{
	LayoutInflater inflater;
	LinearLayout linearlayout;
	int linelayoutheght=0;
	public pushlistview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
		// TODO Auto-generated constructor stub
	}
	public pushlistview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
		// TODO Auto-generated constructor stub
	}
	public pushlistview(Context context) {
		super(context);
		init(context);
		// TODO Auto-generated constructor stub
	}

	public void init(Context context){
		inflater=LayoutInflater.from(context);
		linearlayout=(LinearLayout) findViewById(R.layout.shuaxin);
		linelayoutheght =linearlayout.getMeasuredHeight();
//		headContentWidth = headView.getMeasuredWidth();

		linearlayout.setPadding(0, -1 * linelayoutheght, 0, 0);
		linearlayout.invalidate();

		addHeaderView(linearlayout, null, false);
	}
}
