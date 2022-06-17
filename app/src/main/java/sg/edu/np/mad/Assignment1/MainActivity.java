package sg.edu.np.mad.Assignment1;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_launcher_round);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'> Recyclops </font>"));


        bottomNavigationView = findViewById(R.id.bottomnavbar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    HomeFragment homeFragment = new HomeFragment();
    VideoFragment videoFragment = new VideoFragment();
    UploadFragment uploadFragment = new UploadFragment();
    RewardsFragment rewardsFragment = new RewardsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                return true;

            case R.id.video:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, videoFragment).commit();
                return true;

            case R.id.upload:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, uploadFragment).commit();
                return true;

            case R.id.reward:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, rewardsFragment).commit();
                return true;

            case R.id.userprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
                return true;
        }
        return false;
    }
}