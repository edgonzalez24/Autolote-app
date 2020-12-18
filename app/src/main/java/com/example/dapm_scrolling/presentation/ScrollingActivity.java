package com.example.dapm_scrolling.presentation;

import android.content.Intent;
import android.os.Bundle;

import com.example.dapm_scrolling.R;
import com.example.dapm_scrolling.presentation.AboutActivity;
import com.example.dapm_scrolling.presentation.HelpActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.snackbar_text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                launchCustomer();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_about:
                    launchAbout();
                break;
            case R.id.action_help:
                launchHelp();
                break;
            case R.id.action_exit:
                launchExit();
                break;
            case R.id.action_search:
                launchSearch();
                break;
            case R.id.action_customer:
                launchCustomer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void launchCustomer() {
        //lanzar activity
        Intent i = new Intent(this, NewActivity.class);
        startActivity(i);
    }

    private void launchSearch() {
        //lanzar activity
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
    }

    private void launchExit() {
        finish();
    }

    private void launchHelp() {
        //lanzar activity
        Intent i = new Intent(this, HelpActivity.class);
        startActivity(i);
    }

    private void launchAbout() {
        //lanzar activity
        Intent i = new Intent(this, AboutActivity.class);
        startActivity(i);
    }

    public void showNew(View view) {
        //lanzar activity
        Intent i = new Intent(this, NewActivity.class);
        startActivity(i);
    }

    public void showList(View view) {
        //lanzar activity
        Intent i = new Intent(this, CustomersListActivity.class);
        startActivity(i);
    }

    public void Exit(View view) {
        //finalizar
        finish();
    }
}