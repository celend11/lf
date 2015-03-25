package com.android.sunning.riskpatrol.custom.dialog;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.android.sunning.riskpatrol.record.RecordHelper;
import com.example.yindongxunjian.R;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by sunning on 15/3/6.
 */
public class RecordDialog extends BaseDialog{

    private TextView time , title ;

    private String formatStr = "录音时间:%s" ;

    private boolean isRecording ;

    private String fileName ;

    private TimerTask timerTask ;

    private RecordHelper record = new RecordHelper() ;

    private Handler handle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            count--;
            LogUtils.e("===============LOOP"+count) ;
            time.setText(String.format(formatStr,count+"")) ;
        }
    } ;

    private int count ;

    public RecordDialog(Context context) {
        super(context);
        setCancelable(false) ;
        setDialogContentView(R.layout.record_dialog_layout);
        setTitle("录音");
        findView() ;

    }

    public void startComputeTime(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.e("============");
                        handle.sendEmptyMessage(0) ;
                    }
                };
            }
        };
        new Timer().schedule(timerTask, 1, 1000) ;
    }

    public void btn1(OnClickListener listener){
        this.setButton1("开始", listener);
    }

    public void start(){
        fileName = record.prepare() ;
        LogUtils.e("fileName="+fileName) ;
        record.startRecord();
        startComputeTime() ;
    }

    public void stop(){
        timerTask.cancel() ;
        count = 0 ;
        record.stopRecord() ;
    }
    public void btn2(OnClickListener listener){
        this.setButton2("结束", listener);
    }

    private void findView() {
        time = (TextView) findViewById(R.id.record_time) ;
        title = (TextView) findViewById(R.id.record_title) ;
    }


    public void setTime(TextView time) {
        this.time = time;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getTime() {
        return time;
    }

    public TextView getTitle() {
        return title;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public void setRecording(boolean isRecording) {
        this.isRecording = isRecording;
    }
}
