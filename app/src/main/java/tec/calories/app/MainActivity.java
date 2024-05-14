package tec.calories.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tec.calories.app.models.Activity_level;

// Eksempel på brug i din aktivitet eller fragment
public class MainActivity extends AppCompatActivity {
    EditText weight, height, age;
    RadioGroup goal;
    RadioButton goal_loss, goal_main, goal_gain;
    Button submit;
    Spinner activitySpinner;
    ActivitySpinnerAdapter spinnerAdapter;
    List<Activity_level> activityLevels;
    Double activity_mul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        Integer calorieGoal = sharedPref.getInt(getString(R.string.calories),0);
        if (calorieGoal != 0) redirectToCaloriePage(calorieGoal);

        // Initialiser komponenter i app
        weight = findViewById(R.id.input_weight);
        height = findViewById(R.id.input_height);
        age = findViewById(R.id.input_age);

        goal = findViewById(R.id.goal);

        activitySpinner = findViewById(R.id.activity_spinner);
        activityLevels = fetchActivityLevels(); // Metode til at hente aktivitetsniveauer

        spinnerAdapter = new ActivitySpinnerAdapter(this, activityLevels);
        activitySpinner.setAdapter(spinnerAdapter);

        // Lytter til valg af element i Spinner'en
        activitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Få det valgte Activity_level-objekt
                Activity_level selectedActivityLevel = spinnerAdapter.getSelectedActivityLevel(position);

                if (selectedActivityLevel != null) {
                    // Brug selectedActivityLevel.getMultiplier() til beregninger eller handlinger efter behov
                    activity_mul = selectedActivityLevel.getMultiplier();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        submit = findViewById(R.id.settings_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weightV = Double.parseDouble(weight.getText().toString());
                double heightV = Double.parseDouble(height.getText().toString());
                int ageV = Integer.parseInt(age.getText().toString());

                // Find det valgte mål fra RadioGroup
                RadioButton selectedGoalButton = findViewById(goal.getCheckedRadioButtonId());
                String selectedGoal = selectedGoalButton.getText().toString();

                // Beregn det daglige kaloriebehov baseret på brugerens input og aktivitetsniveau
                double dailyCalories = calculateDailyCalories(weightV, heightV, ageV);

                // Juster kaloriebehovet baseret på målet
                if (selectedGoal.equals("Loose weight")) {
                    dailyCalories -= 500; // Træk 500 kalorier for at tabe sig
                } else if (selectedGoal.equals("Gain weight")) {
                    dailyCalories += 500; // Tilføj 500 kalorier for at tage på
                }

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(getString(R.string.calories), (int) dailyCalories);
                editor.apply();
                // Vis resultatet (for demonstration)
                redirectToCaloriePage((int)dailyCalories);
            }
        });
    }

    // Metode til at simulere hentning af aktivitetsniveauer (kan erstattes med din logik)
    private List<Activity_level> fetchActivityLevels() {
        List<Activity_level> levels = new ArrayList<>();
        levels.add(new Activity_level("Sedentary", 1.2));
        levels.add(new Activity_level("Lightly Active", 1.375));
        levels.add(new Activity_level("Moderately Active", 1.55));
        levels.add(new Activity_level("Very Active", 1.725));
        levels.add(new Activity_level("Extremely Active", 1.9));
        return levels;
    }

    private double calculateDailyCalories(double weight, double height, int age) {
        double bmr = 10 * weight + 6.25 * height - 5 * age; // Beregn basalmetabolisme (BMR)
        return bmr * activity_mul;
    }

    private void redirectToCaloriePage(int calorieGoal){
        Intent caloriePage = new Intent(this, CalorieTracker.class);
        caloriePage.putExtra("getString(R.string.dailyCaloriesVal)", calorieGoal);
        startActivity(caloriePage);
    }
}
