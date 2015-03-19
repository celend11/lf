package com.android.sunning.riskpatrol.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.custom.crop.Crop;

import java.io.File;

/**
 * Created by sunning on 14/10/29.
 */
public class CropActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getValue() ;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void findEvent() {

    }

    private void getValue() {
        Bundle bundle = getIntent().getExtras() ;
        String key = Const.KEY.URI;
        if(bundle != null && bundle.containsKey(key)){
            Uri uri = bundle.getParcelable(key) ;
            beginCrop(uri) ;
            bundle.remove(key);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri outputUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
        new Crop(source).output(outputUri).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
//            resultView.setImageURI(Crop.getOutput(result));
            onBackPressed() ;
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
