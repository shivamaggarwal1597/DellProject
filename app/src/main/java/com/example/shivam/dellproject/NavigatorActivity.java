package com.example.shivam.dellproject;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shivam.dellproject.dummy.DummyContent;

public class NavigatorActivity extends AppCompatActivity implements ProductListFragment.OnListFragmentInteractionListener{

    private TextView mTextMessage;
    Button add_btn;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //SELL IN FRAGMENT
                    ProductListFragment productListFragment = new ProductListFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,productListFragment);
                    fragmentTransaction.commit();
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //SELL OUT FRAGMENT
                    ProductListFragment productListFragment1 = new ProductListFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction1 = getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame,productListFragment1);
                    fragmentTransaction1.commit();
                    mTextMessage.setText(R.string.title_home);
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    //MY REPORTS FRAGMENT
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        add_btn = (Button)findViewById(R.id.add_btn);
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //TODO : Show the text to add data when no data is present in the list
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : DEPENDING ON WHICH NAVIGATION IS SELECTED, The ITEM is pushed to database
                int id  = navigation.getSelectedItemId();
                Log.e("My ID SELECTED:",id+" ");
                Intent intent = new Intent(NavigatorActivity.this,BarcodeActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
