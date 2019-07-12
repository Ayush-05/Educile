package myeducile.project.ayush.tutor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Myadapter1 extends RecyclerView.Adapter<Myadapter1.ProductViewHolder> {
    private Context mCtx;
    Intent it;
    public static final String sgd="";
    public static final String sgd1="";
    //we are storing all the products in a list
    private List<Mypage1> productList;
    Context cc;

    //getting the context and product list with constructor
    public Myadapter1(Context mCtx, List<Mypage1> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.mylayout1, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {

        //getting the product of the specified position
        Mypage1 product = productList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(product.getTitle());
        holder.textViewShortDesc.setText(product.getShortdesc());
        holder.textViewRating.setText(product.getRating());
        holder.textViewPrice.setText(product.getPrice1());
        holder.textViewPrice2.setText(product.getPrice2());
        holder.cost.setText(product.getCost());
        holder.iv1.setImageDrawable(mCtx.getResources().getDrawable(product.getImage1()));
        holder.iv2.setImageDrawable(mCtx.getResources().getDrawable(product.getImage2()));




    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice,cost,textViewPrice2;
        ImageView iv1,iv2;

        public ProductViewHolder(final View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            cost=itemView.findViewById(R.id.cost);
            textViewPrice2=itemView.findViewById(R.id.textViewPrice2);
            iv1 = itemView.findViewById(R.id.iv1);
            iv2 = itemView.findViewById(R.id.iv2);

            cc=itemView.getContext();
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    // get position
                    Mypage1 mypage1=productList.get(getAdapterPosition());
                    it=new Intent(mCtx,Main2Activity.class);
                    it.putExtra(sgd,mypage1.getTitle());
                    it.putExtra("des",mypage1.getCost());
                    //it.putExtra(sgd1,mypage1.getCost());
                    cc.startActivity(it);
                   /* Mypage1 mypage1=productList.get(getAdapterPosition());
                    int pos = getAdapterPosition();

                    // check if item still exists
                    switch (pos)
                    {
                        case 0:
                            it=new Intent(v.getContext(),Main2Activity.class);
                            it.putExtra(sgd,mypage1.getTitle());
                            cc.startActivity(it);
                    }
*/
                }
            });

        }

    }
    public void filterList(ArrayList<Mypage1> filterdNames) {
        productList = filterdNames;
        notifyDataSetChanged();
    }

}
