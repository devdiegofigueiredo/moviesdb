package com.project.moviesdb.moviesdb.util;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.moviesdb.moviesdb.R;

public class LoadingUtil {

    public static AlertDialog getDialog(String message, Context context) {
        return createDialog(message, context);
    }

    private static AlertDialog createDialog(String message, Context context) {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.progress_dialog, null, false);


        TextView textMessage = view.findViewById(R.id.message);
        textMessage.setText(message);

        ProgressBar progressBar = view.findViewById(R.id.pb_progress);
        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(context, android.R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY);

        dialog.setView(view);
        dialog.setCancelable(true);
        return dialog;
    }
}
