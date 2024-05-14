package tec.calories.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import tec.calories.app.models.Food;

public class CalorieTracker extends AppCompatActivity {
    Food f;
    private String URL = "http://192.168.0.200:8080/Food/1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorietracker);
        Intent caloriePage = getIntent();
        int dailyCalories = caloriePage.getIntExtra(getString(R.string.dailyCaloriesVal), 0);

//        TextView test = findViewById(R.id.test);

        getFoods();
    }

    public void getFoods() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, "http://192.168.0.200:8080/Food/1", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                f = new Gson().fromJson(s, Food.class);
//                TextView test = findViewById(R.id.test);
//                test.setText(f.getName());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }
        );
        queue.add(request);
    }
}