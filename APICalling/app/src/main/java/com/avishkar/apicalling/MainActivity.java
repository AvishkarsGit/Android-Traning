package com.avishkar.apicalling;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private String API = "https://fakestoreapi.com/products";

    private ArrayList<ProductsModel> productsList;
    private ProductsAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsList = new ArrayList<>();
        getApiData();

    }

    private void getApiData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0;i<jsonArray.length();i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                ProductsModel model = new ProductsModel(
//                                        jsonObject.getInt("id"),
//                                        jsonObject.getString("title"),
//                                        jsonObject.getString("description"),
//                                        jsonObject.getString("price"),
//                                        jsonObject.getString("category"),
//                                        jsonObject.getString("image")
                                );

                                model.setId(jsonObject.getInt("id"));
                                model.setTitle(jsonObject.getString("title"));
                                model.setDescription(jsonObject.getString("description"));
                                model.setPrice(jsonObject.getString("price"));
                                model.setImage(jsonObject.getString("image"));
                                productsList.add(model);
                            }

                            adapter = new ProductsAdapter(MainActivity.this,productsList);
                            recyclerView.setAdapter(adapter);


                        }catch (Exception e) {
                            Log.d("API", "error:"+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("API", "onError :"+error.getMessage());
                }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}