package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.backendless.persistence.local.UserIdStorageFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyBookings extends AppCompatActivity {

    String URL_PRODUCTS12 ="https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/getusersid/idusers";
    List<Mybukings> productList;
    RecyclerView recyclerView;
    ProgressBar pb;
    EditText ed;
    Button button1;
    String tutor,date_time,chapter;
    Intent i22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recyclerView = findViewById(R.id.rvmb);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pb=(ProgressBar)findViewById(R.id.progressBar);
        ed=(EditText)findViewById(R.id.editText12);
        button1=(Button)findViewById(R.id.button9);
        //initializing the productlist
        productList = new ArrayList<>();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();



        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);
        String userId = UserIdStorageFactory.instance().getStorage().get();
        URL_PRODUCTS12=URL_PRODUCTS12+"?s="+userId;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS12,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONArray array = new JSONArray(response);
                            pb.setVisibility(View.GONE);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                final JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Mybukings(

                                        "Topic:" +product.getString("Chapter"),
                                        product.getString("Date_time"),
                                        product.getString("Tutor")
                                ));


                            }

                            //creating adapter object and setting it to recyclerview
                            Myadapter3 adapter = new Myadapter3(MyBookings.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MyBookings.this,"Check your connection",Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);




    }
}
