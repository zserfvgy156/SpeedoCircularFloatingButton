package activity.demo.example.com.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class PromotedActionsLibrary_speed {
    private Context context;
    private RelativeLayout ParentLayout;        //Dialog's parent contain

    private ObjectAnimator objectAnimator[];     // Animation object array
    private ObjectAnimator objectAnimator_sec[]; 

    public Button Main_button;                // outside main button
    private Button mainImageButton;           // inside main button
    private ArrayList<Button> promotedActions;  // inside sub buttons

    private int Number_Index = 0;              // ini_number to button
    private int px = 0;
    private final int ANIMATION_TIME = 200;
    private boolean isMenuOpened = true;         // open animation enable
    public int sub_item_Button = 6;               //sub buttons total numbers
    private Dialog SpeedometerLevelDialog = null;  // Dialog in background
    private Button MainButton = null;             //show on ui  when alert is disable.
    private boolean SpeedoMeterSegmentsChange = false;

    public PromotedActionsLibrary_speed(Dialog SpeedometerLevelDialog, Button MainButton, int TotalButtons) {
        this.SpeedometerLevelDialog = SpeedometerLevelDialog;
        this.MainButton = MainButton;
        this.sub_item_Button = TotalButtons;
    }
    //set up about object items
    public void setup(Context activityContext, RelativeLayout layout) {
        context = activityContext;
        promotedActions = new ArrayList<Button>();
        ParentLayout = layout;
        //TODO  px value here
        px = (int) context.getResources().getDimension(R.dimen._62sdp) + 18;          
    }
    //setting Main button inside meun
    private void  setup_Main_button_inside(){
        mainImageButton = (Button) LayoutInflater.from(context).inflate(R.layout.main_promoted_action_button_speed_inside,
                ParentLayout, false);
        mainImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Turn_OFF_MainButton(false);
            }
        });
        mainImageButton.setText(String.valueOf(Number_Index));
        ParentLayout.addView(mainImageButton);
        mainImageButton.setVisibility(View.GONE);
    }
    //setting sub button outside meun
    public void addMainItem() {
        Main_button = (Button) LayoutInflater.from(context).inflate(R.layout.main_promoted_action_button_speed,
                ParentLayout, false);

        Main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMenuOpened) {
                    Turn_OFF_MainButton(false);
                }
            }
        });
        change_Main_Button_background(Number_Index, Main_button);
        ParentLayout.addView(Main_button);
        setup_Main_button_inside();
        openPromotedActions().start();
        Main_button.setEnabled(false);
        mainImageButton.setEnabled(false);
    }

    public void addItem(View.OnClickListener onClickListener , int index_Item_number) {
        Button button = (Button) LayoutInflater.from(context).inflate(R.layout.promoted_action_button_speed, ParentLayout, false);
        button.setText(String.valueOf(index_Item_number));
        if(index_Item_number == 0){
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_gray);
        }else if(index_Item_number == 1){
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_blue);
        }else if(index_Item_number == 2){
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_green);
        }else if(index_Item_number == 3){
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_yellow);
        }else if(index_Item_number == 4){
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_org);
        }else{
            button.setBackgroundResource(R.drawable.onivas_fab_circle_speed_red);
        }
        //  0~5   total = 6
        button.setOnClickListener(onClickListener);
        promotedActions.add(button);    //all sub inside  buttons array
        ParentLayout.addView(button);
    }

    /**
     * turn off main_button
     */
    public void Turn_OFF_MainButton(boolean AlphaIndex){
        if (isMenuOpened) {
            closePromotedActions().start();
            isMenuOpened = false;
            Main_button.setEnabled(false);
            mainImageButton.setEnabled(false);
        }
        if(AlphaIndex) MainButton_setAlpha(true);
    }

    /**
     * Set close animation for promoted actions
     */
    public AnimatorSet closePromotedActions() {
        if (objectAnimator == null){  // Animator  array
            objectAnimatorSetup();
        }
        AnimatorSet animation = new AnimatorSet();

        for (int i = 0; i < sub_item_Button; i++) {
            if(Number_Index > i){
                objectAnimator[i] = setCloseAnimation(promotedActions.get(i), i);  // promotedActions is inside of MainButtons
                promotedActions.get(i).setEnabled(false);
            }else if(Number_Index < i){
                objectAnimator[i - 1] = setCloseAnimation(promotedActions.get(i), (i - 1));
                promotedActions.get(i).setEnabled(false);
            }
        }
        if (objectAnimator.length == 0) {
            objectAnimator = null;
        }
        if (objectAnimator_sec.length == 0) {
            objectAnimator_sec = null;
        }
        animation.playTogether(objectAnimator);
        animation.playTogether(objectAnimator_sec);

        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Up_animation();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                hidePromotedActions();
                Main_button.setEnabled(true);
                mainImageButton.setEnabled(true);
                SpeedometerLevelDialog.dismiss();
                MainButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        return animation;
    }

    public AnimatorSet openPromotedActions() {
        if (objectAnimator == null){
            objectAnimatorSetup();
        }

        AnimatorSet animation = new AnimatorSet();

        for (int i = 0; i <  sub_item_Button; i++) {
            if(Number_Index > i){
                objectAnimator[i] = setOpenAnimation(promotedActions.get(i), i);
            }else if(Number_Index < i){
                objectAnimator[i - 1] = setOpenAnimation(promotedActions.get(i), (i - 1));
            }
        }
        if (objectAnimator.length == 0) {
            objectAnimator = null;
        }
        if (objectAnimator_sec.length == 0) {
            objectAnimator_sec = null;
        }
        animation.playTogether(objectAnimator);
        animation.playTogether(objectAnimator_sec);

        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Down_animation();
                showPromotedActions();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Main_button.setEnabled(true);
                mainImageButton.setEnabled(true);
                for (int i = 0; i <  sub_item_Button ; i++) {
                    promotedActions.get(i).setEnabled(true);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
        return animation;
    }
    private void objectAnimatorSetup() {
        int index;
        // if a  SpeedoSegments is 4 or 5,  user change 5 Segment to 3 Segment, it will more one contain
        if(Number_Index > (sub_item_Button - 1)){
            index = sub_item_Button;
            SpeedoMeterSegmentsChange = true;
        }else{
            index = sub_item_Button - 1;
        }
        objectAnimator_sec = new ObjectAnimator[index];
        objectAnimator = new ObjectAnimator[index];
    }
    /**
     * Set close animation for single button
     *
     * @param promotedAction
     * @param position
     * @return objectAnimator
     */
    private ObjectAnimator setCloseAnimation(Button promotedAction, int position) {
        ObjectAnimator objectAnimator_Y_label;
        ObjectAnimator objectAnimator_X_label = null;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {    //�ù�������
            int px_sec = (int) context.getResources().getDimension(R.dimen._57sdp) + 17;
            int px_thr = (int) context.getResources().getDimension(R.dimen._70sdp) + 18;
            int index = position;

            index = ChangeAnimationLocation(index); //while button item is  3

            if(index == 0){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, px_thr , 0f);
            }else if(index == 1) {
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, px_sec, 0f);
            }else if(index == 2){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, 0f, 0f);
            }else if(index == 3){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, -px_sec , 0f );
            }else if(index == 4){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, -px_thr , 0f );
            }

            if(index == 0 || index == 4){
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, 0f, 0f);
            }else if(index == 2){
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, (-px_sec) * 2 ,0f);
            }else{
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, -px, 0f);
            }
            objectAnimator_Y_label.setRepeatCount(0);
            objectAnimator_Y_label.setDuration(ANIMATION_TIME * (sub_item_Button - position));
            objectAnimator_X_label.setRepeatCount(0);
            objectAnimator_X_label.setDuration(ANIMATION_TIME * (sub_item_Button - position));
            objectAnimator_sec[position] = objectAnimator_X_label;
        } else {
            objectAnimator_Y_label= ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, -px * ( sub_item_Button - position), 0f);
            objectAnimator_Y_label.setRepeatCount(0);
            objectAnimator_Y_label.setDuration(ANIMATION_TIME * (sub_item_Button - position));
        }
        return objectAnimator_Y_label;
    }
    /**
     * Set open animation for single butsetOpenAnimationton
     *
     * @param promotedAction
     * @param position
     * @return objectAnimator
     */
    private ObjectAnimator setOpenAnimation(Button promotedAction, int position) {
        ObjectAnimator objectAnimator_Y_label = null;
        ObjectAnimator objectAnimator_X_label = null;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            int px_sec = (int) context.getResources().getDimension(R.dimen._57sdp) + 17;
            int px_thr = (int) context.getResources().getDimension(R.dimen._70sdp) + 18;
            int index = position;

            index = ChangeAnimationLocation(index); //while button item is  3

            if(index == 0){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, 0f , px_thr);
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, 0f, 0f);
            }else if(index == 1) {
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, px_thr, px_sec);
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, 0f, -px);
            }else if(index == 2){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, px_sec, 0f);
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, -px, (-px_sec) * 2);
            }else if(index == 3){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, 0f , -px_sec );
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y,  (-px_sec) * 2 , -px);
            }else if(index == 4){
                objectAnimator_X_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_X, -px_sec , -px_thr );
                objectAnimator_Y_label = ObjectAnimator.ofFloat(promotedAction, View.TRANSLATION_Y, -px, 0f);
            }

            objectAnimator_Y_label.setRepeatCount(0);
            objectAnimator_Y_label.setDuration(ANIMATION_TIME * (sub_item_Button - position));

            objectAnimator_X_label.setRepeatCount(0);
            objectAnimator_X_label.setDuration(ANIMATION_TIME * (sub_item_Button - position));
            objectAnimator_sec[position] = objectAnimator_X_label;
        }
        return objectAnimator_Y_label;
    }

    private int ChangeAnimationLocation(int index){
        if(sub_item_Button == 4 && (!SpeedoMeterSegmentsChange)){   //while button item is  3
            index ++;
        }
        return index;
    }

    private void hidePromotedActions() {
        for (int i = 0; i < sub_item_Button ; i++) {
            promotedActions.get(i).setVisibility(View.GONE);
        }
        mainImageButton.setVisibility(View.GONE);
    }

    private void showPromotedActions() {
        for (int i = 0; i < sub_item_Button; i++) {
            if(Number_Index != i){
                promotedActions.get(i).setVisibility(View.VISIBLE);
            }
        }
        mainImageButton.setVisibility(View.VISIBLE);
    }

    private void Down_animation(){
        ObjectAnimator animation = ObjectAnimator.ofFloat(Main_button, View.TRANSLATION_Y, 0f, (int) context.getResources().getDimension(R.dimen._74sdp));
        AnimationStart(animation);
    }

    private void Up_animation(){
        ObjectAnimator animation = ObjectAnimator.ofFloat(Main_button, View.TRANSLATION_Y, (int) context.getResources().getDimension(R.dimen._74sdp), 0f);
        AnimationStart(animation);
    }

    private void AnimationStart(ObjectAnimator animation){
        animation.setDuration(500);
        animation.start();
        Main_button.bringToFront();
    }

    public void setNumber(int index){
        change_Main_Button_background(index, Main_button);
        change_Main_Button_background(index, MainButton);
        MainButton.setTag(index);
        mainImageButton.setText(String.valueOf(index));
        if (isMenuOpened) {
            Turn_OFF_MainButton(false);
            for (int i = 0; i < sub_item_Button ; i++) {
                promotedActions.get(i).setEnabled(false);
            }
        }
        Number_Index = index;
    }

    public void change_Main_Button_background(int index, Button SelectButton){
        if(index == 0){
            SelectButton.setText("PAS\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_gray);
        }else if(index == 1){
            SelectButton.setText("ECO\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_blue);
        }else if(index == 2){
            SelectButton.setText("TOUR\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_green);
        }else if(index == 3){
            SelectButton.setText("SPORT\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_yellow);
        }else if(index == 4){
            SelectButton.setText("SPEED\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_org);
        }else{
            SelectButton.setText("TURBO\n" + String.valueOf(index));
            SelectButton.setBackgroundResource(R.drawable.bg_expandable_selector_dark_red);
        }
    }
    //setting main button's number
    public void ini_Number(int index){
        Number_Index = index;
    }
    //setting main button's Alpha
    public void MainButton_setAlpha(boolean index){
        if(!index){
            Main_button.setAlpha(1f);
        }else{
            Main_button.setAlpha(0.3f);
        }
    }
}
