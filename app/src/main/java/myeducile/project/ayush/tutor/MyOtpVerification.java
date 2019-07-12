package myeducile.project.ayush.tutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class
MyOtpVerification extends AppCompatActivity {

    EditText tv;
    Button bt;
    String authkey = "181007AeDbQUPn859f2f2a7";
    String phoneotp,myreceivedotp;
    //Multiple mobiles numbers separated by comma

    //Sender ID,While using route4 sender id should be 6 characters long.
    String senderId = "SENDOTP";
    //Your message to send, Add URL encoding here.
    //define route
    String route="default";
    String mainUrl;
    String senderID="98111536481";
    ProgressDialog progressDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_otp_verification);
        tv=(EditText) findViewById(R.id.editText15);
        bt=(Button)findViewById(R.id.button8);
       final String otpverify=tv.getText().toString();
        SharedPreferences preferences = getSharedPreferences("default3", MODE_PRIVATE);

        phoneotp= preferences.getString("otp"," ");
        Backendless.setUrl(MyDefaults.SERVER_URL);
        Backendless.initApp(getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);





        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MyOtpVerification.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Verifying OTP");
                progressDialog.show();

                    myreceivedotp=tv.getText().toString();

                    Loaddata task = new Loaddata();
                    task.execute();



            }
        });
    }

    public class Loaddata extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {


            mainUrl="https://control.msg91.com/api/verifyRequestOTP.php?authkey=181007AeDbQUPn859f2f2a7&mobile="+phoneotp+"&otp="+myreceivedotp;

            StringRequest stringRequest1=new StringRequest(Request.Method.POST, mainUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        progressDialog.dismiss();


                        android.util.Log.e("Main url is: ", mainUrl);
                        JSONObject jsonObject=new JSONObject(response);

                        String hh=jsonObject.getString("message");



                            if (hh.equalsIgnoreCase("otp_verified")) {
                                Toast.makeText(MyOtpVerification.this, "Verified", Toast.LENGTH_LONG).show();
                                Backendless.Messaging.registerDevice(senderID, new AsyncCallback<Void>() {
                                    @Override
                                    public void handleResponse(Void response) {

                                        //Toast.makeText(getApplicationContext(), "Device Registered", Toast.LENGTH_LONG).show();

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {

                                       // Toast.makeText(getApplicationContext(), fault.getMessage(), Toast.LENGTH_LONG).show();


                                    }
                                });

                                Intent it = new Intent(MyOtpVerification.this, MyLogin.class);
                                startActivity(it);
                            } else {
                                Toast.makeText(MyOtpVerification.this, "OTP is wrong", Toast.LENGTH_LONG).show();
                            }


                            //adding the product to product list




                        //creating adapter object and setting it to recyclerview

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MyOtpVerification.this,"Error",Toast.LENGTH_LONG).show();
                        }



                    });
            Volley.newRequestQueue(getApplicationContext()).add(stringRequest1);


            return null;
        }
    }

}
