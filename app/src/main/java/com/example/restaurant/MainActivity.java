package com.example.restaurant;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button bPizza, bCombo, bDeserts, bDrinks;
    ArrayList<Products> products = new ArrayList<Products>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bPizza = findViewById(R.id.button_pizza);
        bCombo = findViewById(R.id.button_combo);
        bDeserts = findViewById(R.id.button_deserts);
        bDrinks = findViewById(R.id.button_drinks);

        bPizza.setBackgroundColor(Color.parseColor("#33FD3A69"));
        bPizza.setTextColor(Color.parseColor("#FD3A69"));

        bPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bPizza.setBackgroundColor(Color.parseColor("#33FD3A69"));
                bCombo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDeserts.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDrinks.setBackgroundColor(Color.parseColor("#FFFFFF"));

                bPizza.setTextColor(Color.parseColor("#FD3A69"));
                bCombo.setTextColor(Color.parseColor("#C3C4C9"));
                bDeserts.setTextColor(Color.parseColor("#C3C4C9"));
                bDrinks.setTextColor(Color.parseColor("#C3C4C9"));
            }
        });
        bCombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bCombo.setBackgroundColor(Color.parseColor("#33FD3A69"));
                bPizza.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDeserts.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDrinks.setBackgroundColor(Color.parseColor("#FFFFFF"));

                bCombo.setTextColor(Color.parseColor("#FD3A69"));
                bPizza.setTextColor(Color.parseColor("#C3C4C9"));
                bDeserts.setTextColor(Color.parseColor("#C3C4C9"));
                bDrinks.setTextColor(Color.parseColor("#C3C4C9"));
            }
        });
        bDeserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bDeserts.setBackgroundColor(Color.parseColor("#33FD3A69"));
                bCombo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bPizza.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDrinks.setBackgroundColor(Color.parseColor("#FFFFFF"));

                bDeserts.setTextColor(Color.parseColor("#FD3A69"));
                bCombo.setTextColor(Color.parseColor("#C3C4C9"));
                bPizza.setTextColor(Color.parseColor("#C3C4C9"));
                bDrinks.setTextColor(Color.parseColor("#C3C4C9"));
            }
        });
        bDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bDrinks.setBackgroundColor(Color.parseColor("#33FD3A69"));
                bCombo.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bDeserts.setBackgroundColor(Color.parseColor("#FFFFFF"));
                bPizza.setBackgroundColor(Color.parseColor("#FFFFFF"));

                bDrinks.setTextColor(Color.parseColor("#FD3A69"));
                bCombo.setTextColor(Color.parseColor("#C3C4C9"));
                bDeserts.setTextColor(Color.parseColor("#C3C4C9"));
                bPizza.setTextColor(Color.parseColor("#C3C4C9"));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.list);

        Request request = new Request.Builder()
                .url("https://api.spoonacular.com/food/menuItems/search?apiKey=750907d7f5bf4933b4bde4a525f22069&query=pizza&number=10")
                .build();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {

                assert response.body() != null;
                final String responseData = response.body().string();


                MainActivity.this.runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        JSONObject Jobject = null;
                        JSONArray Jarray = null;
                        try {
                            Jobject = new JSONObject(responseData);
                            Jarray = Jobject.getJSONArray("menuItems");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < Jarray.length(); i++) {
                            try {
                                JSONObject object = Jarray.getJSONObject(i);
                                String price = (int)(Math.random()*1000) + "";
                                String name = object.getString("title");
                                String description = object.getString("restaurantChain");
                                String imageUrl = object.getString("image");
                                products.add(new Products(name, "Ресторан: " + description, price, imageUrl));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        RecyclerView recyclerView = findViewById(R.id.list);

                        ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, products);
                        recyclerView.setAdapter(adapter);
                    }

                });
            }
        });
    }
}