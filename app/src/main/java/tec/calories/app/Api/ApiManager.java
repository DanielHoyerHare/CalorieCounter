package tec.calories.app.Api;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tec.calories.app.models.Food;

public class ApiManager {

    List<Food> data;


    public List<Food> getItems(){
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        // Call the method to get data
        Call<List<Food>> call = apiService.getData();

        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                if (response.isSuccessful()) {
                    // Handle successful response
                    data = response.body();
                    // Do something with the data
                    Log.d("ApiKald123123", String.valueOf(data.get(0).getName()));
                } else {
                    // Handle error response
                    Log.e("API", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {

            }
        });
        //Log.d("APIKAAALD",data.get(0).name);
        return data;
    }
}
