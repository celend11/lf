package com.android.sunning.riskpatrol.record;

import java.io.IOException;
import java.util.UUID;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.Log;

import com.android.sunning.riskpatrol.Const;

/**
 * Created by sunning on 15/3/6.
 */
public class RecordHelper {

    private MediaRecorder mRecorder;

    private MediaPlayer mPlayer;


    private String recordPath = Const.Path.RECORD_LOCAL;

    private String LOG_TAG = RecordHelper.class.getSimpleName();

//    private static RecordHelper recordHelper = new RecordHelper() ;
//
//    public static RecordHelper getInstance(){
//        return recordHelper ;
//    }

    public RecordHelper() {
        mRecorder = new MediaRecorder();
    }

    public String prepare() {
        String fileName;
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        fileName = recordPath + UUID.randomUUID()+".amr";
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
        return fileName;
    }


    public void startRecord() {
        mRecorder.start();
    }

    public void stopRecord() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public void startPlay(String fileName) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(fileName);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
    }

    public void stopPlay(String fileName) {
        mPlayer.release();
        mPlayer = null;
    }


}
