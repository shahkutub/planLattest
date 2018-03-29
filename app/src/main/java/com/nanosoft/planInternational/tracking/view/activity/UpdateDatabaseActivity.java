package com.nanosoft.planInternational.tracking.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.nanosoft.planInternational.tracking.R;
import com.nanosoft.planInternational.tracking.database.manager.DatabaseManager;
import com.nanosoft.planInternational.tracking.utility.Operation;

public class UpdateDatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_update);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cleanDatabase();
        logout();
    }

    private void logout() {
        new Operation(this).LogOut();
        finish();
    }

    private void cleanDatabase() {
        DatabaseManager mgr = new DatabaseManager(this);
        mgr.DeleteAllTables();
        mgr.createTables();
    }

}
