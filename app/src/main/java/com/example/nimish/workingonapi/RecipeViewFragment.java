package com.example.nimish.workingonapi;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


public class RecipeViewFragment extends Fragment {
    private RecyclerView recipeList;
    private ListAdapter adapter;
    private VolleySingleton vollySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    public RecipeViewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the editbox for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recipe_view, container, false);
        recipeList = (RecyclerView) rootView.findViewById(R.id.recipe_list);
        adapter = new ListAdapter(getActivity());
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(adapter);
        recipeList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        return rootView;
    }
}
