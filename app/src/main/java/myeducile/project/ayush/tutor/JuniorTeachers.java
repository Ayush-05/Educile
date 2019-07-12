package myeducile.project.ayush.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JuniorTeachers extends AppCompatActivity {

   String URL_PRODUCTSJR = "https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/GEttingTutors/tt";

    List<JnrTeacher> productList21;


    //the recyclerview
    RecyclerView recyclerView;
    Intent i22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junior_teachers);

        recyclerView = findViewById(R.id.rvjnr);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList21 = new ArrayList<>();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();


        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);
        Toast.makeText(JuniorTeachers.this,"Getting Details",Toast.LENGTH_LONG).show();
        loadProducts();
    }

        private void loadProducts() {


            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTSJR,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                //converting the string to json array object

                                JSONArray array = new JSONArray(response);


                                //traversing through all the object
                                for (int i = 0; i < array.length(); i++) {

                                    //getting product object from json array
                                    JSONObject product = array.getJSONObject(i);

                                    //adding the product to product list
                                    productList21.add(new JnrTeacher(

                                            product.getString("Name"),
                                            product.getString("Desc"),
                                            product.getString("Title")
                                    ));

                                }

                                //creating adapter object and setting it to recyclerview
                                Myadapter2 adapter = new Myadapter2(JuniorTeachers.this, productList21);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(JuniorTeachers.this,"Check your connection",Toast.LENGTH_LONG).show();
                        }
                    });

            //adding our stringrequest to queue
            Volley.newRequestQueue(this).add(stringRequest);


        }
}
