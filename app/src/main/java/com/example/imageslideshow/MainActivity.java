package com.example.imageslideshow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.ContentInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ImageView imageView;
    private Button nextButton;
    private Button backButton;
    private List<Integer> imageResourceId = new ArrayList<>();
    private int currentImageIndex = 0;

    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageViewer);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // retrieves an array of Field objects that represents all the fields (resource) in R.raw class
        // basically gets the res/raw
        // with this, i can add as many images as i want without needing to hold them in an array
        Field[] fields = R.raw.class.getFields();
        for(Field field : fields){

            try{

                int temp = field.getInt(null);
                imageResourceId.add(temp);

            }
            catch(IllegalAccessException e){

                e.printStackTrace();

            }

        }

        nextButton.setText("Get Image");
        backButton.setText("Get Image");

    }

    public void onNextImage(View view){

        currentImageIndex = (currentImageIndex + 1) % imageResourceId.size();
        int temp = imageResourceId.get(currentImageIndex);
        imageView.setImageResource(temp);

        nextButton.setText("Next Image");
        backButton.setText("Previous Image");

        vibrator.vibrate(100);

    }

    public void onBackImage(View view){

        if(currentImageIndex == 0) // if at index 0, go back to the front
            currentImageIndex = imageResourceId.size() - 1;

        else
            currentImageIndex = (currentImageIndex - 1) % imageResourceId.size();

        int temp = imageResourceId.get(currentImageIndex);
        imageView.setImageResource(temp);

        nextButton.setText("Next Image");
        backButton.setText("Previous Image");

        vibrator.vibrate(100);

    }

}