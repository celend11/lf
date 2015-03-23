package com.android.sunning.riskpatrol.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.util.ImageLoader;

/**
 * Created by sunning on 14-9-29.
 */
public class ViewHolder{
    private final SparseArray<View> mViews;
    private int mPosition , finalHeight , finalWidth ;
    private View mConvertView;
    private ImageLoader imageLoader ;

    private ViewHolder(Context context, ViewGroup parent, int layoutId,int position){
        this.mPosition = position;
        this.mViews = new SparseArray<View>() ;
        //TODO 图片加载
        imageLoader = new ImageLoader() ;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position){
        if (convertView == null){
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public View getConvertView(){
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId){
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm){
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
    public ViewHolder setImageByUrl(int viewId, String url){
        final ImageView headView = getView(viewId) ;
        imageLoader.display(headView , url );
        return this;
    }

    public void setVisibility(int viewId , boolean isVisibility){
        View view = getView(viewId) ;
        view.setVisibility(isVisibility?View.VISIBLE:View.GONE) ;
    }

    public int getPosition(){
        return mPosition;
    }

    public void setOnClick(int viewId ,View.OnClickListener onClickListener) {
        View view = getView(viewId) ;
        view.setClickable(true) ;
        view.setOnClickListener(onClickListener) ;
    }

    public void setViewStub(int viewId){
        ViewStub stub = getView(viewId) ;
        stub.inflate() ;
    }
}