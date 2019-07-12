package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Myfirstpage extends AppCompatActivity {

    private static final String URL_PRODUCTS = "https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/Subjects/mySubjects";

    //a list to store all the products
    List<Product> productList;
    private static long back_pressed;

    //the recyclerview
    RecyclerView recyclerView;
    ProgressBar pb;
    Intent i22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfirstpage);
        recyclerView = findViewById(R.id.recyclerView11);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pb=(ProgressBar)findViewById(R.id.pb1);
        //initializing the productlist
        productList = new ArrayList<>();
        android.support.v7.app.ActionBar actionBar= getSupportActionBar();


        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );



        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.contact: i22=new Intent(Myfirstpage.this,MyContactUs.class);
                                startActivity(i22);
                                break;
            case R.id.About_Educile1: i22=new Intent(Myfirstpage.this,MyTnC.class);
                startActivity(i22);
                break;
            case R.id.Call: i22=new Intent(Myfirstpage.this,MyOnCall.class);
                startActivity(i22);
                break;
            case R.id.faqs: i22=new Intent(Myfirstpage.this,MyFAQs.class);
                startActivity(i22);
                break;
            case R.id.mybookings: i22=new Intent(Myfirstpage.this,MyBookings.class);
                startActivity(i22);
                break;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (back_pressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit",
                    Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();

        }
    }



    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
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
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Product(

                                        product.getString("Title"),
                                        product.getString("Image")
                                ));

                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(Myfirstpage.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Myfirstpage.this,"Check your connection",Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);



    }

}

