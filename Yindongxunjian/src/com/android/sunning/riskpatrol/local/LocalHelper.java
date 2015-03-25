package com.android.sunning.riskpatrol.local;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import android.text.TextUtils;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;
import com.android.sunning.riskpatrol.util.FileUtil;
import com.android.sunning.riskpatrol.util.JSONUtils;

/**
 * Created by sunning on 15/2/28.
 */
public class LocalHelper {
    private String path = Const.Path.FILE_SYNCHRONIZATION ;

    private HomeEntity homeEntity ;

    private FileUtil fileUtil = new FileUtil() ;

    public LocalHelper(HomeEntity homeEntity) {
        this.homeEntity = homeEntity;
    }

    public LocalHelper() {
    }

    /**
     * 保存日月周类型数据到本地文件
     */
    public void saveDMWDataToFile(){
        if(homeEntity == null){
            return;
        }
        if(homeEntity.getData() == null){
            return;
        }
        int size = homeEntity.getData().size() ;


        for(int i = 0 ; i < size ; i ++ ){
            Datum datum = homeEntity.getData().get(i) ;
            save(datum);
        }
    }

    private void save(Datum datum) {
        String fileName = createFileName (datum) ;
        if(!TextUtils.isEmpty(fileName)){
            InputStream inputStream = new ByteArrayInputStream(JSONUtils.toJson(datum).getBytes()) ;
            fileUtil.write2SDFromInput(path,fileName,inputStream);
        }
    }

    public void saveSingleFile(Datum datum){
        if(datum == null){
            return;
        }
        save(datum) ;
    }

    private String createFileName(Datum datum){
        String fileName = new StringBuilder().append(datum.getCreatorID()).append("@").append(datum.getInspectNum()).append(".rp").toString() ;
        return  fileName;
    }
}
