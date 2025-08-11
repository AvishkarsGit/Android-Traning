package com.avishkar.sqlitedatabase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.avishkar.sqlitedatabase.helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        try {
            DatabaseHelper helper = new DatabaseHelper(this);

            helper.addContacts("Avishkar","7376766272");
            helper.addContacts("Abhijeet","7376766337");
            helper.addContacts("Aditya","7376737337");
            helper.addContacts("Atharva","7376734737");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}