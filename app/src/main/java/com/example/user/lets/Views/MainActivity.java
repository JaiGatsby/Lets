package com.example.user.lets.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.lets.Adapter.TabFragmentPagerAdapter;
import com.example.user.lets.Chactivity;
import com.example.user.lets.R;
import com.example.user.lets.Timetable_Planner;
import com.example.user.lets.Utility.ApiClient;
import com.example.user.lets.Utility.OkHttpSingleton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        prefs = this.getSharedPreferences("com.example.user.lets", Context.MODE_PRIVATE);

        final String[] colors = getResources().getStringArray(R.array.default_preview);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_event_white_24dp),
                        Color.parseColor(colors[4])
                ).title("Events")
                        .badgeTitle("Events")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_chat_white_24dp),
                        Color.parseColor(colors[2])
                ).title("Chat")
                        .badgeTitle("Chat")
                        .build()
        );

        navigationTabBar.setModels(models);
        //navigationTabBar.setViewPager(viewPager, 2);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setBadgeBgColor(Color.RED);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBgColor(Color.BLACK);
        navigationTabBar.setBadgeSize(10);
        navigationTabBar.setTitleSize(30);
        navigationTabBar.setIconSizeFraction(0.6f);
        navigationTabBar.setViewPager(viewPager);


        ApiClient.getRecommended(prefs.getString("UserKey", "default")).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG,"Unable to get recommended");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Received recommended activities", Toast.LENGTH_LONG).show();
                        }
                    });

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
            case R.id.action_change_interest:
                startActivity( new Intent(this, InterestQuestions.class));
                break;
            case R.id.action_change_timetable:
                startActivity(new Intent(this, Timetable_Planner.class));
                break;
        }
        return true;
    }
}
