package com.example.diana.crashtrackermobile;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        shape.setColor(context.getResources().getColor(R.color.btnDanger));
        btn.setText(text);
        btn.setBackground(shape);
        btn.setTextColor(context.getResources().getColor(R.color.textDefault));

        btn.setLayoutParams(btnParams);

        return btn;
    }

    static TextView getTextView(Context context, String text, boolean highlight) {
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        if (highlight) {
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextColor(context.getResources().getColor(R.color.btnPrimary));
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(0,0,0,15);
        tv.setLayoutParams(params);
        return tv;
    }
}
