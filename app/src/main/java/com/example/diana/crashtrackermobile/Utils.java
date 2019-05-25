package com.example.diana.crashtrackermobile;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Utils {
    static Button createPrimaryBtn(Context context, String text) {
        Button btn = new Button(context);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(10, 10,10, 10);

        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius(12);
        shape.setColor(context.getResources().getColor(R.color.btnPrimary));
        btn.setText(text);
        btn.setBackground(shape);
        btn.setTextColor(context.getResources().getColor(R.color.textPrimary));

        btn.setLayoutParams(btnParams);

        return btn;
    }

    static Button createDangerBtn(Context context,String text) {
        Button btn = new Button(context);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(10, 10,10, 10);

        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius(12);
        shape.setColor(context.getResources().getColor(R.color.btnDanger));
        btn.setText(text);
        btn.setBackground(shape);
        btn.setTextColor(context.getResources().getColor(R.color.textDanger));

        btn.setLayoutParams(btnParams);

        return btn;
    }

    static Button createDefaultBtn(Context context,String text) {
        Button btn = new Button(context);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParams.setMargins(10, 10,10, 10);

        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius(12);
        shape.setColor(context.getResources().getColor(R.color.btnDefault));
        btn.setText(text);
        btn.setBackground(shape);
        btn.setTextColor(context.getResources().getColor(R.color.textDefault));

        btn.setLayoutParams(btnParams);

        return btn;
    }
}
