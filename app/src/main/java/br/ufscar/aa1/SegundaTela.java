package br.ufscar.aa1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SegundaTela extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "http://10.0.2.2:5000/products/";

    private EditText editTextName;
    private EditText editTextCode;
    private EditText editTextQuantity;
    private EditText editTextDescription;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        editTextName = findViewById(R.id.editTextName);
        editTextCode = findViewById(R.id.editTextCode);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> map = new HashMap<>();
                System.out.println(editTextCode.getText().toString());
                map.put("name", editTextName.getText().toString());
                map.put("code", editTextCode.getText().toString());
                map.put("amount", editTextQuantity.getText().toString());
                map.put("description", editTextDescription.getText().toString());

                Call<AddProductResult> call = retrofitInterface.addProduct(map);
                call.enqueue(new Callback<AddProductResult>() {
                    @Override
                    public void onResponse(Call<AddProductResult> call, Response<AddProductResult> response) {
                        if (response.code() == 200) {
                            Toast.makeText(SegundaTela.this, "Product added successfully.", Toast.LENGTH_LONG).show();
                            setResult(RESULT_OK);
                        } else if (response.code() == 400) {
                            Toast.makeText(SegundaTela.this, "Not added successfully.", Toast.LENGTH_LONG).show();
                        } else {
                            System.out.println(response.code());
                            Toast.makeText(SegundaTela.this, "", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddProductResult> call, Throwable t) {
                        Toast.makeText(SegundaTela.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
                finish();
            }
        });
    }
}
