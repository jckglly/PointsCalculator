/*
Authors: Jack Gallagher & Steve Martin
Date: 26/7/17
Description: A points calculator for the revised points system of the irish leaving cert. Written in java and xml using android studio. Jack and Steve are currently
Computer Science students in UCD and DIT respectively.
*/

package gally.pointscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    final int[] higher = {100, 88, 77, 66, 56, 46, 37, 0};
    final int[] ordinary = {56, 46, 37, 28, 20, 12, 0, 0};

    Integer[] user_six = {0, 0, 0, 0, 0, 0}; // highest 6 subjects values
    Integer[] user_input = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; // input values

    int points = 0;
    int subjects_entered = 0;
    int undo_counter = 0;

    boolean bonus = false;

    TextView points_view;
    TextView subjects_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setViews() {

        points = 0;
        for (int i = 0; i < 6; i++)
        {
            points += user_six[i];
        }

        for (int i = 0; i < 6; i++)
        {
            if((points == higher[i] || points == ordinary[i]) && subjects_entered != 1) {

                subjects_entered = 1;
            }
        }

        if (bonus == true)
        {
            points += 25;
        }

        Log.e("POINTS: ", Integer.toString(points));

        points_view = (TextView) findViewById(R.id.Points);
        points_view.setText(Integer.toString(points));

        subjects_view = (TextView) findViewById(R.id.subjects);
        subjects_view.setText("Number of Subjects entered: " + Integer.toString(subjects_entered));
    }

    public void calculate(int value) {

        for(int i = 0; i < user_input.length-1; i++) {

            user_input[i] = user_input[i+1];
        }
        user_input[user_input.length-1] = value;

        if(user_six[5] < value) {

            user_six[5] = value;
            subjects_entered++;

            Arrays.sort(user_six, Collections.reverseOrder());
        }
        else {
            subjects_entered++; //to ensure subjects entered counter continues increasing
        }
    }

    public void reset_func()
    {
        Arrays.fill(user_six, 0);
        Arrays.fill(user_input, 0);
        points = 0;
        subjects_entered = 0;
        undo_counter = 0;
        bonus = false;
    }

    public void undo_func() {

        try {

            if(undo_counter < 10) {

                if(subjects_entered > 0) {

                    for(int i = 0; i <= user_input.length; i++) {

                        if(user_six[i] == user_input[user_input.length-1]) {

                            user_six[i] = 0;
                            Arrays.sort(user_six, Collections.reverseOrder());
                            break;
                        }
                    }

                    if(user_input[0] == 0) {

                        for (int i = 13; i > 0; i--) {

                            user_input[i] = user_input[i-1];
                        }
                    }
                    else {

                        for (int i = 13; i >= 1; i--) {

                            user_input[i] = user_input[i-1];
                        }
                        user_input[0] = 0;
                    }
                    subjects_entered--;
                }
            }
            else {

                Toast myToast = Toast.makeText(getApplicationContext(), "UNDO limit reached. Please RESET!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        } catch (Exception toasty) {

            Toast myToast = Toast.makeText(getApplicationContext(), "Cannot undo anymore! PLEASE RESET", Toast.LENGTH_SHORT);
            myToast.show();
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
                undo_counter++;
                undo_func();
                setViews();
                break;
            case R.id.HM:
                if(bonus) {

                    Toast bonusToast = Toast.makeText(getApplicationContext(), "Maths Bonus Already Added!", Toast.LENGTH_SHORT);
                    bonusToast.show();
                } else {

                Toast bonusToast = Toast.makeText(getApplicationContext(), "Maths Bonus Added! +25", Toast.LENGTH_SHORT);
                bonusToast.show();
                }
                bonus = true;
                setViews();
                break;

        }
    }
}
