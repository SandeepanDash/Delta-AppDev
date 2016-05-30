package com.example.sandeepandash.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private int Count=0 , Colour,i=0, j=0, k=0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Declare Increment Button
        final Button IncrementButton;

        // Declare Reset Button
        final Button ResetButton;

        // Declare Count text view
        final TextView CountText=(TextView)findViewById(R.id.CountText);

        // Declare Main Layout
        final RelativeLayout LayoutRoot;

        SharedPreferences sharedPref=getSharedPreferences("countSave",Context.MODE_PRIVATE);
        Count=sharedPref.getInt("Count",0);
        i=sharedPref.getInt("Color",0);
        j=sharedPref.getInt("Color1",0);
        k=sharedPref.getInt("Color2",0);

        // Init count

        // Init Colour

        // Get reference to Main Layout
        LayoutRoot = (RelativeLayout)findViewById(R.id.LayoutRoot);
        IncrementButton = (Button) findViewById(R.id.IncrementButton);
        ResetButton=(Button) findViewById(R.id.ResetButton);

        CountText.setText( "Count is: " + Count);
        LayoutRoot.setBackgroundColor(Color.argb(255, i, j, k));
        IncrementButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Update Count
                Count++;

                // Get reference of Text View


                CountText.setText( "Count is: " + Count);

                // Change Background Colour to a Random Colour
                 i=getRandomColor();
                j=getRandomColor();
                k=getRandomColor();
                LayoutRoot.setBackgroundColor(Color.argb(255, i, j, k));


                ResetButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Count=0;
                            CountText.setText("Count is: "+ Count);
                        }
                    });
            }
        });
    }

    private int getRandomColor() // returns random int (100 - 255)
    {
        Random randomIntGenerator = new Random();
        return  (randomIntGenerator.nextInt(255));
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPref=getSharedPreferences("countSave", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putInt("Count",Count);
        editor.putInt("Color",i);
        editor.putInt("Color1",j);
        editor.putInt("Color2",k);
                editor.commit();
    }
}


