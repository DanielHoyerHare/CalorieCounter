package tec.calories.app.Api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tec.calories.app.models.Food;

public interface ApiService {

    @GET("/Food")
    Call<List<Food>> getData();

    @GET("/Food/{id}")
    Call<Food> getDataById(int id);

}
