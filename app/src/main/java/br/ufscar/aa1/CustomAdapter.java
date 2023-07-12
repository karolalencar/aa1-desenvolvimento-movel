package br.ufscar.aa1;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private final List<Product> products;
    private final Activity activity;

    public CustomAdapter(List<Product> products, Activity activity) {
        this.products = products;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.row_product, parent, false);
        Product product = products.get(position);

        TextView productName = (TextView) view.findViewById(R.id.txtProductName);
        TextView productDescription = (TextView) view.findViewById(R.id.txtProductDescription);
        //EditText productAmount = (EditText) view.findViewById(R.id.edtTxtProductAmount);

        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        //productAmount.setText(product.getAmount());

        return view;
    }
}
