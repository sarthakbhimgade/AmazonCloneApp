package com.example.amazoncloneapp.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazoncloneapp.Interfaces.ItemClickListener;
import com.example.amazoncloneapp.R;

public class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView orderName,orderDate,orderAddr,orderCity,orderPhone,orderPrice;
    private ItemClickListener itemClickListener;


    public OrdersViewHolder(@NonNull View itemView) {
        super(itemView);
        orderName = itemView.findViewById(R.id.orderName);
        orderDate = itemView.findViewById(R.id.orderDate);
        orderAddr = itemView.findViewById(R.id.orderAddr);
        orderCity = itemView.findViewById(R.id.orderCity);
        orderPhone = itemView.findViewById(R.id.orderPhone);
        orderPrice = itemView.findViewById(R.id.orderPrice);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
