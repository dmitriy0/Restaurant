package com.example.restaurant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductsAdapter  extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

   private final LayoutInflater inflater;
   private final List<Products> products;

   ProductsAdapter(Context context, List<Products> products) {
      this.products = products;
      this.inflater = LayoutInflater.from(context);
   }
   @Override
   public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      View view = inflater.inflate(R.layout.recycycler_view_item, parent, false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position) {
      Products article = products.get(position);
      holder.priceView.setText(article.getPrice());
      holder.nameView.setText(article.getName());
      holder.descriptionView.setText(article.getDescription());
      Picasso.get().load(article.getImageUrl()).error(R.drawable.error_image).into(holder.imageView);
   }

   @Override
   public int getItemCount() {
      return products.size();
   }


   public static class ViewHolder extends RecyclerView.ViewHolder {
      final ImageView imageView;
      final TextView priceView, nameView, descriptionView;
      ViewHolder(View view){
         super(view);
         priceView = view.findViewById(R.id.price);
         nameView = view.findViewById(R.id.name);
         descriptionView = view.findViewById(R.id.description);
         imageView = view.findViewById(R.id.image);
      }
   }
}

