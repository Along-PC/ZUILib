/***********************************************************
 * author   colin
 * company  fosung
 * email    wanglin2046@126.com
 * date     16-7-18 下午5:24
 **********************************************************/

package com.fosung.ui.fragment;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.fosung.frame.app.BaseFrameLazyLoadFrag;
import com.fosung.frame.utils.ActivityUtil;
import com.fosung.ui.R;
import com.fosung.ui.DialogActivity;
import com.fosung.ui.OtherViewActivity;
import com.fosung.ui.WebViewActivity;
import com.fosung.ui.WebViewVideoActivity;
import com.fosung.ui.ZKVViewActivity;

import java.util.ArrayList;


/**
 * View相关示例
 */
public class ViewFragment extends BaseFrameLazyLoadFrag implements View.OnClickListener {
    private LinearLayout llContent;
    private ArrayList<Button> listButton = new ArrayList<>();

    public static ViewFragment newInstance() {
        Bundle args = new Bundle();
        ViewFragment fragment = new ViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        init();
    }

    @Override
    protected int getRootViewLayId() {
        return R.layout.activity_common;
    }


    private void init() {
        llContent = getView(R.id.ll_content);
        listButton.add(addButton("ZKVActivity"));
        listButton.add(addButton("WebViewDemo"));
        listButton.add(addButton("DialogDemo"));
        listButton.add(addButton("其他View示例"));

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
            ActivityUtil.startActivity(mActivity, ZKVViewActivity.class);
        } else if (v == listButton.get(1)) {
            ActivityUtil.startActivity(mActivity, WebViewActivity.class);
        } else if (v == listButton.get(2)) {
            ActivityUtil.startActivity(mActivity, WebViewVideoActivity.class);
        } else if (v == listButton.get(3)) {
            ActivityUtil.startActivity(mActivity, DialogActivity.class);
        } else if (v == listButton.get(4)) {
            ActivityUtil.startActivity(mActivity, OtherViewActivity.class);
        }
    }
}