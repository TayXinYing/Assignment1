package sg.edu.np.mad.Assignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class FAQHelp extends AppCompatActivity {
    
    RecyclerView recyclerView;
    List<FAQ> faqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqhelp);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

        actionBar.setHomeAsUpIndicator(R.drawable.backbutton_icon);

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        // Display FAQ
        recyclerView = findViewById(R.id.faq_recyclerView);

        initData();
        setRecyclerView();
    }
    
    private void setRecyclerView(){
        FAQAdapter faqAdapter = new FAQAdapter(faqList);
        recyclerView.setAdapter(faqAdapter);
        //recyclerView.setHasFixedSize(true);
    }

    private void initData(){
        faqList = new ArrayList<>();

        faqList.add(new FAQ("What is this application about?", "Recyclops is a platform that allows users to list secondhand items and learn upcycling. Unlike Carousell, which allows people to sell new or old items for cash, our application only allows users to list secondhand items in order to raise awareness on the importance of buying second-hand and upcycling. Users can utilize this platform to buy supplies for their own projects and can also contribute their upcycling suggestions. Finally, through uploaded tutorials, users will be able to learn to upcycle in unique ways."));
        faqList.add(new FAQ("What is the purpose of this application?", "Our application aims to promote upcycling through reusing and second-hand purchases in order to create a more sustainable world."));
        faqList.add(new FAQ("Who is suited to use this application?", "Everyone! This application is developed to educate people of all ages on how to safeguard the environment by upcycling items and the importance of natural resource conservation."));
        faqList.add(new FAQ("How can I contribute my upcycling ideas?", "There is an upload feature where user would be able to upload their upcycling tutorials to share it with other users."));
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
