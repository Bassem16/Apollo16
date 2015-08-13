package com.servus.apollo16;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layout = null;
    TextView text = null;
    EditText editTextLogin = null;
    EditText editTextPswd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On récupère notre layout par désérialisation. La méthode inflate retourne un View
        // C'est pourquoi on caste (on convertit) le retour de la méthode avec le vrai type de notre layout, c'est-à-dire RelativeLayout
        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);
        // … puis on récupère TextView grâce à son identifiant
        text = (TextView) layout.findViewById(R.id.appName);
        text.setText("ServUs!");
        text.setTextSize(32);
        setContentView(layout);

        editTextLogin = new EditText(this);

        editTextPswd = new EditText(this);

        // On aurait très bien pu utiliser « setContentView(R.layout.activity_main) » bien sûr !
    }

}
