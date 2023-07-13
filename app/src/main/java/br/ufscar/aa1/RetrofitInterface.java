package br.ufscar.aa1;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    @PUT("/products")
    Call<AddProductResult> addProduct(@Body HashMap<String, String> map);

    @GET("/products")
    Call<List<AddProductResult>> getProducts();

    @PATCH("/products/{id}")
    Call<Void> changeProductAmount(@Body HashMap<String, String> map);
}
