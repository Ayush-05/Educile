package myeducile.project.ayush.tutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserIdStorageFactory;

import java.util.HashMap;
import java.util.Map;

public class MyFinalPage extends AppCompatActivity {

   TextView tv1,tv2,tv3,tv4;
   Button bt1;
   String comments,students;
   ProgressDialog progressDialog=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_final_page);
        tv1=(TextView)findViewById(R.id.textView11);
        tv2=(TextView)findViewById(R.id.textView12);
        tv3=(TextView)findViewById(R.id.textView13);
        tv4=(TextView)findViewById(R.id.textView14);
        bt1=(Button)findViewById(R.id.button7);
        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);
        final String userId = UserIdStorageFactory.instance().getStorage().get();


        SharedPreferences preferences = getSharedPreferences("default", MODE_PRIVATE);

        final String text1= preferences.getString("text1"," ");
        final String text2= preferences.getString("text2"," ");
        final String text3= preferences.getString("text3"," ");
        final String text4= preferences.getString("text4"," ");
        final String text5= preferences.getString("text5"," ");
        final String text6= preferences.getString("text6"," ");




        SharedPreferences preferences1 = getSharedPreferences("default2", MODE_PRIVATE);

        final String text12= preferences1.getString("text12"," ");
        String text13= preferences1.getString("mycost"," ");

        SharedPreferences preferences12 = getSharedPreferences("default6", MODE_PRIVATE);
        comments=preferences12.getString("mycomments"," ");

        SharedPreferences preferences121 = getSharedPreferences("default21", MODE_PRIVATE);
        students=preferences121.getString("Students"," ");



        tv2.setText(text12);
        tv4.setText(students);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MyFinalPage.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Confirming your order,please wait");
                progressDialog.show();

                final HashMap h1=new HashMap<>();
                h1.put("Name",text1);
                h1.put("Phone",text2);
                h1.put("Address",text3);
                h1.put("City",text4);
                h1.put("Pincode",text5);
                h1.put("Date_time",text6);
                h1.put("Additional_Comments",comments);
                h1.put("Chapter",text12);
                h1.put("User_id",userId);
                h1.put("Students",students);




                //final ProgressDialog progressDialog33=ProgressDialog.show(getApplicationContext(),"Loading","Please wait");

                Backendless.Persistence.of("Mybookings").save(h1, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Toast.makeText(MyFinalPage.this,"Successful",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        Intent io=new Intent(MyFinalPage.this,MyConfirmedPage.class);
                        startActivity(io);
                         String userId = UserIdStorageFactory.instance().getStorage().get();
                        Backendless.UserService.findById(userId, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                h1.put("Relatedusers",response.getEmail());


                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(MyFinalPage.this,"Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }
}
