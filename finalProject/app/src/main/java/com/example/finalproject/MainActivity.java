package com.example.finalproject;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private LinearLayout languagesContainer;
    private LinearLayout contentContainer;
    private List<Pioneer> pioneers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        languagesContainer = findViewById(R.id.languagesContainer);
        contentContainer = findViewById(R.id.contentContainer);

        pioneers = readPioneersFromCSV();
        Collections.sort(pioneers, new PioneerComparator());

        populateLanguages();
    }

    private List<Pioneer> readPioneersFromCSV() {
        List<Pioneer> pioneers = new ArrayList<>();

        try {
            Resources resources = getResources();
            InputStream inputStream = resources.openRawResource(R.raw.pioneers);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String country = parts[1].trim();
                    String bio = parts[3].trim();
                    String photoFileName = parts[4].trim();
                    int photoResId = resources.getIdentifier(photoFileName, "drawable", getPackageName());

                    Pioneer pioneer = new Pioneer(name, country, bio, photoFileName);
                    pioneers.add(pioneer);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pioneers;
    }


    private void populateLanguages() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (final Pioneer pioneer : pioneers) {
            Button languageButton = (Button) inflater.inflate(R.layout.language_button, languagesContainer, false);
            languageButton.setText(pioneer.getName());
            languageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPioneerDetails(pioneer);
                }
            });

            languagesContainer.addView(languageButton);
        }
    }

    private void showPioneerDetails(Pioneer pioneer) {
        contentContainer.removeAllViews();

        View detailsView = LayoutInflater.from(this).inflate(R.layout.pioneers_details, contentContainer, false);

        TextView nameTextView = detailsView.findViewById(R.id.nameTextView);
        TextView countryTextView = detailsView.findViewById(R.id.countryTextView);
        TextView bioTextView = detailsView.findViewById(R.id.bioTextView);
        ImageView photoImageView = detailsView.findViewById(R.id.photoImageView);

        nameTextView.setText(pioneer.getName());
        countryTextView.setText(pioneer.getCountry());
        bioTextView.setText(pioneer.getBio());

        // Obtain the drawable resource ID dynamically based on the pioneer's photo file name
        String photoFileName = pioneer.getPhotoFileName();
        int photoResId = getResources().getIdentifier(photoFileName.split("\\.")[0], "drawable", getPackageName());

        if (photoResId != 0) {
            photoImageView.setImageResource(photoResId);
        } else {
            // If the photoResId is 0, there might be an issue with the image file name or location
            Log.e("Image Error", "Invalid resource ID for photo: " + photoFileName);
        }

        contentContainer.addView(detailsView);
        contentContainer.setVisibility(View.VISIBLE);
    }

    private class PioneerComparator implements Comparator<Pioneer> {
        @Override
        public int compare(Pioneer p1, Pioneer p2) {
            return p1.getName().compareToIgnoreCase(p2.getName());
        }
    }
}