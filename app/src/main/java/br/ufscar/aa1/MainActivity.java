package br.ufscar.aa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        EditText edtTxtSearch = findViewById(R.id.edtTxtSearch);

        ListView listViewProducts = findViewById(R.id.productsList);

        FloatingActionButton fabAddProduct = findViewById(R.id.fabAddProduct);
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SegundaTela.class);
                startActivity(intent);
            }
        });

        int desiredColor = ContextCompat.getColor(this, R.color.darkGreen);
        fabAddProduct.setImageTintList(ColorStateList.valueOf(desiredColor));

        fetchProducts();
    }

    private void fetchProducts() {
        Call<List<AddProductResult>> call = retrofitInterface.getProducts();

        call.enqueue(new Callback<List<AddProductResult>>() {
            @Override
            public void onResponse(Call<List<AddProductResult>> call, Response<List<AddProductResult>> response) {
                if (response.isSuccessful()) {
                    List<AddProductResult> productsList = response.body();

                    CustomAdapter adapter = new CustomAdapter(productsList, MainActivity.this);
                    ListView listViewProducts = findViewById(R.id.productsList);
                    listViewProducts.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Error in getting products", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<AddProductResult>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
