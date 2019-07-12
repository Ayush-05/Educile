package myeducile.project.ayush.tutor;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import java.util.*;

public class Myphone extends Application {


    public static Context context;
    static final String BACKENDLESS_HOST = "https://api.backendless.com";
    static final String SERVICE_NAME = "myphone";
    static final String APP_ID = "A1A435B7-ADD6-8037-FF2F-CC890EF53D00";
    static final String API_KEY = "541FF278-6BD1-0C7B-FFEA-86738D23F600";

    private static Myphone ourInstance = new Myphone();

    private Myphone(  )
    {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Backendless.setUrl( MyDefaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );

    }

    public static Myphone getInstance()
    {
        return ourInstance;
    }

    public static void initApplication()
    {
        Backendless.setUrl( Myphone.BACKENDLESS_HOST );
        // if you invoke this sample inside of android application, you should use overloaded "initApp" with "context" argument
        //Backendless.initApp( Myphone.APP_ID, Myphone.API_KEY );
        Backendless.initApp(Myphone.APP_ID, Myphone.API_KEY );


    }




    public java.lang.Object fghj(float phone)
    {
        Object[] args = new Object[]{phone};
        return Backendless.CustomService.invoke( SERVICE_NAME, "fghj", args, java.lang.Object.class );
    }

    public void fghjAsync(float phone, AsyncCallback<java.lang.Object> callback)
    {
        Object[] args = new Object[]{phone};
        Backendless.CustomService.invoke( SERVICE_NAME, "fghj", args, java.lang.Object.class, callback);
    }



}
