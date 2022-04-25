package Adaptor;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment4.EditCartItem;
import com.example.assignment4.EditStock;
import com.example.assignment4.MyCart;
import com.example.assignment4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import Models.Cart;
import Models.Stock;
import VH.MyCartVH;
import VH.StockVH;

public class MyCartAdaptor extends RecyclerView.Adapter<MyCartVH> {

    private Context mContext;
    private List<Cart> myCart;
    private FirebaseUser mUser;
    final DatabaseReference CartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");

    public MyCartAdaptor(Context mContext, List<Cart> myCart) {
        this.mContext = mContext;
        this.myCart = myCart;
    }

    @NonNull
    @Override
    public MyCartVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        return new MyCartVH(v);
    }

    public void deleteItem(int position){
        Cart c = myCart.get(position);
        String id = c.getCartitemID();


        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("MyCart");
        cartRef.child(id).removeValue();
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartVH holder, int position) {
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        String u = mUser.getUid();
        Cart c = myCart.get(position);
        holder.citemN.setText(c.getItemName());
        holder.citemB.setText(c.getManufacturer());
        holder.citemP.setText(c.getItemprice());
        holder.citemQuan.setText(c.getItemquantity());

        holder.txtOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(mContext, holder.txtOption);
                popupMenu.inflate(R.menu.cartitemmenu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()){
                            case R.id.edit:
                                Intent intent = new Intent(mContext, EditCartItem.class);
                                intent.putExtra("stockitemKey", c.getCartitemID());
                                mContext.startActivity(intent);
                                break;
                            case R.id.deleteitem:
                                CartRef.child(c.getCartitemID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(mContext, "Item deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                break;

                        }
                        return false;
                    }
                });
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EditCartItem.class);
                intent.putExtra("stockitemKey", c.getCartitemID());
                mContext.startActivity(intent);
            }
//                CharSequence options[] = new CharSequence[]{
//                        "Edit",
//                        "Delete"
//                };
//                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//                builder.setTitle("Cart Options");
//
//                builder.setItems(options, (dialogInterface, i) -> {
//                    if(i==0){
//                        Intent intent = new Intent(mContext, EditCartItem.class);
//                        intent.putExtra("stockitemKey", c.getCartitemID());
//                        mContext.startActivity(intent);
//                    }
//                    else if(i==1){
//                        CartRef.child(c.getCartitemID()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//                            }
//                        });
//                    }
//                });
//            }
        });


    }



    @Override
    public int getItemCount() {
        return myCart.size();
    }
}
