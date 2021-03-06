package sg.edu.np.mad.Assignment1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import sg.edu.np.mad.Assignment1.databinding.FragmentVideoBinding;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button eduButton, uploadvidButton, rewardsButton, helpButton;

    public HomeFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        eduButton = (Button) view.findViewById(R.id.button);
        uploadvidButton = (Button) view.findViewById(R.id.button2);
        rewardsButton = (Button) view.findViewById(R.id.button3);
        helpButton = (Button) view.findViewById(R.id.button4);

        eduButton.setOnClickListener(this);
        uploadvidButton.setOnClickListener(this);
        rewardsButton.setOnClickListener(this);
        helpButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).onButtonSelected(v);
    }
}