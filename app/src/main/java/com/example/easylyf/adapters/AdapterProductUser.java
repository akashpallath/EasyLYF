package com.example.easylyf.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylyf.FilterProductUser;
import com.example.easylyf.R;
import com.example.easylyf.activites.ShopDetailsActivity;
import com.example.easylyf.models.ModelProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class AdapterProductUser extends RecyclerView.Adapter<AdapterProductUser.HolderProductUser> implements Filterable {

    private Context context;
    public ArrayList<ModelProduct> productsList, filterList;
    private FilterProductUser filter;

    public AdapterProductUser(Context context, ArrayList<ModelProduct> productsList) {
        this.context = context;
        this.productsList = productsList;
        this.filterList = productsList;
    }

    @NonNull
    @Override
    public HolderProductUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout
        View view = LayoutInflater.from(context).inflate(R.layout.row_product_user, parent, false);
        return new HolderProductUser(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductUser holder, int position) {
        //get data
        final ModelProduct modelProduct = productsList.get(position);
        String discountAvailable = modelProduct.getDiscountAvailable();
        String discountedNote = modelProduct.getProductDiscountedNote();
        String discountPrice = modelProduct.getProductDiscountedPrice();
        String ProductCategory = modelProduct.getProductCategory();
        String originalPrice = modelProduct.getProductPrice();
        String productDescription = modelProduct.getProductDescription();
        String productTitle = modelProduct.getProductTitle();
        String productQuantity = modelProduct.getProductQuantity();
        String productId = modelProduct.getProductID();
        String timestamp = modelProduct.getTimestamp();
        String productIcon = modelProduct.getProductPic();

        //set data
        holder.productTitle.setText(productTitle);
        holder.productDiscountedNote.setText(discountedNote);
        holder.productQuantity.setText(productQuantity);
        holder.productDescription.setText(productDescription);
        holder.productDiscountedPrice.setText("₹"+discountPrice);
        holder.productPrice.setText("₹"+originalPrice);

        if (discountAvailable.equals("true")){
            holder.productDiscountedPrice.setVisibility(View.VISIBLE);
            holder.productDiscountedNote.setVisibility(View.VISIBLE);
            holder.productPrice.setPaintFlags(holder.productPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //add strike through on original price
        }
        else {
            holder.productDiscountedPrice.setVisibility(View.GONE);
            holder.productDiscountedNote.setVisibility(View.GONE);
            holder.productPrice.setPaintFlags(0);
        }

        try {
            Picasso.get().load(productIcon).placeholder(R.drawable.ic_add_shopping_cart).into(holder.productPic);
        }
        catch (Exception e) {
            holder.productPic.setImageResource(R.drawable.ic_add_shopping_cart);
        }

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add product to cart
                showQuantityDialog(modelProduct);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //show products details
            }
        });

    }

    private double cost = 0;
    private double finalCost = 0;
    private int quantity = 0;
    private void showQuantityDialog(ModelProduct modelProduct) {
        //inflate layout for dialog
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quantity, null);
        //init layout views
        ImageView productIv = view.findViewById(R.id.productIv);
        TextView titleIv = view.findViewById(R.id.titleIv);
        TextView pQuantityTv = view.findViewById(R.id.pQuantityTv);
        TextView descriptionTv = view.findViewById(R.id.descriptionTv);
        TextView discountedNoteTv = view.findViewById(R.id.discountedNoteTv);
        TextView originalPriceTv = view.findViewById(R.id.originalPriceTv);
        TextView priceDicountedTv = view.findViewById(R.id.priceDicountedTv);
        TextView finalPriceTv = view.findViewById(R.id.finalPriceTv);
        Button decrementBtn = view.findViewById(R.id.decrementBtn);
        TextView quantityTv = view.findViewById(R.id.quantityTv);
        Button incrementBtn = view.findViewById(R.id.incrementBtn);
        Button continueBtn = view.findViewById(R.id.continueBtn);

        //get data from model
        String productID = modelProduct.getProductID();
        String title = modelProduct.getProductTitle();
        String productQuantity = modelProduct.getProductQuantity();
        String description = modelProduct.getProductDescription();
        String discountNote = modelProduct.getProductDiscountedNote();
        String image = modelProduct.getProductPic();

        final String price;
        if(modelProduct.getDiscountAvailable().equals("true")){
            //product have discount
            price = modelProduct.getProductDiscountedPrice();
            discountedNoteTv.setVisibility(View.VISIBLE);
            originalPriceTv.setPaintFlags(originalPriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); //add strike through on original price
        }
        else {
            //product don't have discount
            discountedNoteTv.setVisibility(View.GONE);
            priceDicountedTv.setVisibility(View.GONE);
            price = modelProduct.getProductPrice();
        }

        cost = Double.parseDouble(price.replaceAll("₹", ""));
        finalCost = Double.parseDouble(price.replaceAll("₹", ""));
        quantity = 1;

        //dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        try{
            Picasso.get().load(image).placeholder(R.drawable.ic_shopping_cart).into(productIv);
        }
        catch (Exception e){
            productIv.setImageResource(R.drawable.ic_shopping_cart);
        }

        titleIv.setText(""+title);
        pQuantityTv.setText(""+productQuantity);
        descriptionTv.setText(""+description);
        discountedNoteTv.setText(""+discountNote);
        quantityTv.setText(""+quantity);
        originalPriceTv.setText(""+modelProduct.getProductPrice());
        priceDicountedTv.setText(""+modelProduct.getProductDiscountedPrice());
        finalPriceTv.setText(""+finalCost);

        AlertDialog dialog = builder.create();
        dialog.show();

        //increment quantity of the product
        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCost = finalCost + cost;
                quantity++;

                finalPriceTv.setText(""+finalCost);
                quantityTv.setText(""+quantity);
            }
        });

        //decrement quantity of the product, only if quantity is > 1
        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity>1){
                    finalCost = finalCost - cost;
                    quantity --;

                    finalPriceTv.setText(""+finalCost);
                    quantityTv.setText(""+quantity);
                }
            }
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleIv.getText().toString().trim();
                String priceEach = price;
                String totalPrice = finalPriceTv.getText().toString().trim().replace("","");
                String quantity = quantityTv.getText().toString().trim();

                //add to db(SQLite)
                addToCart(productID, title, priceEach, totalPrice, quantity);

                dialog.dismiss();
            }
        });


    }

    private int itemId = 1;
    private void addToCart(String productID, String title, String priceEach, String price, String quantity) {
        itemId++;

        EasyDB easyDB = EasyDB.init(context, "ITEMS_DB")
                .setTableName("ITEMS_TABLE")
                .addColumn(new Column("Item_Id", new String[]{"text","unique"}))
                .addColumn(new Column("Item_PID", new String[]{"text","not null"}))
                .addColumn(new Column("Item_Name", new String[]{"text","not null"}))
                .addColumn(new Column("Item_Price_Each", new String[]{"text","not null"}))
                .addColumn(new Column("Item_Price", new String[]{"text","not null"}))
                .addColumn(new Column("Item_Quantity", new String[]{"text","not null"}))
                .doneTableColumn();

        Boolean b = easyDB.addData("Item_Id", itemId)
                .addData("Item_PID", productID)
                .addData("Item_Name", title)
                .addData("Item_Price_Each", priceEach)
                .addData("Item_Price", price)
                .addData("Item_Quantity", quantity)
                .doneDataAdding();

        Toast.makeText(context, "Added to cart....", Toast.LENGTH_SHORT).show();

        //update cart
        ((ShopDetailsActivity)context).cartCount();

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new FilterProductUser(this, filterList);
        }
        return filter;
    }

    class HolderProductUser extends RecyclerView.ViewHolder{

        private ImageView productPic;
        private TextView productDiscountedNote, productDescription, productTitle, productQuantity, productDiscountedPrice, productPrice ;
        private Button addToCart;

        public HolderProductUser(@NonNull View itemView) {
            super(itemView);

            productPic = itemView.findViewById(R.id.productPic);
            productDiscountedNote = itemView.findViewById(R.id.productDiscountedNote);
            productTitle = itemView.findViewById(R.id.productTitle);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productDiscountedPrice = itemView.findViewById(R.id.productDiscountedPrice);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCart = itemView.findViewById(R.id.addToCart);
            productDescription = itemView.findViewById(R.id.productDescription);

        }
    }

}
