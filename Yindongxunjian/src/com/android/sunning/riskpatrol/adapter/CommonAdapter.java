package com.android.sunning.riskpatrol.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by sunning on 14-9-29.
 */
public abstract class CommonAdapter<T> extends BaseAdapter  {
    protected LayoutInflater mInflater;  
    protected Context mContext;  
    protected List<T> adapterData;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<T> adapterData, int itemLayoutId) {
        this.mContext = context;  
        this.mInflater = LayoutInflater.from(mContext);  
        this.adapterData = adapterData;
        this.mItemLayoutId = itemLayoutId;
    }
  
    @Override  
    public int getCount() {
        return adapterData.size();
    }  
  
    @Override  
    public T getItem(int position) {
        return adapterData.get(position);
    }  
  
    @Override  
    public long getItemId(int position) {
        return position;  
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView , parent);
        convert(viewHolder, getItem(position));  
        return viewHolder.getConvertView();  
    }
  
    public abstract void convert(ViewHolder helper, T item);  
  
    private ViewHolder getViewHolder(int position, View convertView,ViewGroup parent){
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }
}