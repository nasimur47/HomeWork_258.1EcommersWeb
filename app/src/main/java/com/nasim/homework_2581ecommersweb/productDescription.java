package com.nasim.homework_2581ecommersweb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class productDescription extends AppCompatActivity {

    public static String TITLE = "";
    public static String DESCRIPTION = "";
    public static String PRIZE = "";
    public static Bitmap my_bitmap = null;
    public static String DISCOUNTPERCENTAGE = "";
    public static String RATING = "";
    public static String STOCK = "";
    public static String BRAND = "";
    public static String CATEGORY = "";


    ImageView   pImage ;
    TextView    pTitle,pDescription,pPrize,discountPercentage,rating,stock,brand,category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_product_description );
        getSupportActionBar().hide();

        pImage=findViewById( R.id.pImage );
        pTitle=findViewById( R.id.pTitle );
        pDescription=findViewById( R.id.pDescription );
        pPrize=findViewById( R.id.pPrize );
        discountPercentage=findViewById( R.id.discountPercentage );
        rating=findViewById( R.id.rating );
        stock=findViewById( R.id.stock );
        brand=findViewById( R.id.brand );
        category=findViewById( R.id.category );




        pTitle.setText( TITLE );
        pDescription.setText( DESCRIPTION );
        pPrize.setText( "Prize:     "+PRIZE+"$" );
        discountPercentage.setText( "Discount Percentage:"+DISCOUNTPERCENTAGE +"%" );
        rating.setText( "Rating:        "+RATING+"/5" );
        stock.setText( "Stock:      "+STOCK +"Pc");
        brand.setText( "Brand:      "+BRAND );
        category.setText( "Catagory:        "+CATEGORY );

        if (my_bitmap!=null) pImage.setImageBitmap( my_bitmap );







    }
}