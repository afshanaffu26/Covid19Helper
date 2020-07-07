package com.example.covid19helper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreenActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;
    ImageView imgUser;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private WorldFragment worldFragment;
    private GlobalNewsFragment globalNewsFragment;
    private SettingsFragment settingsFragment;
    private GovtGuidelinesFragment govtGuidelinesFragment;
    private HistoryFragment historyFragment;
    private SymptomAndTreatmentFragment symptomAndTreatmentFragment;
    private SelfAssessmentFragment selfAssessmentFragment;
    TextView drawerUsername,drawerAccount;
    ImageView drawerImage;
    private boolean isInFront;

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;
    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        toolbar=findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Covid-19 Helper");

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        drawerImage = (ImageView) headerView.findViewById(R.id.imageView2);
        drawerUsername = (TextView) headerView.findViewById(R.id.textView3);
        drawerAccount = (TextView) headerView.findViewById(R.id.textView4);

        mMainFrame=findViewById(R.id.main_frame);
        mMainNav=findViewById(R.id.main_nav);
        homeFragment=new HomeFragment();
        worldFragment=new WorldFragment();
        settingsFragment=new SettingsFragment();
        historyFragment = new HistoryFragment();
        selfAssessmentFragment = new SelfAssessmentFragment();
        govtGuidelinesFragment = new GovtGuidelinesFragment();
        globalNewsFragment = new GlobalNewsFragment();
        symptomAndTreatmentFragment = new SymptomAndTreatmentFragment();

        mAuth = FirebaseAuth.getInstance();
        setUserDetails();
        setFragment(homeFragment);

        ActionBarDrawerToggle actionBarDrawerToggle =new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setUserDetails();
            }

        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_home:
                       // mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_world:
                       // mMainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(worldFragment);
                        return true;
                    case R.id.nav_settings:
                      //  mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(settingsFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setUserDetails() {
        FirebaseUser user = mAuth.getCurrentUser();
        drawerUsername.setText("");
        drawerAccount.setText("");
        //drawerImage.setImageDrawable(R.drawable.menu);
        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this).load(user.getPhotoUrl().toString()).into(drawerImage);
            }
            if (user.getDisplayName() != null) {
                String displayName = user.getDisplayName();
                drawerUsername.setText(displayName);
            }
            if (user.getEmail() != null) {
                String emailId = user.getEmail().toString();
                drawerAccount.setText(emailId);
            }
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch(id){
            case R.id.menu1home:
//                if (!isInFront)
//                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
//                else
//                    drawerLayout.closeDrawer(GravityCompat.START);
                setFragment(homeFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu1history:
                setFragment(historyFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu1test:
                setFragment(selfAssessmentFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu1news:
                  //startActivity(new Intent(getApplicationContext(),GlobalNewsActivity.class));

                setFragment(globalNewsFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu2govt:
                new ExpandableTextViewAdapter(this).setGovtGuidelines(true);
                setFragment(govtGuidelinesFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu2symps:
                new ExpandableTextViewAdapter(this).setGovtGuidelines(false);
                setFragment(symptomAndTreatmentFragment);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu3logout:
                userLogout();
                break;
        }
        return true;
    }

    private void userLogout() {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent i= new Intent(this,LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
