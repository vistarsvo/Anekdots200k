package pro.vistar.anekdots200k.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import pro.vistar.anekdots200k.App;
import pro.vistar.anekdots200k.R;
import pro.vistar.anekdots200k.adapters.MenuRecyclerViewAdapter;
import pro.vistar.anekdots200k.data.preferences.AppSharedPreferences;
import pro.vistar.anekdots200k.data.sqlite.cache.CensorSetCache;
import pro.vistar.anekdots200k.data.sqlite.cache.ThemeSetCache;
import pro.vistar.anekdots200k.data.sqlite.entity.CensorEntity;
import pro.vistar.anekdots200k.data.sqlite.entity.ThemeEntity;
import pro.vistar.anekdots200k.utils.DisplayUtils;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private GridLayoutManager lLayout;
    private AppSharedPreferences appSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setTitle(null);
        appSharedPreferences = new AppSharedPreferences(App.getContext());

        initToolBar(); //TODO add Find|Share|Favorite
        initFloatingActionButton(); //TODO action and hide when scroll is max
        initNavigationDrawer(); //TODO Menu items, Nice header
        initCategoriesMenu(); //TODO Icons and design
    }

    /**
     * Init toolBar
     */
    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher_round);
        toolbar.setLogoDescription(getResources().getString(R.string.app_name));
    }

    /**
     * Init an floating button
     */
    private void initFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Init Navigation Drawer menu
     */
    private void initNavigationDrawer() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Build an categories menu grid
     */
    private void initCategoriesMenu() {
        // read prefs or set defaults for menu columns size
        int spanCount = 0;
        int nameFontSize = 12;
        int countFontSize = 9;

        if (DisplayUtils.getOrientationName().equals("LANDSCAPE")) {
            // Set sizes for Landscape|album orientation
            spanCount = appSharedPreferences.getIntSetting(AppSharedPreferences.ALBUM_MENU_COLUMNS);
            nameFontSize = appSharedPreferences.getIntSetting(AppSharedPreferences.ALBUM_MENU_FONT_SIZE, nameFontSize);
            countFontSize = appSharedPreferences.getIntSetting(AppSharedPreferences.ALBUM_MENU_COUNT_FONT_SIZE, countFontSize);
            if (spanCount <= 0) {
                spanCount = DisplayUtils.getDefaultMenuColumns();
                if (spanCount <= 0) spanCount = 1;
                //save default grid size
                appSharedPreferences.setIntSetting(AppSharedPreferences.ALBUM_MENU_COLUMNS, spanCount);
            }
        } else {
            // Set sizes for portrait|others orientation
            spanCount = appSharedPreferences.getIntSetting(AppSharedPreferences.PORTRAIT_MENU_COLUMNS);
            nameFontSize = appSharedPreferences.getIntSetting(AppSharedPreferences.PORTRAIT_MENU_FONT_SIZE, nameFontSize);
            countFontSize = appSharedPreferences.getIntSetting(AppSharedPreferences.ALBUM_MENU_COUNT_FONT_SIZE, countFontSize);
            if (spanCount <= 0) {
                spanCount = DisplayUtils.getDefaultMenuColumns();
                if (spanCount <= 0) spanCount = 1;
                //save default grid size
                appSharedPreferences.setIntSetting(AppSharedPreferences.PORTRAIT_MENU_COLUMNS, spanCount);
            }
        }

        lLayout = new GridLayoutManager(MenuActivity.this, spanCount);

        RecyclerView menuRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        menuRecyclerView.setHasFixedSize(true);
        menuRecyclerView.setLayoutManager(lLayout);

        MenuRecyclerViewAdapter mrcAdapter = new MenuRecyclerViewAdapter(MenuActivity.this, ThemeSetCache.themeEntitiesSet);
        mrcAdapter.setNameFontSize(nameFontSize);
        mrcAdapter.setCountFontSize(countFontSize);
        menuRecyclerView.setAdapter(mrcAdapter);
    }

    /**
     * This exit confirm dialog.
     * If pressed "Yes" then finish activity and exit(0)
     */
    private void showExitCofirm() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_exit_24dp)
                .setTitle(getString(R.string.title_exit))
                .setMessage(getString(R.string.text_exit))
                .setPositiveButton(getString(R.string.Yes), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton(getString(R.string.No), null)
                .show();
    }

    /**
     * Catch backButton pressed and Close NavDrawer Menu
     * If NavDrawer closed then show exit confirm dialog
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            showExitCofirm();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO Menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    /**
     * ToolBar menu actions
     *
     * @param item - selected toolbar menu
     * @return from super
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_exit) {
            showExitCofirm();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Navigate Drawer menu actions
     *
     * @param item - selected menu item in NavDrawer
     * @return always true
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //TODO Menu
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
