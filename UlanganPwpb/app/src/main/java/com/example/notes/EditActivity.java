package com.example.notes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    ActionBar actionBar;
    DatabaseHelper helper;
    EditText TxTitle, TxDesc;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Edit");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        helper = new DatabaseHelper(this);

        id = getIntent().getLongExtra(DatabaseHelper.row_id, 0);

        TxTitle = findViewById(R.id.txTitle_Edit);
        TxDesc = findViewById(R.id.txDetail_Edit);

        getData();
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

    private void getData() {
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_title));
            String detail = cursor.getString(cursor.getColumnIndex(DatabaseHelper.row_note));

            TxTitle.setText(title);
            TxDesc.setText(detail);
        }
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_edit:
                String title = TxTitle.getText().toString().trim();
                String detail = TxDesc.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(DatabaseHelper.row_title, title);
                values.put(DatabaseHelper.row_note, detail);

                if (title.equals("") && detail.equals("")){
                    Toast.makeText(EditActivity.this, "Nothing to save", Toast.LENGTH_SHORT).show();
                }else {
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        switch (item.getItemId()){
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("Are you sure want to delete this note?");
                builder.setCancelable(true);
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}