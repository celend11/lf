package com.android.sunning.riskpatrol.custom.dialog;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.TextView;

/**
 * Created by sunning on 15/2/21.
 */
public class DateDialog {

    private TextView showDate ;
    private Context context ;
    private DatePickerDialog picker ;
    private int year;
    private int month;
    private int day;
    private DateSetFinish dateSetFinish ;

    public DateDialog(TextView showDate, Context context , DateSetFinish dateSetFinish) {
        this.showDate = showDate;
        this.context = context;
        this.dateSetFinish = dateSetFinish ;
        init() ;
    }

    private void init() {
        initDate() ;
        picker = new DatePickerDialog(context,Datelistener,year,month,day);
        picker.setCancelable(false);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    	showDate.setText("2015-02-14");
                        dialog.dismiss() ;
                    }
                });
    }

    public void show(){
        picker.show();
    }



    private void initDate() {
        Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
        Date mydate = new Date(); //获取当前日期Date对象
        mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期

        year=mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month=mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day=mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天
    }

    private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
    {
        /**params：view：该事件关联的组件
         * params：myyear：当前选择的年
         * params：monthOfYear：当前选择的月
         * params：dayOfMonth：当前选择的日
         */
        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {


            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
            year=myyear;
            month=monthOfYear;
            day=dayOfMonth;
            //更新日期   
            updateDate();

        }
        //当DatePickerDialog关闭时，更新日期显示
        private void updateDate() {
            String selectDate = year+"-"+(month+1)+"-" + day ;
            showDate.setText(selectDate);
            if(dateSetFinish != null){
                dateSetFinish.dataFinish(selectDate);
            }
        }
    };

    public static interface DateSetFinish{
        public void dataFinish(String date) ;
    }
}
