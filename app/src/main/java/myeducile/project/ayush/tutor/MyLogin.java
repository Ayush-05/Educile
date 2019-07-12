package myeducile.project.ayush.tutor;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class MyLogin extends AppCompatActivity {

    EditText ed1,ed2,ed3;
    Button bt1,bt2;
    Intent i;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        ed1=(EditText)findViewById(R.id.editText10);
        ed2=(EditText)findViewById(R.id.editText11);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        bt1=(Button)findViewById(R.id.button4);
        bt2=(Button)findViewById(R.id.button5);
        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=ed1.getText().toString();
                String password=ed2.getText().toString();
                progressDialog = new ProgressDialog(MyLogin.this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setMessage("Loading...Please wait");
                progressDialog.show();



                Backendless.UserService.login(username,password, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Login successfull",Toast.LENGTH_LONG).show();
                        Intent it=new Intent(MyLogin.this,Main4Activity.class);
                        startActivity(it);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), fault.getMessage(),Toast.LENGTH_LONG).show();

                    }
                },true);
            }
        });



        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(MyLogin.this,MyRegistration.class);
                startActivity(ii);
            }
        });

       /* if(Backendless.UserService.loggedInUser()=="")
        {
            i=new Intent(MyLogin.this,MyLogin.class);
            startActivity(i);
        }
        else
        {
            i=new Intent(MyLogin.this,Main2Activity.class);
            startActivity(i);
        }*/


    }
}
