package tec.calories.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import tec.calories.app.Api.ApiManager;
import tec.calories.app.models.Food;
import tec.calories.app.models.NutritionItem;

public class CalorieTracker extends AppCompatActivity {
    Food f = new Food();

    List<Food> foodList;
    String URL = "http://192.168.0.79:8080/Food";

    ArrayList<NutritionItem> nutritionItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorietracker);
        Intent caloriePage = getIntent();
        int dailyCalories = caloriePage.getIntExtra(getString(R.string.dailyCaloriesVal), 0);

//        TextView test = findViewById(R.id.test);
        foodList = new ArrayList<>();


        ApiManager apiserve = new ApiManager();
        foodList = apiserve.getItems();

        Log.d("ApiKald123123", String.valueOf(foodList.get(0).getName()));


//        getAllFoods();
//        getFoods();
//
//
//        Log.d("FoodAPIKALD", String.valueOf(foodList.get(0).getName()));
//        Spinner foodSpinner = findViewById(R.id.foodList);
//        FoodSpinnerAdapter adapter = new FoodSpinnerAdapter(this, foodList);
//        foodSpinner.setAdapter(adapter);
//    }
//
//    public void getAllFoods() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest request = new StringRequest(Request.Method.GET, "http://192.168.0.79:8080/Food", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                foodList = new Gson().fromJson(s, new TypeToken<ArrayList<Food>>() {}.getType());
////                TextView test = findViewById(R.id.test);
////                test.setText(f.getName());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }
//        );
//        queue.add(request);
//    }
//
//    public void getFoods() {
//        RequestQueue queue = Volley.newRequestQueue(this);
//        StringRequest request = new StringRequest(Request.Method.GET,  "http://192.168.0.79:8080/Food/1", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                f = new Gson().fromJson(s, Food.class);
////                TextView test = findViewById(R.id.test);
////                test.setText(f.getName());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }
//        );
//        queue.add(request);
//    }
}}