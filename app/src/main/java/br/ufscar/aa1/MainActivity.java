package br.ufscar.aa1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edtTxtSearch = findViewById(R.id.edtTxtSearch);
        edtTxtSearch.getText().toString();

        Product product = new Product("Ração", "7797", 15, "Ração de gato");
       Product product2 = new Product("Ração", "7797", 10, "Ração de gato");
       List<Product> products = new ArrayList<>();
       products.add(product);
       products.add(product2);

        ListView productsList = (ListView) findViewById(R.id.productsList);
        //ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products);
        //ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        CustomAdapter adapter2 = new CustomAdapter(products, this);

        productsList.setAdapter(adapter2);

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
    }
}