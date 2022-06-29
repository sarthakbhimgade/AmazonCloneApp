package com.example.amazoncloneapp.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoncloneapp.Interfaces.ItemClickListener;
import com.example.amazoncloneapp.R;

public class ViewProductsHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    public TextView addProductName, addProductPrice;
    public ItemClickListener itemClickListener;
    public ImageView addProductImg;



    public ViewProductsHolder(@NonNull View itemView) {
        super(itemView);
        addProductImg = itemView.findViewById(R.id.prod_image);
        addProductPrice = itemView.findViewById(R.id.prod_price);
        addProductName = itemView.findViewById(R.id.prod_name);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);


    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}
