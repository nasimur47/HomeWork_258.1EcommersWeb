package com.nasim.homework_2581ecommersweb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Home_Activity extends AppCompatActivity {
    ImageSlider imageSlider;
    GridView    gridView;
    ArrayList<HashMap<String,String>>   arrayList = new ArrayList<>();
    HashMap<String,String>  hashMap;
    ProgressBar  progressBar;
    BottomNavigationView    bottomNevView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        getSupportActionBar().hide();

        imageSlider =findViewById(R.id.image_slider);
        gridView = findViewById( R.id.gridView );
        progressBar = findViewById( R.id.progressBar );
        bottomNevView = findViewById( R.id.bottomNevView );

//        .................bottom nev view. start..............
        bottomNevView.setOnNavigationItemSelectedListener( new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.addToCard:
                        startActivity( new Intent(Home_Activity.this,Add_To_Cart_Activity.class) );
                        break;



                }
                return true;
            }
        } );



        //        .................bottom nev view. end..............


        String  url = "https://www.mobiledokan.com/wp-content/uploads/2022/09/Apple-iPhone-14-Pro-Max.jpg";
        String  url_1 = "https://consumer.huawei.com/content/dam/huawei-cbg-site/en/support/laptop-user-guide/img/laptop.png";
        String  url_2 = "https://cdn-4.nikon-cdn.com/e/Q5NM96RZZo-YRYNeYvAi9beHK4x3L-8u4h56I3YwHLAQ4G0XzTY4Dg==/Views/353_1590_D3500_left.png";
        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(url,null));
        imageList.add(new SlideModel(url_1,null));
        imageList.add(new SlideModel(url_2,null));

        imageSlider.setImageList(imageList);
        progressBar.setVisibility( View.VISIBLE );

//..................staet...................................

        String url1 = "https://marzan45.000webhostapp.com/apps/info.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility( View.GONE );




                        try {
                            JSONArray   jsonArray = response.getJSONArray( "products" );
                            for (int x = 0; x < jsonArray.length(); x++) {

                                JSONObject  jsonObject =  jsonArray.getJSONObject( x );
                                JSONArray   imageArray = jsonObject.getJSONArray( "images" );


                                String  sTitle = jsonObject.getString( "title" );
                                String  sDescription = jsonObject.getString( "description" );
                                String  sPrice = jsonObject.getString( "price" );
                                String sImage = imageArray.getString( 0 );
                                String  sDiscountPercentage = jsonObject.getString( "discountPercentage" );
                                String  sRating = jsonObject.getString( "rating" );
                                String  sStock = jsonObject.getString( "stock" );
                                String  sBrand = jsonObject.getString( "brand" );
                                String  sCategory = jsonObject.getString( "category" );

                                    hashMap = new HashMap<>();
                                    hashMap.put( "images", sImage );
                                    hashMap.put( "title", sTitle );
                                    hashMap.put( "description", sDescription );
                                    hashMap.put( "prize", sPrice );
                                hashMap.put( "discountPercentage", sDiscountPercentage );
                                hashMap.put( "rating", sRating );
                                hashMap.put( "stock", sStock );
                                hashMap.put( "brand", sBrand );
                                hashMap.put( "category", sCategory );
                                    arrayList.add( hashMap );


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        progressBar.setVisibility( View.GONE );

                    }
                }
                );

        RequestQueue requestQueue = Volley.newRequestQueue( Home_Activity.this );
        requestQueue.add(jsonObjectRequest);




        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);


    }

//    ............MyAdapter made..............

    public class MyAdapter extends BaseAdapter {
        LayoutInflater  layoutInflater;

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            layoutInflater = (LayoutInflater) getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            View    myView = layoutInflater.inflate( R.layout.gird_view_activity , viewGroup,false);


            ImageView   pImage = myView.findViewById( R.id.pImage );
            TextView    pTitle = myView.findViewById( R.id.pTitle );
            TextView    pDescription = myView.findViewById( R.id.pDescription );
            TextView    pPrize = myView.findViewById( R.id.pPrize );
            LinearLayout  list_detail = myView.findViewById( R.id.list_detail );

            HashMap<String,String>  hashMap = arrayList.get( position );
            String SpImage = hashMap.get( "images" );
            String SpTitle = hashMap.get( "title" );
            String SpDescription = hashMap.get( "description" );
            String SpPrize = hashMap.get( "prize" );
            String SpdiscountPercentage = hashMap.get( "discountPercentage" );
            String Sprating = hashMap.get( "rating" );
            String Spstock = hashMap.get( "stock" );
            String Spbrand = hashMap.get( "brand" );
            String Spcategory = hashMap.get( "category" );


            Picasso.get().load( SpImage ).into( pImage );
            pTitle.setText( SpTitle );
            pDescription.setText( SpDescription );
            pPrize.setText( SpPrize+"$" );


            list_detail.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productDescription.TITLE = SpTitle ;
                    productDescription.DESCRIPTION =  SpDescription ;
                    productDescription.PRIZE = SpPrize ;
                    Bitmap my_bitmap = ((BitmapDrawable) pImage.getDrawable()).getBitmap();
                    productDescription.my_bitmap = my_bitmap;
                    productDescription.DISCOUNTPERCENTAGE = SpdiscountPercentage ;
                    productDescription.RATING = Sprating ;
                    productDescription.STOCK = Spstock ;
                    productDescription.BRAND = Spbrand ;
                    productDescription.CATEGORY = Spcategory ;




                    startActivity( new Intent(Home_Activity.this,productDescription.class) );
                }
            } );

            return myView ;
        }
    }

//    .....MyAdapter end..........







}