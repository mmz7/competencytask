package com.example.competencytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgroup;
    Button submit;
    String movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submitbtn);
        rgroup = findViewById(R.id.radiogroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedid = rgroup.getCheckedRadioButtonId();
                if(selectedid == R.id.betterdays) {
                    movie = "Better Days";
                } else if(selectedid == R.id.upcomingsummer) {
                    movie = "Upcoming Summer";
                } else if(selectedid == R.id.loveo2o) {
                    movie = "Love O2O";
                }
                post(movie);
            }
        });

    }

    public void post(String movie) {
        String url = "https://mzhang-resagain.sites.tjhsst.edu/apppost";
        RequestQueue requestQ = Volley.newRequestQueue(this);

        JSONObject data = new JSONObject();
        try {
            data.put("vote", movie);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQ.add(jsonObjectRequest);
    }
    public void get() {
        String url = "https://mzhang-resagain.sites.tjhsst.edu/appget";
        List<String> jsonResponses = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String email = jsonObject.getString("email");

                        jsonResponses.add(email);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}