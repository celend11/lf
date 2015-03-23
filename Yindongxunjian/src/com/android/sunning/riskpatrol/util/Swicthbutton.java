package com.android.sunning.riskpatrol.util;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CompoundButton;
import com.example.yindongxunjian.R;
 
public class Swicthbutton extends CompoundButton {
 
    public Swicthbutton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    private Drawable imgOFF;
    private Drawable imgON;
 
    public Swicthbutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SwitchButton, 0, 0);
        try {
            imgOFF = a.getDrawable(R.drawable.close);
            imgON = a.getDrawable(R.drawable.open);
        } catch (Exception e) {
            e.printStackTrace();
        }
        a.recycle();
    }
 
    public Swicthbutton(Context context) {
        super(context);
    }
 
    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        udpateDrawable();
 
    }
 
    private void udpateDrawable() {
        if (this.isChecked()) {
            Log.e(Swicthbutton.class.getName(),"changing to imgON");
            this.setBackgroundDrawable(imgON);
        } else {
            Log.e(Swicthbutton.class.getName(),"changing to imgOFF");
            this.setBackgroundDrawable(imgOFF);
        }
    }
 
    @Override
    protected void onFinishInflate() {
        udpateDrawable();
        super.onFinishInflate();
    }
 
}
 

