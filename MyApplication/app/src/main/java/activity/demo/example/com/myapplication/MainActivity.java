package activity.demo.example.com.myapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //SpeedoMeterSegments button
    public Button SpeedoMeterSegments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get SpeedoMeterSegments button id
        SpeedoMeterSegments = (Button) findViewById(R.id.main_tor_button);
        SpeedoMeterSegments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpeedoMeterSegments.setVisibility(View.GONE);
                //outside Main button
                View speedometerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.speedometer_segments,
                        null);
                //create dialog
                Dialog dialog = new CustomAlertDialog(MainActivity.this).GetAlertDialog(speedometerView);

                final PromotedActionsLibrary_speed promotedActionsLibrary_speed =
                        new PromotedActionsLibrary_speed(dialog, SpeedoMeterSegments, 6);
                // setting about sub inside buttons
                promotedActionsLibrary_speed.setup(getApplicationContext(),
                        (RelativeLayout) speedometerView.findViewById(R.id.dialog_segments));

                //setting inside buttons onclick function
                View.OnClickListener onClickListener_speed_T0 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to PAS mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(0);
                    }
                };

                View.OnClickListener onClickListener_speed_T1 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to ECO mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(1);
                    }
                };

                View.OnClickListener onClickListener_speed_T2 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to TOUR mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(2);
                    }
                };

                View.OnClickListener onClickListener_speed_T3 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to SPORT mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(3);
                    }
                };

                View.OnClickListener onClickListener_speed_T4 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to SPEED mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(4);
                    }
                };

                View.OnClickListener onClickListener_speed_T5 = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Change to TURBO mode", Toast.LENGTH_SHORT).show();
                        promotedActionsLibrary_speed.setNumber(5);
                    }
                };
                // initialization  inside Segment buttons
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T0, 0);
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T1, 1);
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T2, 2);
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T3, 3);
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T4, 4);
                promotedActionsLibrary_speed.addItem(onClickListener_speed_T5, 5);
                // initialization  outside Main buttons
                promotedActionsLibrary_speed.ini_Number((Integer) view.getTag());
                promotedActionsLibrary_speed.addMainItem();
            }
        });
        // initialization SpeedoMeterButton object  segments
        //SpeedoMeterSegments.setText("PAS\n" + String.valueOf(0));
        //SpeedoMeterSegments.setBackgroundResource(R.drawable.bg_expandable_selector_dark_gray);
        SpeedoMeterSegments.setTag(0);
    }
}
