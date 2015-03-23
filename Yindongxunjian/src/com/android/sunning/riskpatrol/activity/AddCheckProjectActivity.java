package com.android.sunning.riskpatrol.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sunning.riskpatrol.Const;
import com.android.sunning.riskpatrol.entity.generate.Area;
import com.android.sunning.riskpatrol.entity.generate.JianChaXiangMu;
import com.android.sunning.riskpatrol.entity.generate.PatrolItem;
import com.example.yindongxunjian.R;

/**
 * Created by sunning on 15/2/15.
 * 添加检查项
 */
public class AddCheckProjectActivity extends BaseActivity {
    private JianChaXiangMu jianChaXiangMu;
    private LayoutInflater layoutInflater;
    private LinearLayout rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.add_check_project_layout);
        super.onCreate(savedInstanceState);
        setTitle("添加检查项");
        layoutInflater = LayoutInflater.from(this);
        addProject();
    }

    private void addProject() {
        rootView.removeAllViews();
        CreateCheckPointActivity createCheckPointActivity = (CreateCheckPointActivity) application.getSession().get(Const.KEY.CURRENT_CHECK_POINT);
        jianChaXiangMu = createCheckPointActivity.rootDatum.getJianChaXiangMu();
        if (jianChaXiangMu != null) {
            if (jianChaXiangMu.getAreas() != null) {
                int size = jianChaXiangMu.getAreas().size();
                LinearLayout innerLayout = null;
                for (int i = 0; i < size; i++) {
                    innerLayout = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner, null);
                    Area checkArea = jianChaXiangMu.getAreas().get(i);
                    if (checkArea.isSelect) {
                        View title = createTitle(checkArea);
                        innerLayout.addView(title);
                        View content = createContentView(checkArea);
                        innerLayout.addView(content);
                        rootView.addView(innerLayout);
                    }
                }
            }
        }
    }

    private View createContentView(Area acceptCheckArea) {
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.check_result_layout_inner, null);
        if (acceptCheckArea != null && acceptCheckArea.getPatrolItems() != null) {
            int size = acceptCheckArea.getPatrolItems().size();
            for (int i = 0; i < size; i++) {
                View view = createInnerView(acceptCheckArea.getPatrolItems().get(i));
                content.addView(view);
            }
        }
        return content;
    }

    private View createInnerView(final PatrolItem areaPatrolItem) {
        LinearLayout content = (LinearLayout) layoutInflater.inflate(R.layout.add_check_project_child_item, null);
        TextView title = (TextView) content.findViewById(R.id.add_check_project_child_item_tv);
        CheckBox checkBox = (CheckBox) content.findViewById(R.id.accept_check_project_ck);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                areaPatrolItem.isSelect = b;
            }
        });
//        RelativeLayout parent = (RelativeLayout) content.findViewById(R.id.accept_check_project_parent) ;
//        parent.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                LogUtils.e("点击了这个" + areaPatrolItems.itemName + "========") ;
//            }
//        });
        title.setText(areaPatrolItem.getItemName());
        return content;
    }

    @Override
    protected void findView() {
        rootView = (LinearLayout) findViewById(R.id.add_check_project_parent);
    }

    @Override
    protected void findEvent() {

    }

    private View createTitle(Area acceptCheckArea) {
        View view = layoutInflater.inflate(R.layout.add_check_project_form_title, null);
        TextView title = (TextView) view.findViewById(R.id.add_check_project_title);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.add_check_project_parent_cb);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (rootView != null && rootView.getChildCount() > 0) {
                    LinearLayout groupView = (LinearLayout) rootView.getChildAt(0);
                    if (groupView.getChildCount() >= 2) {
                        LinearLayout contentView = (LinearLayout) groupView.getChildAt(1);
                        int size = contentView.getChildCount();
                        for (int i = 0; i < size; i++) {
                            View view = contentView.getChildAt(i);
                            CheckBox cb = (CheckBox) view.findViewById(R.id.accept_check_project_ck);
                            cb.setChecked(checkBox.isChecked());
                        }
                    }
                }
            }
        });
        title.setText(acceptCheckArea.getAreaName());
        return view;
    }

}
