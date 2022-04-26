package Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.AddToCart;
import com.example.assignment4.MainActivity;
import com.example.assignment4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Cart;
import Models.Stock;
import VH.MyCartVH;
import VH.ReviewVH;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewVH> {

    private Context mContext;
    private List<Cart> myCart;

    private FirebaseUser mUser;
    final DatabaseReference CartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
    final DatabaseReference reviewRef = FirebaseDatabase.getInstance().getReference().child("Reviews");

    public ReviewAdapter(Context mContext, List<Cart> myCart) {
        this.mContext = mContext;
        this.myCart = myCart;
    }


    @NonNull
    @Override
    public ReviewVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.review_item, parent, false);
        return new ReviewVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewVH holder, int position) {
        Cart c = myCart.get(position);
        holder.iN.setText(c.getItemName());


//        cart.put("category", itemC);
//        cart.put("manufacturer", itemM);
//        cart.put("itemprice", itemP);
//        cart.put("itemquantity", q);
//        cart.put("itemID", itemid);
//
//        mycart.child(itemId).setValue(cart)

        holder.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String rating = String.valueOf(holder.ratingBar.getRating());
                String revi = holder.review.getText().toString();
                mUser = FirebaseAuth.getInstance().getCurrentUser();
                Map<String, Object> rev = new HashMap<>();
                String customerID = mUser.getUid();
                String itemId = reviewRef.push().getKey();
                rev.put("itemID", c.getItemID());
                rev.put("itemname", c.getItemName());
                rev.put("rating", rating);
                rev.put("review", revi);
                rev.put("cusotmer", customerID);
                reviewRef.child(itemId).setValue(rev).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            holder.review.setText(revi);
                            holder.ratingBar.setRating(Float.parseFloat(rating));
//                            Intent intent = new Intent(mContext, MainActivity.class);
//                            mContext.startActivity(intent);
                            Toast.makeText(mContext, "Rating made", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        //holder.itemPIC.
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }
}
