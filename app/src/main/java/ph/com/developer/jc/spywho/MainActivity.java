package ph.com.developer.jc.spywho;

import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expandableListView;
    List<String> listHeader;
    HashMap<String, List<String>> listChild;

    android.support.v7.widget.Toolbar toolbar;

    DrawerLayout drawerLayout;
    LinearLayout leftDrawer;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_action_drawer));

        expandableListView = (ExpandableListView) findViewById(R.id.elvMenu);
        prepareExpandableListView();
        listAdapter = new ExpandableListAdapter(this, listHeader, listChild);

        expandableListView.setAdapter(listAdapter);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        leftDrawer = (LinearLayout) findViewById(R.id.leftDrawer);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                drawerLayout.closeDrawer(leftDrawer);
                if(expandableListView.isGroupExpanded(groupPosition)){
                    expandableListView.collapseGroup(groupPosition);
                }
                else{
                    expandableListView.expandGroup(groupPosition, true);
                }
                return true;
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    private void prepareExpandableListView(){
        listHeader = new ArrayList<String>();
        listChild = new HashMap<String, List<String>>();

        listHeader.add("PERSONAL FILES");
        listHeader.add("TODO LIST");
        listHeader.add("FOR APPROVAL");

        List<String> personalFiles = new ArrayList<String>();
        personalFiles.add("PERSONAL INFORMATION");
        personalFiles.add("LEAVE STATUS");
        personalFiles.add("DAILY TIME RECORD");
        personalFiles.add("DTR - EMPLOYEES");
        personalFiles.add("PAYSLIP VIEWING");
        personalFiles.add("CHANGE PAYSLIP PASSWORD");
        personalFiles.add("DIRECTORY");

        List<String> todoList = new ArrayList<String>();
        todoList.add("SUBMITTED LIST");

        List<String> forApproval = new ArrayList<String>();
        forApproval.add("OFFICIAL BUSINESS");
        forApproval.add("LEAVE FORM");
        forApproval.add("OFFSET");
        forApproval.add("OVERTIME");

        listChild.put(listHeader.get(0), personalFiles);
        listChild.put(listHeader.get(1), todoList);
        listChild.put(listHeader.get(2), forApproval);
    }
}
