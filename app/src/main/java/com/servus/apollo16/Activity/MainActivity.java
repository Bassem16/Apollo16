package com.servus.apollo16.Activity;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.servus.apollo16.Beans.User;
import com.servus.apollo16.R;
import com.servus.apollo16.Util.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  {
    RelativeLayout layout = null;
    TextView text = null;
    TextView name = null;
    EditText editTextLogin = null;
    EditText editTextPswd = null;
    Button buttonConnexion= null;
    Button buttonRegister = null;

    private View.OnClickListener clickListenerConnection = new View.OnClickListener() {
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
                JSONObject res = Util.POST(url,user);
                if(res==null)
                    name.setText("Login ou mot de passe erroné");
                else {
                    String testlog = null;
                    try {
                        testlog = res.getString("userPseudo");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    name.setText("Félicitation " + testlog + "! Tu es connecté à ServUS!");
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
