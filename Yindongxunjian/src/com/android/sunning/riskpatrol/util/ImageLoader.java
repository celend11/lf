package com.android.sunning.riskpatrol.util;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.android.sunning.riskpatrol.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ImageLoader {

    protected com.nostra13.universalimageloader.core.ImageLoader imageLoader ;
    protected DisplayImageOptions options ;


    /**
     * 使用默认图，并且不使用缓存
     */
    public ImageLoader() {
        imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance() ;
        options = getConfig() ;
    }

    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();

    public void displayNoneAnimation(ImageView container,String url){
        imageLoader.displayImage(url, container , getConfigNoneRound() , animateFirstListener) ;
    }

    private DisplayImageOptions getConfigNoneRound(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .build();
        return options ;
    }

    private DisplayImageOptions getConfig(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.ic_launcher)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.ic_launcher)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(10))
                .build();
        return options ;
    }

    public void display(ImageView container,String url ){
        imageLoader.displayImage(url , container, options, animateFirstListener) ;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}