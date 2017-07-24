package gally.pointscalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    final int[] higher = {100, 88, 77, 66, 56, 46, 37, 0};
    final int[] ordinary = {56, 46, 37, 28, 20, 12, 0, 0};
    int[] user_six = {0, 0, 0, 0, 0, 0, 0};
    int points = 0;
    int subjects_entered = 0;
    int previous = 0;
    TextView points_view;
    TextView subjects_view;
    boolean undo_lock = false; //if true undo can be executed
    int temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        update();
//        Log.e("HPoints", Integer.toString(points));
    }

    public void setViews() {

        points_view = (TextView) findViewById(R.id.Points);
        points_view.setText(Integer.toString(points));

        subjects_view = (TextView) findViewById(R.id.subjects);
        subjects_view.setText("Number of Subjects entered: " + Integer.toString(subjects_entered));
    }

//    public void update()
//    {
//        calculate(21);
//    }

    public void calculate(int value)
    {
        undo_lock = false;
        if(user_six[5] < value && subjects_entered < 6) {

            temp = user_six[5];
            user_six[subjects_entered] = value;
            previous = value;
            subjects_entered++;
            sort();
            points = 0;

            for (int i = 0; i < 6; i++)
            {
                points += user_six[i];
            }
        }
        else if(user_six[5] < value){

            temp = user_six[5];
            user_six[5] = value;
            previous = value;
            subjects_entered++;
            sort();
            points = 0;

            for (int i = 0; i < 6; i++)
            {
                points += user_six[i];
            }
        }
        else {
            subjects_entered++; //to ensure subjects entered counter continues increasing
        }
    }

    public void sort()
    {
        int temp;
        if (user_six == null)
        {
            return;
        }
        else
        {
            for (int i = 1; i < user_six.length; i++)
            {
                for (int j = 0; j < user_six.length - 1; j++)
                if (user_six[j] < user_six[j + 1])
                {
                    temp = user_six[j];
                    user_six[j] = user_six[j + 1];
                    user_six[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < 6; i ++) {
            Log.e("users six", Integer.toString(user_six[i]));
        }
    }

    public void reset_func()
    {
        Arrays.fill(user_six, 0);
        points = 0;
        subjects_entered = 0;
        previous = 0;
    }

    public void undo_func()
    {
        if (subjects_entered > 0 && undo_lock == false) {
            if (previous > user_six[5]) {
                points -= previous;
            }
            subjects_entered--;
            previous = 0;
            undo_lock = true;
        }
        if (subjects_entered > 6 && undo_lock == false && previous > temp)
        {
            if (previous > user_six[5]) {
                points -= previous;
                points += temp;
            }
            subjects_entered--;
            previous = 0;
            undo_lock = true;
        }
    }

    public void onButtonTap(View v) {

        switch(v.getId()) {

            case R.id.H1:
                calculate(higher[0]);
                setViews();
                break;
            case R.id.H2:
                calculate(higher[1]);
                setViews();
                break;
            case R.id.H3:
                calculate(higher[2]);
                setViews();
                break;
            case R.id.H4:
                calculate(higher[3]);
                setViews();
                break;
            case R.id.H5:
                calculate(higher[4]);
                setViews();
                break;
            case R.id.H6:
                calculate(higher[5]);
                setViews();
                break;
            case R.id.H7:
                calculate(higher[6]);
                setViews();
                break;
            case R.id.H8:
                calculate(higher[7]);
                setViews();
                break;
            case R.id.O1:
                calculate(ordinary[0]);
                setViews();
                break;
            case R.id.O2:
                calculate(ordinary[1]);
                setViews();
                break;
            case R.id.O3:
                calculate(ordinary[2]);
                setViews();
                break;
            case R.id.O4:
                calculate(ordinary[3]);
                setViews();
                break;
            case R.id.O5:
                calculate(ordinary[4]);
                setViews();
                break;
            case R.id.O6:
                calculate(ordinary[5]);
                setViews();
                break;
            case R.id.O7:
                calculate(ordinary[6]);
                setViews();
                break;
            case R.id.O8:
                calculate(ordinary[7]);
                setViews();
                break;
            case R.id.HL:
                setContentView(R.layout.activity_ordinary);
                setViews();
                break;
            case R.id.OL:
                setContentView(R.layout.activity_main);
                setViews();
                break;
            case R.id.reset:
                reset_func();
                setViews();
                Toast myToast = Toast.makeText(getApplicationContext(), "Reset!", Toast.LENGTH_SHORT);
                myToast.show();
                break;
            case R.id.undo:
                undo_func();
                setViews();
                break;
        }

//        if(v.getId() == R.id.reset) {
//
//            Toast myToast = Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT);
//            myToast.show();
//            reset_func();
//            Log.e("RESET", Integer.toString(points));
//        }
    }
}
