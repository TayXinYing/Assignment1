package sg.edu.np.mad.Assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
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

        //Action Bar CODES//
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        actionBar.setHomeAsUpIndicator(R.drawable.backbutton_icon);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        //Action Bar CODES//

        //Registering the new user CODES//
        registerEmail = findViewById(R.id.registerEmailText);
        registerPassword = findViewById(R.id.registerPasswordText);
        registerPassword2 = findViewById(R.id.reenterpasswordText);
        registerButton = findViewById(R.id.registerButton);
        loginWord = findViewById(R.id.loginText);
        fAuth1 = FirebaseAuth.getInstance();

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
                    registerPassword.setError("Password must be more than 5 Characters");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    registerPassword2.setError("Please Enter your password");
                    return;
                }

                if(!password2.equals(password)){
                    registerPassword2.setError("Password not the same as above");
                    return;
                }

                //Registering the user in FireBase
                fAuth1.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserRegistration.this, "User Created, you may now login",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserLogin.class));
                        }
                        else{
                            Toast.makeText(UserRegistration.this, "Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        //Registering the new user CODES//


        //Go to LOGIN Page if account exist CODES//
        loginWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
            }
        });
        //Go to LOGIN Page if account exist CODES//
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}