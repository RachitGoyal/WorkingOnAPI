package com.example.nimish.workingonapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.Collections;
import java.util.List;

/**
 * Created by Nimish on 7/24/2015.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private final LayoutInflater inflater;
    List<RecipePojo> data = Collections.emptyList();
    ListViewHolder viewHolder;
    private VolleySingleton volleySingleton;
    private ImageLoader imageLoader;

    public ListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        volleySingleton = VolleySingleton.getInstance();
        imageLoader = VolleySingleton.getImageLoader();
    }

    public void setRecipeList(List<RecipePojo> data){
        this.data = data;
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view, parent, false);

        viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ListViewHolder holder, int position) {
        RecipePojo current = data.get(position);
        holder.recipeName.setText(current.getRecipeName());
        holder.recipeMethod.setText(current.getCookingMethod());
        String imageURL = current.getRecipeImageURL();
        if(imageURL!=null){
            imageLoader.get(imageURL, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                    holder.recipeImage.setImageBitmap(imageContainer.getBitmap());
                }

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        TextView recipeName;
        TextView recipeMethod;
        ImageView recipeImage;
        public ListViewHolder(View itemView) {
            super(itemView);

            recipeName = (TextView) itemView.findViewById(R.id.recipe_name);
            recipeMethod = (TextView) itemView.findViewById(R.id.recipe_method);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
        }
    }
}
