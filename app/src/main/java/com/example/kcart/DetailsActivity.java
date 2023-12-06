package com.example.kcart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kcart.databinding.ActivityMainBinding;
import com.example.kcart.view.ImageAdapter;
import com.example.kcart.view.ProductAdapter;

import java.util.Arrays;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<String> imgList;
    private TextView tv_title, tv_desc, tv_rating, tv_stock, tv_brand, tv_category, tv_finalPriceStr, tv_discPercent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        initializeVariables();


        //Retrieval of data from intent
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String rating = getIntent().getStringExtra("rating");
        String stock = getIntent().getStringExtra("stock");
        String brand = getIntent().getStringExtra("brand");
        String category = getIntent().getStringExtra("category");
        String[] receivedStringArray = getIntent().getStringArrayExtra("images");

        imgList = Arrays.asList(receivedStringArray);

        int price = getIntent().getIntExtra("price", 0);
        Double discPercent = getIntent().getDoubleExtra("discPercent", 0);
        Double finalPriceDouble = (double) ((100 - discPercent)  * price) / 100;
        String finalPriceStr = String.format("%.2f", finalPriceDouble);  //Final price after discount up to two decimal places.


        tv_title.setText(title);
        tv_brand.setText("Brand: "+brand);
        tv_rating.setText("Rating: "+rating +" / 5");
        tv_desc.setText(desc);
        tv_stock.setText("Stock: " + stock);
        tv_discPercent.setText("-" + discPercent + "%");
        tv_finalPriceStr.setText("| $" +finalPriceStr);
        tv_category.setText("Category: " + category);


//        Toast.makeText(this, "" + finalPriceStr, Toast.LENGTH_SHORT).show();


        displayImagesInRecyclerView();

    }

    private void initializeVariables() {
        tv_brand = findViewById(R.id.details_Brand);
        tv_title = findViewById(R.id.details_title);
        tv_desc = findViewById(R.id.details_desc);
        tv_rating = findViewById(R.id.details_rating);
        tv_stock = findViewById(R.id.details_stock);
        tv_finalPriceStr = findViewById(R.id.details_price);
        tv_category = findViewById(R.id.details_category);
        tv_discPercent = findViewById(R.id.details_discPercent);



    }

    private void displayImagesInRecyclerView() {
        recyclerView = findViewById(R.id.rv_img);

        imageAdapter = new ImageAdapter(this, imgList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(imageAdapter);
    }
}