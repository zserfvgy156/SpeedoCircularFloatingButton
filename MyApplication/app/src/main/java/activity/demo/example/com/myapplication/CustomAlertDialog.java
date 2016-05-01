package activity.demo.example.com.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by user on 2016/5/1.
 */
public class CustomAlertDialog {

    Activity GetActivity = null;
    //get mainActivity to dialog object
    public CustomAlertDialog(Activity GetActivity){
        this.GetActivity = GetActivity;
    }

    Dialog GetAlertDialog(View speedometerView){
        Dialog dialog = new Dialog(GetActivity);
        // it remove the dialog title
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogNoAnimation;
        // set the laytout in the dialog
        dialog.setContentView(speedometerView);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        // set the background partial transparent
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        // it dismiss the dialog when click outside the dialog frame
        dialog.setCanceledOnTouchOutside(false);
        // it show the dialog box
        dialog.show();
        return dialog;
    }
}
