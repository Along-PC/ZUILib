/*
 * *********************************************************
 *   author   colin
 *   company  telchina
 *   email    wanglin2046@126.com
 *   date     18-1-9 上午8:51
 * ********************************************************
 */

package com.zcolin.gui;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


/**
 * 普通对话框，有确定和取消按钮
 */
public class ZConfirm extends ZDialog<ZConfirm> implements OnClickListener {
    private static int LAYOUT_ID;

    protected ZDialogSubmitListener submitListener;    // 点击确定按钮回调接口
    protected ZDialogCancelListener cancelListener;    // 点击取消按钮回调接口
    protected TextView              tvTitle;            // 标题文字
    protected TextView              tvOK;               // 确定按钮
    protected TextView              tvCancel;           // 取消按钮
    protected TextView              tvMessage;           // 消息内容

    public static ZConfirm instance(Context context) {
        return new ZConfirm(context);
    }

    /**
     * 如果用户需要自己使用布局替代此xml文件，单次更改
     *
     * @param layoutId 布局id
     */
    public static ZConfirm instance(Context context, @LayoutRes int layoutId) {
        return new ZConfirm(context, layoutId);
    }

    /**
     * 如果用户需要自己使用布局替代此xml文件，则需要在Application中初始化此函数，全局更改
     * 传入自定义的Layout，layout中的所有Id必须与本xml的Id相同
     *
     * @param layoutId 布局id
     */
    public static void initLayout(int layoutId) {
        LAYOUT_ID = layoutId;
    }

    public ZConfirm(Context context) {
        this(context, 0);
    }

    /**
     * @param context
     */
    public ZConfirm(Context context, @LayoutRes int layoutId) {
        super(context, layoutId == 0 ? (LAYOUT_ID == 0 ? R.layout.gui_dlg_confirm : LAYOUT_ID) : layoutId);
        initRes();
    }

    /**
     * 设置标题
     */
    public ZConfirm setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    /**
     * 设置内容
     */
    public ZConfirm setMessage(String msg) {
        tvMessage.setText(msg);
        return this;
    }

    /**
     * 设置按钮文字
     */
    public ZConfirm setOKBtnText(String text) {
        tvOK.setText(text);
        return this;
    }

    /**
     * 设置按钮文字
     */
    public ZConfirm setCancelBtnText(String text) {
        tvCancel.setText(text);
        return this;
    }

    /**
     * 添加确定回调接口
     */
    public ZConfirm addSubmitListener(ZDialogSubmitListener submitInterface) {
        this.submitListener = submitInterface;
        return this;
    }


    /**
     * 添加取消回调接口
     */
    public ZConfirm addCancelListener(ZDialogCancelListener cancelInterface) {
        this.cancelListener = cancelInterface;
        return this;
    }

    /**
     * 设置提示内容对齐方式
     */
    public ZConfirm setMessageGravity(int gravity) {
        tvMessage.setGravity(gravity);
        return this;
    }


    @Override
    public void show() {
        if (tvTitle.getText().length() == 0) {
            tvTitle.setVisibility(View.GONE);
        } else {
            tvTitle.setVisibility(View.VISIBLE);
        }

        super.show();
    }

    @Override
    public void onClick(View v) {
        if (v == tvOK) {
            if (submitListener != null) {
                boolean flag = submitListener.submit();
                if (flag) {
                    cancel();
                }
            }
        } else if (v == tvCancel) {
            if (cancelListener != null) {
                boolean flag = cancelListener.cancel();
                if (flag) {
                    cancel();
                }
            } else {
                cancel();
            }
        }
    }


    private void initRes() {
        tvOK = getView(R.id.dialog_okbutton);
        tvCancel = getView(R.id.dialog_cancelbutton);
        tvMessage = getView(R.id.dialog_message);
        tvTitle = getView(R.id.dialog_tilte);
        tvMessage.setMovementMethod(new ScrollingMovementMethod());

        tvOK.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }
}
