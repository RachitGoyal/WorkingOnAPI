package com.example.nimish.workingonapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.nimish.workingonapi.Keys.EndPointRecipe.KEY_IMAGE;
import static com.example.nimish.workingonapi.Keys.EndPointRecipe.KEY_METHOD;
import static com.example.nimish.workingonapi.Keys.EndPointRecipe.KEY_NAME;
import static com.example.nimish.workingonapi.Keys.EndPointRecipe.KEY_RESULT;

public class HomeFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recipeList;
    private ListAdapter adapter;
    private VolleySingleton vollySingleton;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private List<RecipePojo> recipess = new ArrayList<>();

    public static final String URL_SHOW_RECIPES = "http://api.pearson.com:80/kitchen-manager/v1/recipes?ingredients-all=sugar&limit=30";

    private String mParam1;
    private String mParam2;


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        vollySingleton = VolleySingleton.getInstance();
        requestQueue = VolleySingleton.getRequestQueue();

    }

    private void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL_SHOW_RECIPES, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonObject(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

        requestQueue.add(request);

    }

    private void parseJsonObject(JSONObject response) {
        List<RecipePojo> list = new ArrayList<>();
        StringBuilder bui = new StringBuilder();
        if (response == null || response.length()==0) {
            return;
        }
            try {
                if (response.has(KEY_RESULT)) {
                    JSONArray arrayRecipe = response.getJSONArray(KEY_RESULT);

                    for (int i = 0; i < arrayRecipe.length(); i++) {
                        RecipePojo recipe = new RecipePojo();
                        JSONObject currentRecipe = arrayRecipe.getJSONObject(i);
                        String name = currentRecipe.getString(KEY_NAME);
                        recipe.setRecipeName(name);
                        bui.append(name);

                        String method = currentRecipe.getString(KEY_METHOD);
                        recipe.setCookingMethod(method);

                        bui.append(method);

                        /*if (currentRecipe.has(KEY_INGREDIENT)) {
                            JSONArray ingredientArray = currentRecipe.getJSONArray(KEY_INGREDIENT);
                            for (int j = 0; j < ingredientArray.length(); j++) {
                                String ing = ingredientArray.getString(j);
                                String temp = j + 1 + " " + ing + "\n";
                            }
                        } else {
                            L.t(getActivity(), "Ingredients not found");
                        }
*/
                        String urlThumbnail = currentRecipe.getString(KEY_IMAGE);
                        recipe.setRecipeImageURL(urlThumbnail);

                        recipess.add(recipe);
                    //  L.t(getActivity(), bui.toString());
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        adapter.setRecipeList(recipess);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        recipeList = (RecyclerView) rootView.findViewById(R.id.recipe_list);
        adapter = new ListAdapter(getActivity());
        sendJsonRequest();
        recipeList.setHasFixedSize(true);
        recipeList.setAdapter(adapter);
        recipeList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return rootView;
    }


}
