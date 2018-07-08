package com.example.shubhamkumar.code_for_good;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class center_head_login extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    EditText username, pass;
    private RequestQueue mQueue;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_head_login);
        username = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.newlogin);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login.setOnClickListener(this);
        mQueue = Volley.newRequestQueue(this);
    }

    public void adminLogin() {
        final String userid = username.getText().toString().trim();
        final String password = pass.getText().toString().trim();
        System.out.println(userid);
        System.out.println(password);
        progressDialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.Control_LOGIN + "?password=" + password + "&userid=" + userid, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            if (!response.getBoolean("error"))

                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), showdetails.class);
                                startActivity(i);
                                finish();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error bolck", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userid", userid);
                params.put("password", password);
                return params;
            }
        };
         //RequestHandler.getInstance(this).addToRequestQueue(request);
        mQueue.add(request);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newlogin) {

            String x = username.getText().toString();
            String b = pass.getText().toString();
            if (!x.isEmpty() && !b.isEmpty())
                adminLogin();
            else {
                if (x.isEmpty()) {
                    username.setError("Please enter an id");
                    username.requestFocus();
                }
                if (b.isEmpty()) {
                    pass.setError("Please enter a password");
                    pass.requestFocus();
                }
                return;

            }
        }
    }
}
