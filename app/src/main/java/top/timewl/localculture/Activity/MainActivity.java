package top.timewl.localculture.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import top.timewl.localculture.Fragment.FindFragment;
import top.timewl.localculture.Fragment.GuideFragment;
import top.timewl.localculture.Fragment.HomeFragment;
import top.timewl.localculture.Fragment.MyFragment;
import top.timewl.localculture.R;

public class MainActivity extends AppCompatActivity {

    private Button home;
    private TextView home_tv;
    private Button guide;
    private TextView guide_tv;
    private Button my;
    private TextView my_tv;
    private Button find;
    private LinearLayout find_layout;
    private TextView find_tv;
    private LinearLayout home_layout;
    private LinearLayout guide_layout;
    private LinearLayout my_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        listener();
    }

    private void listener() {
        home_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeActivation();
                home.setEnabled(false);
                home_tv.setTextColor(Color.parseColor("#1296db"));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                HomeFragment fileFragment = new HomeFragment();
                transaction.replace(R.id.fragment_container,fileFragment,"file");
                transaction.commit();
            }
        });
        find_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeActivation();
                find.setEnabled(false);
                find_tv.setTextColor(Color.parseColor("#1296db"));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                FindFragment findFragment = new FindFragment();
                transaction.replace(R.id.fragment_container,findFragment,"find");
                transaction.commit();
            }
        });
        guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeActivation();
                guide.setEnabled(false);
                guide_tv.setTextColor(Color.parseColor("#1296db"));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                GuideFragment friendFragment = new GuideFragment();
                transaction.replace(R.id.fragment_container,friendFragment,"friend");
                transaction.commit();
            }
        });
        my_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeActivation();
                my.setEnabled(false);
                my_tv.setTextColor(Color.parseColor("#1296db"));
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                MyFragment myFragment = new MyFragment();
                transaction.replace(R.id.fragment_container,myFragment,"my");
                transaction.commit();
            }
        });

    }

    private void removeActivation() {
        home.setEnabled(true);
        guide.setEnabled(true);
        my.setEnabled(true);
        find.setEnabled(true);
        home_tv.setTextColor(Color.parseColor("#8a8a8a"));
        guide_tv.setTextColor(Color.parseColor("#8a8a8a"));
        my_tv.setTextColor(Color.parseColor("#8a8a8a"));
        find_tv.setTextColor(Color.parseColor("#8a8a8a"));

    }

    private void initView() {
        home_layout = findViewById(R.id.home_layout);
        home = findViewById(R.id.home);
        home_tv = findViewById(R.id.home_tv);
        find_layout = findViewById(R.id.find_layout);
        find = findViewById(R.id.find);
        find_tv = findViewById(R.id.find_tv);
        guide_layout = findViewById(R.id.guide_layout);
        guide = findViewById(R.id.guide);
        guide_tv = findViewById(R.id.guide_tv);
        my_layout = findViewById(R.id.my_layout);
        my = findViewById(R.id.my);
        my_tv = findViewById(R.id.my_tv);
    }
}

