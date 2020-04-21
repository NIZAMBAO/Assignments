package com.kingominho.assignments.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kingominho.assignments.R;
import com.kingominho.assignments.adapter.AssignmentAdapter;
import com.kingominho.assignments.entity.Assignment;
import com.kingominho.assignments.ui.EmptyViewRecyclerView;
import com.kingominho.assignments.ui.ItemListDialogFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class NavigationBaseActivity extends AppCompatActivity implements ItemListDialogFragment.Listener {

    private static final String TAG = "NavigationBase: ";
    private BottomAppBar bottomAppBar;
    private EmptyViewRecyclerView recyclerView;
    private ImageView emptyView;
    private FloatingActionButton floatingActionButton;

    int selectedRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_base);
        bottomAppBar = findViewById(R.id.bottom_app_bar);
        emptyView = findViewById(R.id.empty_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        floatingActionButton = findViewById(R.id.fab_add);

        List<Assignment> mAssignmentList= new ArrayList<Assignment>();
        AssignmentAdapter mAssignmentAdapter = new AssignmentAdapter(mAssignmentList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAssignmentAdapter);
        recyclerView.setEmptyView(emptyView);

        this.selectedRadio = R.id.radio_show_completed;

        setSupportActionBar(bottomAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = Color.GREEN;
                mAssignmentList.add(new Assignment("Subnetting", 2 ,"23 Feb, 2021", 0, color));
                mAssignmentAdapter.notifyDataSetChanged();
            }
        });


        bottomAppBar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_dashboard_white_24dp));
        bottomAppBar.setNavigationOnClickListener((view) -> {
            Log.d(TAG, "onClick: BottomAppBar navigation clicked. selectedRadio:" + selectedRadio);
            ItemListDialogFragment itemListDialogFragment = ItemListDialogFragment.newInstance(selectedRadio);
            itemListDialogFragment.show(getSupportFragmentManager(), "tag");
        });

        mAssignmentAdapter.setOnClickListener(new AssignmentAdapter.OnClickListener() {
            @Override
            public void OnClick(Assignment assignment, int position) {
                mAssignmentList.remove(position);
                mAssignmentAdapter.notifyItemRemoved(position);
            }
        });
    }

    @Override
    public void onItemClicked(View view, boolean isChecked) {
        int id = view.getId();
        this.selectedRadio = id;
        Log.d(TAG, "onItemClicked: Radio clicked. selectedRadio: " + this.selectedRadio);
        StringBuilder stringBuilder = new StringBuilder();
        switch (id) {
            case R.id.radio_show_all: {
                stringBuilder.append("Show all ");
                break;
            }
            case R.id.radio_show_completed: {
                stringBuilder.append("Show completed ");
                break;
            }
            case R.id.radio_show_remaining: {
                stringBuilder.append("Show remaining ");
                break;
            }
            default: {
                stringBuilder.append("Default case ");
            }
        }
        stringBuilder.append("clicked. isChecked: ").append(isChecked);

        Toast.makeText(getApplicationContext(), stringBuilder.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_delete_all: {
                Toast.makeText(getApplicationContext(), "Delete All clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.action_about: {
                Toast.makeText(getApplicationContext(), "About clicked", Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
