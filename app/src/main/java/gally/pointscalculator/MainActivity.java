package gally.pointscalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final int[] higher = {100, 88, 77, 66, 56, 46, 37, 0};
    final int[] ordinary = {56, 46, 37, 28, 20, 12, 0, 0};
    int[] user_six = {1, 4, 776, 34, 2 ,71, 6};
    int points = 0;
    int subjects_entered = 0;
    int previous = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update();
        Log.e("HPoints", Integer.toString(points));
    }

    public void update()
    {
        String test = Integer.toString(higher[2]);
        Log.d("swag", test);
        calculate(21);
    }

    public void calculate(int value)
    {
        user_six[subjects_entered] = value;
        previous = value;
        subjects_entered++;
        sort();
        for (int i = 0; i < 6; i++)
        {
            points += user_six[i];
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

    public void reset()
    {
        user_six = null;
        points = 0;
        subjects_entered = 0;
        previous = 0;
    }


}
