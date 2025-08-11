package com.avishkar.jsondata;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
    ArrayList<String> titles;
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

        titles = new ArrayList<>();
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
                        Log.d("api", "response: "+response);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0;i<jsonArray.length();i++) {
                                JSONObject jsonObject =  jsonArray.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                titles.add(title);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("api", "error: "+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                  @Override
                public void onErrorResponse(VolleyError error) {
                      error.printStackTrace();
                      Log.d("api", "onErrorResponse: "+error.getMessage());
                }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}