package com.servus.apollo16.Activity;


import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.servus.apollo16.Beans.User;
import com.servus.apollo16.R;
import com.servus.apollo16.Util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    private RelativeLayout layout = null;
    private TextView text = null;
    private TextView name = null;
    private EditText editTextLogin = null;
    private EditText editTextPswd = null;
    private Button buttonConnexion= null;
    private Button buttonRegister = null;

    private final View.OnClickListener clickListenerConnection = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int SDK_INT = android.os.Build.VERSION.SDK_INT;
            if (SDK_INT > 8)
            {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                        .permitAll().build();
                StrictMode.setThreadPolicy(policy);
                String login = editTextLogin.getText().toString();
                String password = editTextPswd.getText().toString();
                User user = new User(password,login);

                String url = "http://10.0.2.2:5000/servus/user";
                name=(TextView) layout.findViewById(R.id.textViewTEST);

                JSONObject res = Util.postUser(url,user);

                if(res==null)
                    name.setText("Login ou mot de passe erroné");
                else {
                    user =new User(res);
                    name.setText("Félicitation " + user.getLogin() + "! Tu es connecté à ServUS!");
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slideright, R.anim.slideright2);
                    finish();
                }
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On récupère notre layout par désérialisation. La méthode inflate retourne un View
        // C'est pourquoi on caste (on convertit) le retour de la méthode avec le vrai type de notre layout, c'est-à-dire RelativeLayout
        layout = (RelativeLayout) RelativeLayout.inflate(this, R.layout.activity_main, null);
        // … puis on récupère TextView grâce à son identifiant
        text = (TextView) layout.findViewById(R.id.appName);
        text.setTextSize(42);
        setContentView(layout);

        editTextLogin = (EditText) layout.findViewById(R.id.login);

        editTextPswd = (EditText) layout.findViewById(R.id.password);

        buttonConnexion = (Button) layout.findViewById(R.id.button_connexion);
        buttonConnexion.setOnClickListener(clickListenerConnection);



        buttonRegister = (Button) layout.findViewById(R.id.button_register);
        // On aurait très bien pu utiliser « setContentView(R.layout.activity_main) » bien sûr !
    }
}
