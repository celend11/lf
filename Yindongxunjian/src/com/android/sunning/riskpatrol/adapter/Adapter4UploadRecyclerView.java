package com.android.sunning.riskpatrol.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.sunning.riskpatrol.Const;
import com.example.yindongxunjian.R;
import com.android.sunning.riskpatrol.entity.EUploadImgList;
import com.lidroid.xutils.BitmapUtils;

/**
 * Created by sunning on 15/2/26
 */
public class Adapter4UploadRecyclerView extends RecyclerView.Adapter<Adapter4UploadRecyclerView.UploadImgHolder>{

    private LayoutInflater mInflater;
    public EUploadImgList _data;
    private BitmapUtils bitmapUtils ;


    public Adapter4UploadRecyclerView(Context context, EUploadImgList _data, OnItemClickListener mOnItemClickListener) {
        this._data = _data;
        mInflater = LayoutInflater.from(context) ;
        //todo 图片加载
        bitmapUtils = new BitmapUtils(context) ;
        this.mOnItemClickListener = mOnItemClickListener ;
    }

    @Override
    public UploadImgHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.upload_img_view_gridview_item, null) ;
        UploadImgHolder viewHolder = new UploadImgHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(UploadImgHolder uploadImgHolder, int i) {
        setView(uploadImgHolder,i) ;

    }

    private void setView(final UploadImgHolder uploadImgHolder, final int i){
        EUploadImgList.EUploadImg eUploadImg = _data.imgList.get(i) ;
        String localPath = eUploadImg.imgUrl ;
        if(!TextUtils.isEmpty(localPath)){
            bitmapUtils.display(uploadImgHolder.mImg, localPath) ;
        }
        if (mOnItemClickListener != null) {
            uploadImgHolder.mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    mOnItemClickListener.onItemClick(uploadImgHolder.itemView,i) ;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return _data.imgList.size() ;
    }


    public static class UploadImgHolder extends RecyclerView.ViewHolder {
        ImageView mImg ;

        public UploadImgHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.upload_img_item_iv_id) ;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position) ;
    }

    private OnItemClickListener mOnItemClickListener;
}
