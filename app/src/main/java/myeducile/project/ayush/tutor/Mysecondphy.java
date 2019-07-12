package myeducile.project.ayush.tutor;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
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

public class Mysecondphy extends AppCompatActivity {

    List<Mypage1> productList;
    String URL_PRODUCTS = "https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/retrieves/fgh?subject=physics";

    RecyclerView recyclerView;
    EditText editText;
    Myadapter1 adapter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysecondphy);
        editText = (EditText) findViewById(R.id.editTextSearch);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        tv=(TextView)findViewById(R.id.textView8);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //after the change calling the method and passing the search input
                filter(s.toString());
            }
        });

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);

        //initializing the productlist
        productList = new ArrayList<>();
        loadProducts();


        //adding some items to our list
        /*productList.add(
                new Mypage1(
                        "1",
                        "Sets,Relations And Functions",
                        "gdsghfdhffdhfhdhhhfhdfhhshdhdhdfhhffddhhhfdfhfdhfdhd" +
                                "dfhdhdhddhddhdhfhdhfdhdddhfdffhhdf",
                        "ADDONS:Boards,Multiple","08:00-20:00","1daypackage",
                        R.drawable.macbook,R.drawable.macbook,"Rs 500"
                        ));

        productList.add(
                new Mypage1(
                        "1",
                        "Sets,Relations And Functions",
                        "gdsghfdhffdhfhdhhhfhdfhhshdhdhdfhhffddhhhfdfhfdhfdhd" +
                                "dfhdhdhddhddhdhfhdhfdhdddhfdffhhdf",
                        "ADDONS:Boards,Multiple","08:00-20:00","1daypackage",
                        R.drawable.macbook,R.drawable.macbook,"Rs 500"
                ));
        productList.add(
                new Mypage1(
                        "1",
                        "Sets,Relations And Functions",
                        "gdsghfdhffdhfhdhhhfhdfhhshdhdhdfhhffddhhhfdfhfdhfdhd" +
                                "dfhdhdhddhddhdhfhdhfdhdddhfdffhhdf",
                        "ADDONS:Boards,Multiple","08:00-20:00","1daypackage",
                        R.drawable.macbook,R.drawable.macbook,"Rs 500"
                ));
        productList.add(
                new Mypage1(
                        "1",
                        "Sets,Relations And Functions",
                        "gdsghfdhffdhfhdhhhfhdfhhshdhdhdfhhffddhhhfdfhfdhfdhd" +
                                "dfhdhdhddhddhdhfhdhfdhdddhfdffhhdf",
                        "ADDONS:Boards,Multiple","08:00-20:00","1daypackage",
                        R.drawable.macbook,R.drawable.macbook,"Rs 500"
                ));


        //creating recyclerview adapter
        adapter = new Myadapter1(this, productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);*/
    }

    private void filter(String text) {
        //new array list that will hold the filtered data
        ArrayList<Mypage1> filterdNames = new ArrayList<>();

        //looping through existing elements
        for (Mypage1 s : productList) {
            //if the existing elements contains the search input
            if (s.getTitle().toLowerCase().contains(text)) {
                //adding the element to filtered list
                filterdNames.add(s);
            }
        }

        //calling a method of the adapter class and passing the filtered list
        adapter.filterList(filterdNames);
    }

    private void loadProducts() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object

                            JSONArray array1 = new JSONArray(response);
                            tv.setText("");

                            //traversing through all the object
                            for (int i = 0; i < array1.length(); i++) {

                                //getting product object from json array
                                JSONObject product1 = array1.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Mypage1(

                                        product1.getString("Id"),
                                        product1.getString("Chapter_name"),
                                        product1.getString("Description"),
                                        "ADDONS:Entrace,Boards,Multiple", "08:00-20:00","Number of classes may vary according to contents of chapter.",
                                        R.drawable.ww1, R.drawable.ww2, product1.getString("Cost")
                                ));

                            }

                            //creating adapter object and setting it to recyclerview
                            adapter = new Myadapter1(Mysecondphy.this, productList);

                            //setting adapter to recyclerview
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Mysecondphy.this, "Check your connection", Toast.LENGTH_LONG).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);


    }
}
