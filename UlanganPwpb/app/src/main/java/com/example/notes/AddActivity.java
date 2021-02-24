package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    ActionBar actionBar;
    DatabaseHelper helper;
    EditText TxTitle, TxDesc;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        actionBar = getSupportActionBar();
        actionBar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        TxTitle = (EditText)findViewById(R.id.txTitle_Add);
        TxDesc = (EditText)findViewById(R.id.txDetail_Add);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.save_add:
                String title = TxTitle.getText().toString().trim();
                String detail = TxDesc.getText().toString().trim();

                //Get Date
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDate.format(calendar.getTime());

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.row_title, title);
                values.put(DatabaseHelper.row_note, detail);
                values.put(DatabaseHelper.row_created, created);

                //Create Condition if Title and Detail is empty
                if (title.equals("") && detail.equals("")){
                    Toast.makeText(AddActivity.this, "Nothing to save", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
