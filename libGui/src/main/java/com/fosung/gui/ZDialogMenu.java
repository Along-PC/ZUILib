/*
 * **********************************************************
 *   author   colin
 *   company  fosung
 *   email    wanglin2046@126.com
 *   date     16-10-8 下午4:47
 * *********************************************************
 */

package com.fosung.gui;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * 菜单弹出框
 */
public class ZDialogMenu extends ZDialog {
    private static int LAYOUT_ID;

    protected LinearLayout                         llMenu;        //菜单容器布局
    protected TextView                             tvTitle;        //标题控件
    protected ZDialogParamSubmitInterface<Integer> submitInter;    // 点击确定按钮回调接口
    protected Context                              context;

    /**
     * 如果用户需要自己使用布局替代此xml文件，则需要在Application中初始化此函数，
     * 传入自定义的Layout，layout中的所有Id必须与本xml的Id相同
     */
    public static void initLayout(int layoutId) {
        LAYOUT_ID = layoutId;
    }

    public ZDialogMenu(Activity context) {
        super(context, LAYOUT_ID == 0 ? R.layout.gui_dlg_menu : LAYOUT_ID);
        this.context = context;
        initRes();
    }

    private void initRes() {
        llMenu = getView(R.id.dialogmenu_ll);
        tvTitle = getView(R.id.dialogmenu_title);
    }

    public ZDialogMenu setTitle(String str) {
        if (!TextUtils.isEmpty(str)) {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(str);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        return this;
    }

    public ZDialogMenu setDatas(ArrayList<String> listData) {
        setDatas(listData.toArray(new String[listData.size()]));
        return this;
    }

    public ZDialogMenu setDatas(String[] arrStr) {
        int padding = (int) context.getResources()
                                   .getDimension(R.dimen.gui_dimens_big);
        int paddingLR = (int) context.getResources()
                                   .getDimension(R.dimen.gui_dimens_mid);
        float textSize = context.getResources()
                                .getDimension(R.dimen.gui_textsize_big);
        LayoutParams layout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        llMenu.removeAllViews();
        for (int i = 0, attrStrLength = arrStr.length; i < attrStrLength; i++) {
            final int index = i;
            String anAttrStr = arrStr[i];
            TextView tv = new TextView(context);
            tv.setText(anAttrStr);
            if (i == 0) {
                tv.setBackgroundResource(R.drawable.gui_dlg_menu_sel);
            } else {
                tv.setBackgroundResource(R.drawable.gui_listitem_sel);
            }
            tv.setTextAppearance(context, R.style.Gui_TextStyle_GrayMid_Normal);
            tv.setPadding(paddingLR, padding, paddingLR, padding);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (submitInter != null) {
                        submitInter.submit(index);
                        cancel();
                    }
                }
            });
            llMenu.addView(tv, layout);
        }
        return this;
    }

    /**
     * 添加点击回调接口
     */
    public ZDialogMenu addSubmitListener(ZDialogParamSubmitInterface<Integer> submitInter) {
        this.submitInter = submitInter;
        return this;
    }
}