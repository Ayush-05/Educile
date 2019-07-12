package myeducile.project.ayush.tutor;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Myadapter3 extends RecyclerView.Adapter<Myadapter3.ProductViewHolder> {
    private Context mCtx;
    Intent it;
    public static final String sgd="";
    public static final String sgd1="";
    //we are storing all the products in a list
    private List<Mybukings> productList;
    Context cc;

    //getting the context and product list with constructor
    public Myadapter3(Context mCtx, List<Mybukings> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.mybukings, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        //getting the product of the specified position
        Mybukings product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getDesc());
        holder.textViewRating.setText(product.getName());
        }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating;
        EditText ed;
        Button bt;


        public ProductViewHolder(final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textView23);
            textViewShortDesc = itemView.findViewById(R.id.textView24);
            textViewRating = itemView.findViewById(R.id.textView25);
            ed=itemView.findViewById(R.id.editText12);
            bt=itemView.findViewById(R.id.button9);
            cc=itemView.getContext();
            Backendless.setUrl( MyDefaults.SERVER_URL );
            Backendless.initApp( cc, MyDefaults.APPLICATION_ID, MyDefaults.API_KEY);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Mybukings mybukings=productList.get(getAdapterPosition());
                    String feedback=ed.getText().toString();
                    HashMap hashMap=new HashMap<>();
                    hashMap.put("Chapter",mybukings.getName());
                    hashMap.put("Date_Time",mybukings.getDesc());
                    hashMap.put("Tutor",mybukings.getTitle());
                    hashMap.put("Feedback",feedback);

                    Backendless.Persistence.of("Feedbacks").save(hashMap, new AsyncCallback<Map>() {
                        @Override
                        public void handleResponse(Map response) {
                            Toast.makeText(cc,"Thank you for your feedback",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(cc,"Please try again",Toast.LENGTH_LONG).show();
                        }
                    });

                }
            });

        }

    }
    public void filterList(ArrayList<Mybukings> filterdNames) {
        productList = filterdNames;
        notifyDataSetChanged();
    }

}
