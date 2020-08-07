package com.accounting.ant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class UserOptions extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_options);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> startActivity(new Intent(this,UsersFeedback.class)));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);


        //dialog for log out option
        myDialog = new Dialog(this);
        myDialog.setCancelable(false);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_dashboard, R.id.nav_wallet, R.id.nav_creditFirst,
                R.id.nav_transHistory, R.id.nav_agent)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_options, menu);   
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();

        if (menuId == R.id.action_profile) {
            Intent intent = new Intent(this, UsersProfile.class);
            startActivity(intent);
        } else if (menuId == R.id.action_about) {
            Intent intent = new Intent(this, AboutApplication.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //method to show dialog for log out and controls every component in the dialog /**N.b onclick listener was done in the layout **/
    public void showLogOutDialog(View view) {
        myDialog.setContentView(R.layout.activity_logout);
        myDialog.show();
        //finding the buttons Id
        Button button = myDialog.findViewById(R.id.dialogCancel);
        button.setOnClickListener(V -> cancelLogOutDialog());
        //----------------------------------------------------------

    }

    //method for log out cancel dialog
    public void cancelLogOutDialog() {
        myDialog.dismiss();
    }


}
