package Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import Models.Cart;
import Models.Stock;
import VH.MyCartVH;
import VH.ReviewVH;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewVH> {

    private Context mContext;
    private List<Cart> myCart;
    private FirebaseUser mUser;
    final DatabaseReference CartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");

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
        //holder.itemPIC.
    }

    @Override
    public int getItemCount() {
        return myCart.size();
    }
}
