package tec.calories.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tec.calories.app.Api.ApiManager;
import tec.calories.app.models.Food;

public class CalorieTracker extends AppCompatActivity {
    Food f = new Food();

    List<Food> foodList;
    String URL = "http://192.168.0.79:8080/Food";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorietracker);
        Intent caloriePage = getIntent();
        int dailyCalories = caloriePage.getIntExtra(getString(R.string.dailyCaloriesVal), 0);
        TextView dailyCaloriesTxt = findViewById(R.id.dailyCaloriesTxt);
        dailyCaloriesTxt.setText("Daily Calories: " + String.valueOf(dailyCalories));

//        TextView test = findViewById(R.id.test);
        String URL = "http://192.168.0.79:8080/Food";

        okhttp3.Request request = new Request.Builder().url(URL).get().build();
        OkHttpClient client = new OkHttpClient();

        Call newcall = client.newCall(request);

        Spinner spinner = findViewById(R.id.foodList);

        newcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code() == 200){
                    String responseData = response.body().string();
                    Gson gson = new Gson();
                    Type foodListType = new TypeToken<List<Food>>(){}.getType();


                    foodList = gson.fromJson(responseData, foodListType);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Create a custom ArrayAdapter to display only the names
                            ArrayAdapter<Food> adapter = new ArrayAdapter<Food>(CalorieTracker.this, android.R.layout.simple_spinner_item, foodList) {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View view = super.getView(position, convertView, parent);
                                    // Set the text of the spinner item to the name of the food item
                                    TextView textView = view.findViewById(android.R.id.text1);
                                    textView.setText(foodList.get(position).getName());
                                    return view;
                                }

                                @Override
                                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                    View view = super.getDropDownView(position, convertView, parent);
                                    // Set the text of the dropdown item to the name of the food item
                                    TextView textView = view.findViewById(android.R.id.text1);
                                    textView.setText(foodList.get(position).getName());
                                    return view;
                                }
                            };
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                        }
                    });


                }
            }
        });




}}