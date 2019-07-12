package myeducile.project.ayush.tutor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter2 extends RecyclerView.Adapter<Myadapter2.ProductViewHolder> {
    private Context mCtx;
    Intent it;
    public static final String sgd="";
    public static final String sgd1="";
    //we are storing all the products in a list
    private List<JnrTeacher> productList21;
    Context cc;

    //getting the context and product list with constructor
    public Myadapter2(Context mCtx, List<JnrTeacher> productList) {
        this.mCtx = mCtx;
        this.productList21 = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.my_tutors_secondary, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        //getting the product of the specified position
        JnrTeacher product = productList21.get(position);

        //binding the data with the viewholder views
        holder.tt1.setText(product.getName());
        holder.tt2.setText(product.getDesc());
        holder.tt3.setText(product.getTitle());




    }


    @Override
    public int getItemCount() {
        return productList21.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tt1,tt2,tt3;


        public ProductViewHolder(final View itemView) {
            super(itemView);

            tt1=itemView.findViewById(R.id.namejnr);
            tt2=itemView.findViewById(R.id.descjnr);
            tt3=itemView.findViewById(R.id.titlejnr);

            cc=itemView.getContext();


        }

    }

}
