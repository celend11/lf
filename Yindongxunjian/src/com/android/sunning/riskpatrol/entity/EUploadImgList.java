package com.android.sunning.riskpatrol.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunning on 15/2/26.
 * 上传图片的bean
 */
public class EUploadImgList extends BaseEntity{
    public int chooseImgCount ;
    public List<EUploadImg> imgList ;

    public EUploadImgList() {
        imgList = new ArrayList<EUploadImg>() ;
    }

    public static class EUploadImg extends BaseEntity{
        public String descFormation ;
        public String imgUrl ;
        public String fileName ;
        public int imgId ;
        public int width ;
        public int height ;
    }

}
