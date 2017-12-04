package com.napas.paytouch.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.ProgressBar;

import com.napas.paytouch.R;

public class CommonUtil {

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.dialog_progress);
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        ProgressBar progressBar = progressDialog.findViewById(R.id.progress_bar);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}
