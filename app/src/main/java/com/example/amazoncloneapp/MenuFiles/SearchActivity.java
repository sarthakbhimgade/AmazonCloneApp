package com.example.amazoncloneapp.MenuFiles;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import com.example.amazoncloneapp.HomeActivity;
import com.example.amazoncloneapp.ProductDetails;
import com.example.amazoncloneapp.R;
import com.example.amazoncloneapp.model.AddProdModel;
import com.example.amazoncloneapp.viewholder.ViewProductsHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchActivity extends BaseActivity {
    EditText inputText;
    AppCompatButton searchBtn;
    RecyclerView searchList;
    String searchInput;
    ImageView backHome;
    Toolbar viewToolbar;
    LinearLayout dynamicContent;
    LinearLayout bottomNavBar;

        @SuppressLint("ResourceAsColor")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //  setContentView(R.layout.activity_home);

            dynamicContent = findViewById(R.id.dynamicContent);
            bottomNavBar = findViewById(R.id.bottomNavBar);
            View wizard = getLayoutInflater().inflate(R.layout.activity_search, null);
            dynamicContent.addView(wizard);

            RadioGroup rg = findViewById(R.id.radioGroup1);
            RadioButton rb = findViewById(R.id.bottom_search );

            rb.setBackgroundColor(R.color.item_selected);
            rb.setTextColor(Color.parseColor("#3F5185"));

            viewToolbar = findViewById(R.id.viewtoolbar);
            viewToolbar.setBackgroundResource(R.drawable.bg_color);

            searchBtn = findViewById(R.id.search_btn);
            searchBtn.setBackgroundResource(R.drawable.intro_signin);

            inputText = findViewById(R.id.searchEditText);
            searchList = findViewById(R.id.searchList);
            backHome = findViewById(R.id.backHome);

            searchList.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    searchInput = inputText.getEditableText().toString();
                    onStart();
                }
            });
            backHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

    @Override
    protected void onStart() {
        super.onStart();

        final DatabaseReference prodListRef = FirebaseDatabase.getInstance().getReference().child("View All").
                child("User View ").child("Products");
        FirebaseRecyclerOptions<AddProdModel> options = new FirebaseRecyclerOptions.Builder<AddProdModel>().
                setQuery(prodListRef.orderByChild("name").startAt(searchInput),AddProdModel.class).build();

        FirebaseRecyclerAdapter<AddProdModel, ViewProductsHolder> adapter =
                new FirebaseRecyclerAdapter<AddProdModel, ViewProductsHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewProductsHolder holder, int position, @NonNull AddProdModel model) {
                        String name = model.getName().replaceAll("\n","");
                        String price = model.getPrice();
                        String imgUri = model.getImg();

                        holder.addProductName.setText(name);
                        holder.addProductPrice.setText(price);

                        Picasso.get().load(imgUri).into(holder.addProductImg);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(SearchActivity.this, ProductDetails.class);
                                intent.putExtra("id",4);
                                intent.putExtra("uniqueId",name);
                                intent.putExtra("addProdName",name);
                                intent.putExtra("addProdPrice",price);
                                intent.putExtra("addProdDesc",model.getDescription());
                                intent.putExtra("addProdCategory",model.getCategory());
                                intent.putExtra("img",imgUri);
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ViewProductsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products_adapter,parent,false);
                       ViewProductsHolder holder = new ViewProductsHolder(view);
                       return holder;
                    }
                };
        searchList.setAdapter(adapter);
        adapter.startListening();
    }
}
