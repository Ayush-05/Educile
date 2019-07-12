package myeducile.project.ayush.tutor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.backendless.Backendless;
import com.bumptech.glide.Glide;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>{

    private Context mCtx;
    private static final String URL_PRODUCTS = "https://api.backendless.com/875251C1-5C56-A158-FF59-E1585F146E00/502A4C9A-1E01-B54E-FFA0-BC87ADC18200/services/Subjects/mySubjects";

    Intent it;
    Context cc;
    Integer i;
    public static final String sgd="news_headline";
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.mylayout2, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getTitle());


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle11);

            imageView = itemView.findViewById(R.id.imageView11);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View v) {
                    // get position
                    final Product mypage1=productList.get(getAdapterPosition());
                     int pos = getAdapterPosition();
                    Backendless.setUrl( MyDefaults.SERVER_URL );
                    Backendless.initApp( mCtx, MyDefaults.APPLICATION_ID, MyDefaults.API_KEY );

                    switch(pos)
                    {

                        case 0:
                            it=new Intent(v.getContext(),Mysecondphy.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;

                        case 1:
                            it=new Intent(v.getContext(),Mysecondche.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;

                        case 2:
                            it=new Intent(v.getContext(),Mysecondmath.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;

                        case 3:
                            it=new Intent(v.getContext(),Mysecondbio.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;

                        case 4:
                            it=new Intent(v.getContext(),Mysecondcomp.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;
                        case 5:
                            it=new Intent(v.getContext(),JuniorTeachers.class);
                            //it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;
                        default:
                            it=new Intent(v.getContext(),MysecondtestSeries.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            mCtx.startActivity(it);
                            break;




                    }
                   /* final StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        //converting the string to json array object
                                      //  Toast.makeText(Myfirstpage.this,"GEtting response",Toast.LENGTH_LONG).show();

                                        JSONArray array = new JSONArray(response);

                                        //traversing through all the object
                                        for (int i = 0; i < array.length(); i++) {

                                            //getting product object from json array
                                            JSONObject product = array.getJSONObject(i);

                                            //adding the product to product list
                                            String i1=product.getString("Id");
                                            switch (i1)
                                            {
                                                case "1":
                                                    it=new Intent(v.getContext(),Mysecondphy.class);
                                                    it.putExtra(sgd,mypage1.getTitle());
                                                    mCtx.startActivity(it);
                                                    break;

                                                case "2":
                                                    it=new Intent(v.getContext(),Mysecondche.class);
                                                    it.putExtra(sgd,mypage1.getTitle());
                                                    mCtx.startActivity(it);
                                                    break;

                                                case "3":
                                                    it=new Intent(v.getContext(),Mysecondmath.class);
                                                    it.putExtra(sgd,mypage1.getTitle());
                                                    mCtx.startActivity(it);
                                                    break;

                                                case "4":
                                                    it=new Intent(v.getContext(),Mysecondbio.class);
                                                    it.putExtra(sgd,mypage1.getTitle());
                                                    mCtx.startActivity(it);
                                                    break;
                                            }
                                        }


                                        //creating adapter object and setting it to recyclerview

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                   // Toast.makeText(ProductsAdapter.this,"Error",Toast.LENGTH_LONG).show();
                                }
                            });

                    //adding our stringrequest to queue

                    Volley.newRequestQueue(mCtx).add(stringRequest);
*/

                    // check if item still exists


                }
            });

        }
    }

}
