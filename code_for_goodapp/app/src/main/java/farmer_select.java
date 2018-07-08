package com.example.shubhamkumar.code_for_good;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class farmer_select extends AppCompatActivity {
    public RequestQueue mQueue;

    private LinearLayout tractorLayout;
    private LinearLayout ploughLayout;
    private LinearLayout cutterLayout;
    private LinearLayout loadingLayout;

    AlertDialog alertDialog;
    private TextView tractorSelectDate;
    private TextView ploughSelectDate;
    private TextView cutterSelectDate;
    private Button confirm;
    private Spinner tSpinner;
    private Spinner pSpinner;
    private Spinner cSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_select);
        mQueue = Volley.newRequestQueue(this);
        tractorLayout = findViewById(R.id.tractorLayout);
        ploughLayout = findViewById(R.id.ploughLayout);
        cutterLayout = findViewById(R.id.cutterLayout);
        confirm=findViewById(R.id.button3);
        tractorSelectDate = findViewById(R.id.tractorDateSelected);
        ploughSelectDate = findViewById(R.id.ploughDateSelected);
        cutterSelectDate = findViewById(R.id.cutterDateSelected);
        tSpinner = findViewById(R.id.tractorSpinner);
        pSpinner = findViewById(R.id.ploughSpinner);
        cSpinner = findViewById(R.id.cutterSpinner);
        alertDialog=new AlertDialog.Builder(farmer_select.this).create();
        loadingLayout = findViewById(R.id.loading);
        tractorLayout.setVisibility(View.GONE);
        ploughLayout.setVisibility(View.GONE);
        cutterLayout.setVisibility(View.GONE);
        Intent intent = getIntent();
        String start=intent.getExtras().getString("start_date");
        String end=intent.getExtras().getString("end_date");
        this.makeServerCall(start, end);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(farmer_select.this)
                        .setTitle("Confirm order")
                        .setMessage("Place order?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                               try {
                                   SmsManager sm = SmsManager.getDefault();
                                   sm.sendTextMessage("919731584053", null, "Confirmation message:", null, null);
                                Intent intent1=new Intent(farmer_select.this,ConfirmationPage.class);
                                startActivity(intent1);
                               }catch (Exception e){

                               }
                               }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }

    private Map<String, Equipment> eqMap = new HashMap<>();

    public void makeServerCall(String start, String end) {

        JsonArrayRequest req = new JsonArrayRequest(Constants.EQUIPMENT_DETAILS+"?start_date="+start+"&end_date="+end,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        try {

                            List<String> list1=new ArrayList<>();
                            List<String> list2=new ArrayList<>();
                            List<String> list3=new ArrayList<>();

                            // Parsing json array response
                            // loop through each json object
                            String jsonResponse = "";
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject eq = (JSONObject) response
                                            .get(i);
                                    if(null == eqMap.get(eq.getString("eq_name"))) {
                                        eqMap.put(eq.getString("eq_name"), new Equipment(eq.getString("eq_name"), 0));
                                    }
                                    eqMap.get(eq.getString("eq_name")).setQuantity(1+eqMap.get(eq.getString("eq_name")).getQuantity());

                                }



                            loadingLayout.setVisibility(View.GONE);

                            list1.add("0");
                            list2.add("0");
                            list3.add("0");

                            for(String eqName : eqMap.keySet()) {
                                if(eqName.equals("Pot hole digger")) {
                                    for(int i=0; i < eqMap.get(eqName).getQuantity(); i++) {
                                        list3.add(""+i+1);
                                    }
                                }
                                if(eqName.equals("Fixed plough")) {
                                    for(int i=0; i < eqMap.get(eqName).getQuantity(); i++) {
                                        list2.add(""+i+1);
                                    }
                                }
                                if(eqName.equals("Tractor")) {
                                    for(int i=0; i < eqMap.get(eqName).getQuantity(); i++) {
                                        list1.add(""+i+1);
                                    }
                                }
                                System.out.println(eqName + " :: " + eqMap.get(eqName).getQuantity());
                            }

                            ArrayAdapter<String> dataadapter1=new ArrayAdapter<String>(farmer_select.this,android.R.layout.simple_spinner_dropdown_item,list1);
                            dataadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            tSpinner.setAdapter(dataadapter1);

                            ArrayAdapter<String> dataadapter2=new ArrayAdapter<String>(farmer_select.this,android.R.layout.simple_spinner_dropdown_item,list2);
                            dataadapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            pSpinner.setAdapter(dataadapter2);


                            ArrayAdapter<String> dataadapter3=new ArrayAdapter<String>(farmer_select.this,android.R.layout.simple_spinner_dropdown_item,list3);
                            dataadapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            cSpinner.setAdapter(dataadapter3);

                            if(!eqMap.containsKey("Tractor")) {
                                tractorLayout.setVisibility(View.GONE);
                            } else {
                                tractorLayout.setVisibility(View.VISIBLE);
                            }

                            if(!eqMap.containsKey("Fixed plough")) {
                                ploughLayout.setVisibility(View.GONE);
                            } else {
                                ploughLayout.setVisibility(View.VISIBLE);
                            }

                            if(!eqMap.containsKey("Pot hole digger")) {
                                cutterLayout.setVisibility(View.GONE);
                            } else {
                                cutterLayout.setVisibility(View.VISIBLE);
                            }


                            for(String eqName : eqMap.keySet()) {

                                System.out.println(eqName + " :: " + eqMap.get(eqName).getQuantity());
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(req);
    }

}