package tec.calories.app.Api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tec.calories.app.models.Food;

public class ApiManager {

    List<Food> data;


    public List<Food> getItems(){
        String URL = "http://100.70.102.13:8080/Food";

        Request request = new Request.Builder().url(URL).get().build();
        OkHttpClient client = new OkHttpClient();

        Call newcall = client.newCall(request);

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
                    data = gson.fromJson(responseData, foodListType);
                    for (Food food : data) {
                        System.out.println("APIIIKALD Food: " + food.getName());
                    }
                }
            }
        });
        return data;

    }
}
