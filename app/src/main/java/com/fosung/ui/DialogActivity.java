/*
 * *********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-12-21 下午3:16
 * ********************************************************
 */
package com.fosung.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fosung.frame.utils.ToastUtil;
import com.fosung.gui.ZAlert;
import com.fosung.gui.ZConfirm;
import com.fosung.gui.ZDialog;
import com.fosung.gui.ZDialogCheckBox;
import com.fosung.gui.ZDialogEdit;
import com.fosung.gui.ZDialogMenu;
import com.fosung.gui.ZDialogRadioGroup;
import com.fosung.gui.ZDialogWheelDate;

import java.util.ArrayList;

/**
 * 带JsBridge的webview的Demo
 */
public class DialogActivity extends FragmentActivity implements OnClickListener {
    private Activity     mActivity;
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        mActivity = this;

        init();
    }

    private void init() {
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        listButton.add(addButton("ZAlert"));
        listButton.add(addButton("ZConfirm"));
        listButton.add(addButton("ZDlgRadioGroup"));
        listButton.add(addButton("ZDlgMenu"));
        listButton.add(addButton("ZDialogWheelDate"));
        listButton.add(addButton("ZDialogEdit"));
        listButton.add(addButton("ZDialogCheckBox"));

        for (Button btn : listButton) {
            btn.setOnClickListener(this);
        }
    }

    private Button addButton(String text) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        Button button = new Button(mActivity);
        button.setText(text);
        button.setGravity(Gravity.CENTER);
        button.setAllCaps(false);
        llContent.addView(button, params);
        return button;
    }

    @Override
    public void onClick(View v) {
        if (v == listButton.get(0)) {
            new ZAlert(mActivity)
                    .setTitle("ZAlert")
                    .setMessage("这是一个Alert")
                    .show();
        } else if (v == listButton.get(1)) {
            new ZConfirm(mActivity)
                    .setTitle("ZConfirm")
                    .setMessage("这是一个通用对话框")
                    .addSubmitListener(new ZDialog.ZDialogSubmitInterface() {
                        @Override
                        public boolean submit() {
                            ToastUtil.toastShort("点击了确定");
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(2)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDialogRadioGroup(mActivity)
                    .setTitle("ZDialogRadioGroup")
                    .setDatas(arrt, "menu1")
                    .addSubmitListener(new ZDialog.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(3)) {
            final String[] arrt = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDialogMenu(mActivity)
                    .setTitle("ZDialogMenu")
                    .setDatas(arrt)
                    .addSubmitListener(new ZDialog.ZDialogParamSubmitInterface<Integer>() {
                        @Override
                        public boolean submit(Integer integer) {
                            ToastUtil.toastShort("选择了" + arrt[integer]);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(4)) {
            new ZDialogWheelDate(mActivity)
                    .setTitle("ZDialogWheelDate")
                    .setDataSubmitListener(new ZDialogWheelDate.OnDateSubmitListener() {
                        @Override
                        public void onClick(int year, int month, int day) {
                            ToastUtil.toastShort(year + "-" + month + "-" + day);
                        }
                    })
                    .show();
        } else if (v == listButton.get(5)) {
            new ZDialogEdit(mActivity)
                    .setTitle("ZDialogEdit")
                    .setEditText("回填数据")
                    .addSubmitStrListener(new ZDialog.ZDialogParamSubmitInterface<String>() {
                        @Override
                        public boolean submit(String s) {
                            ToastUtil.toastShort("输入框数据" + s);
                            return true;
                        }
                    })
                    .show();
        } else if (v == listButton.get(6)) {
            final String[] arrStr = new String[]{"menu1", "menu2", "menu3", "menu4", "menu5"};
            new ZDialogCheckBox(mActivity)
                    .setTitle("ZDialogCheckbBox")
                    .setDatas(arrStr, null)
                    .addValueSubmitListener(new ZDialog.ZDialogParamSubmitInterface<ArrayList<String>>() {
                        @Override
                        public boolean submit(ArrayList<String> s) {
                            String str = "";
                            for (String s1 : s) {
                                str += s1;
                                str += ",";
                            }
                            ToastUtil.toastShort("选中数据" + str);

                            return true;
                        }
                    })
                    .show();
        }
    }
}