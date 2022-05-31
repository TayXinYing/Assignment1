package sg.edu.np.mad.Assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserRegistration extends AppCompatActivity {
    EditText registerEmail, registerPassword, registerPassword2;
    Button registerButton;
    TextView loginWord;
    FirebaseAuth fAuth1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        registerEmail = findViewById(R.id.registerEmailText);
        registerPassword = findViewById(R.id.registerPasswordText);
        registerPassword2 = findViewById(R.id.reenterpasswordText);
        registerButton = findViewById(R.id.registerButton);
        loginWord = findViewById(R.id.loginText);
        fAuth1 = FirebaseAuth.getInstance();

        //if(fAuth.getCurrentUser() != null){
        //    startActivity(new Intent(getApplicationContext(), UserLogin.class));
        //    finish();
        //}

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = registerEmail.getText().toString();
                String password = registerPassword.getText().toString();
                String password2 = registerPassword2.getText().toString();

                if(TextUtils.isEmpty(email)){
                    registerEmail.setError("Username is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    registerPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    registerPassword.setError("Password must be more than 6 Characters");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    registerPassword2.setError("Re-Enter your password");
                    return;
                }

                //Registering the user in FireBase
                fAuth1.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserRegistration.this, "User Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserLogin.class));
                        }
                        else{
                            Toast.makeText(UserRegistration.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        loginWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserLogin.class));

            }
        });
    }
}