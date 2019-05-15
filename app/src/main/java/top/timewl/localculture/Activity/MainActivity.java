package top.timewl.localculture.Activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        listener();
    }

    private void listener() {
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        home_tv.setTextColor(Color.parseColor("#8a8a8a"));
        guide_tv.setTextColor(Color.parseColor("#8a8a8a"));
        my_tv.setTextColor(Color.parseColor("#8a8a8a"));

    }

    private void initView() {
        home = findViewById(R.id.home);
        home_tv = findViewById(R.id.home_tv);
        guide = findViewById(R.id.guide);
        guide_tv = findViewById(R.id.guide_tv);
        my = findViewById(R.id.my);
        my_tv = findViewById(R.id.my_tv);
    }
}

