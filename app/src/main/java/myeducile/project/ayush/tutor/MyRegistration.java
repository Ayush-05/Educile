package myeducile.project.ayush.tutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

import weborb.util.log.Log;

public class MyRegistration extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;
    String authkey = "181007AeDbQUPn859f2f2a7";
    //Multiple mobiles numbers separated by comma

    //Sender ID,While using route4 sender id should be 6 characters long.
    String senderId = "SENDOTP";
    //Your message to send, Add URL encoding here.
    //define route
    String route="default";
    String message="Your verification code is ##OTP##.";
    Button bt1;
    String mainUrl;
    ProgressDialog progressDialog=null;

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_registration);
        ed1=(EditText)findViewById(R.id.editText7);
        ed2=(EditText)findViewById(R.id.editText8);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ed3=(EditText)findViewById(R.id.editText9);
        ed4=(EditText)findViewById(R.id.editText14);
        ed7=(EditText)findViewById(R.id.editText16);
        bt1=(Button)findViewById(R.id.button3);
        ed5=(EditText)findViewById(R.id.editTex);
        ed6=(EditText)findViewById(R.id.editTe);
        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MyRegistration.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Loading...Please wait");
               progressDialog.show();
                String username = ed1.getText().toString();
                String password = ed2.getText().toString();
                String email = ed3.getText().toString();
                String address=ed7.getText().toString();
                String city=ed5.getText().toString();
                String pincode=ed6.getText().toString();
                SharedPreferences preferences12 = getSharedPreferences("default3", MODE_PRIVATE);
                SharedPreferences.Editor editor12 = preferences12.edit();

               String phone12 = ed4.getText().toString();
                editor12.putString("otp",phone12);

                editor12.commit();

                if (username.trim().equalsIgnoreCase("") || password.trim().equalsIgnoreCase("")
                        || email.trim().equalsIgnoreCase("") ||
                        phone12.trim().equalsIgnoreCase("")|| address.trim().equalsIgnoreCase("") ||
                        city.trim().equalsIgnoreCase("") ||
                        pincode.trim().equalsIgnoreCase("") ) {
                    progressDialog.dismiss();
                    Toast.makeText(MyRegistration.this, "Required Field Missing", Toast.LENGTH_LONG).show();
                } else {
                    BackendlessUser backendlessUser = new BackendlessUser();
                    backendlessUser.setProperty("name", username);
                    backendlessUser.setPassword(password);
                   // backendlessUser.setEmail(email);
                    backendlessUser.setProperty("email",email);
                    backendlessUser.setProperty("Phone", phone12);
                    backendlessUser.setProperty("Address", address);
                    backendlessUser.setProperty("City", city);
                    backendlessUser.setProperty("Pincode", pincode);


                    Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>() {
                        @Override
                        public void handleResponse(BackendlessUser response) {

                            // public void setProperty( "Phone", ed4 );
                           /* Intent po=new Intent(MyRegistration.this,MyOtpVerification.class);
                            startActivity(po);*/
                          progressDialog.dismiss();

                            Loaddata task = new Loaddata();
                            task.execute();;
                            Intent po=new Intent(MyRegistration.this,MyOtpVerification.class);
                            startActivity(po);

                            //Toast.makeText(getApplicationContext(), "Registered successfully,Please Confirm your e-mail,then Login", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(getApplicationContext(), "Not registered successfully,Check E-mail", Toast.LENGTH_LONG).show();

                        }
                    });


                }
            }
        });

    }
        public class Loaddata extends AsyncTask<Void,Void,Void>
        {

            @Override
            protected Void doInBackground(Void... voids) {
                String phone=ed4.getText().toString();
                mainUrl="http://control.msg91.com/api/sendotp.php?authkey=181007AeDbQUPn859f2f2a7&sender=OTPSMS&mobile="+phone;

                android.util.Log.e("Main url is: ", mainUrl);
                StringRequest stringRequest1=new StringRequest(Request.Method.POST, mainUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MyRegistration.this,"Error",Toast.LENGTH_LONG).show();
                            }



                        });
                Volley.newRequestQueue(getApplicationContext()).add(stringRequest1);
                return null;
            }
        }

}
