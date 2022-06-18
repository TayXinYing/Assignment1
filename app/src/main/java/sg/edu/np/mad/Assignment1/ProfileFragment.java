package sg.edu.np.mad.Assignment1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileFragment extends Fragment {

    private TextView username, email;

    DatabaseReference mDatabase;

    public ProfileFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        username = (TextView) view.findViewById(R.id.textView8);
        email = (TextView) view.findViewById(R.id.textView9);

        mDatabase = FirebaseDatabase.getInstance("https://mad-assignment-1-7b524-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

        mDatabase.child("Users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    username.setText(String.valueOf(task.getResult().child("username").getValue()));
                    email.setText(String.valueOf(task.getResult().child("email").getValue()));
                }
            }
        });
        return view;
    }
}