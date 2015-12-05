package com.firebaseapp.interestmatcher.interestmatcher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.firebase.client.Firebase;
import com.firebaseapp.interestmatcher.interestmatcher.ChatRoom.chatRoomFragment;
import com.firebaseapp.interestmatcher.interestmatcher.Posts.postsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    public static final String sharedPrefsName = "FBLoginPrefs";
    private SharedPreferences.Editor editor;
    public static String userName, id;
    private TextView navDrawerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();

        userName = getSharedPreferences(sharedPrefsName, MODE_PRIVATE).getString("userName", "name");
        id = getSharedPreferences(sharedPrefsName, MODE_PRIVATE).getString("id", "id");

        editor = getSharedPreferences(sharedPrefsName, MODE_PRIVATE).edit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

        navDrawerName = (TextView) headerView.findViewById(R.id.navDrawerName);
        setUpNavDrawerContent();

        swapFragment(new postsFragment());
    }

    private void setUpNavDrawerContent(){
        navDrawerName.setText(userName);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void swapFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.content_holder, fragment).commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_posts) {
            swapFragment(new postsFragment());
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_profile) {
            swapFragment(new profileFragment());
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_map) {
            swapFragment(new mapFragment());
            drawer.closeDrawer(GravityCompat.START);
        }else if (id == R.id.nav_chatroom){
            swapFragment(new chatRoomFragment());
            drawer.closeDrawer(GravityCompat.START);
        }else if(id == R.id.nav_logout){
            logout();
        }

        return true;
    }

    private void logout(){
        LoginManager.getInstance().logOut();
        editor.putBoolean("hasLoggedIn", false).commit();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
