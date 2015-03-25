package com.android.sunning.riskpatrol.local;

import java.util.ArrayList;
import java.util.List;

import android.util.SparseArray;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.HomePageEntity;
import com.android.sunning.riskpatrol.entity.generate.Datum;
import com.android.sunning.riskpatrol.entity.generate.HomeEntity;

/**
 * Created by sunning on 15/2/28.
 * 检查单分类
 */
public class Sorting {

    private SparseArray<List<HomePageEntity>> array = new SparseArray<List<HomePageEntity>>() ;
    private List<HomePageEntity> dayContent = new ArrayList<HomePageEntity>() ;
    private List<HomePageEntity> weekContent = new ArrayList<HomePageEntity>() ;
    private List<HomePageEntity> monthContent = new ArrayList<HomePageEntity>() ;

    public Sorting() {
        array.put(Const.CollectType.DAY , dayContent) ;
        array.put(Const.CollectType.WEEK , weekContent) ;
        array.put(Const.CollectType.MONTH , monthContent) ;
    }

    public Sorting sort(HomeEntity homeEntity){
        if(homeEntity == null){
            return null;
        }
        int size = homeEntity.getData().size() ;
        for(int i=0 ; i < size ; i++){
           Datum datum = homeEntity.getData().get(i) ;
           if(datum!=null){
               setToArray(datum) ;
           }
        }
        return this ;
    }

    public Sorting add(Datum datum){
        if(datum != null){
            setToArray(datum) ;
        }
        return this ;
    }

    /**
     * 获取处理完的数据
     * @return
     */
    public SparseArray<List<HomePageEntity>> obtainProcessFinishDatum() {
        return array;
    }


    private void setToArray(Datum datum){
        HomePageEntity homePageEntity = new HomePageEntity() ;
        homePageEntity.creatorID = datum.getCreatorID() ;
        homePageEntity.creatorName = datum.getCreatorName() ;
        homePageEntity.inspectNumFormat = datum.getInspectNumFormat() ;
        homePageEntity.inspectNum = datum.getInspectNum() ;
        homePageEntity.createDate = datum.getCreateTime() ;
        homePageEntity.patrolItemName = datum.getSite().getSiteName() ;
        homePageEntity.isDispatch = datum.getInspectType() == 1 ;
        homePageEntity.isLocal = datum.isLocal ;
        InspectType type = getTypeByStatus(datum.getInspectType()) ;
        switch (type){
            case Day:
                dayContent.add(homePageEntity) ;
                break;
            case WEEK:
                weekContent.add(homePageEntity) ;
            case MONTH:
                monthContent.add(homePageEntity) ;
                break;
        }
    }


    public InspectType getTypeByStatus(int status){
        InspectType inspectType = null;
        switch (status){
            case Const.CollectType.DAY:
                inspectType =  InspectType.Day;
            case Const.CollectType.WEEK:
                inspectType =  InspectType.WEEK;
            case Const.CollectType.MONTH:
                inspectType =  InspectType.Day;
        }
        return inspectType;
    }
}
