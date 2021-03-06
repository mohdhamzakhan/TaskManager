package com.example.admin.projectacad;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.projectacad.adapter.taskAdapter;
import com.example.admin.projectacad.businessLogic.Resource;
import com.example.admin.projectacad.businessLogic.To_Do;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class Task_completed extends AppCompatActivity {

    private Resource mResource;
    private taskAdapter mTaskAdapter;
    private ListView mTaskListView;
    private ArrayList<To_Do> mTasks;

    private void restart()
    {
        mResource = new Resource(getApplicationContext());
        mTasks = mResource.GetResultDatabase(1);
        if(mTasks.size()>0) {
            mTaskListView = (ListView) findViewById(R.id.listView);
            mTaskAdapter = new taskAdapter(getApplicationContext(), R.layout.item_task, mTasks);
            mTaskListView.setAdapter(mTaskAdapter);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restart();
        mTaskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                To_Do to_do= (To_Do) mTaskAdapter.getItem(position);
                mResource.DeleteDatabase(to_do.getID());
                Log.e("deleted","deleted");
                Toast.makeText(getApplicationContext(),"Values has been Deleted", Toast.LENGTH_SHORT).show();
                restart();
                return false;
            }
        });
        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                To_Do to_do= (To_Do) mTaskAdapter.getItem(position);
                mResource.UpdateDatabase(to_do.getID(),0);
                Log.e("updated","updated");
                Toast.makeText(getApplicationContext(),"Values has been Updated", Toast.LENGTH_SHORT).show();
                restart();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.menu_new:
                Log.e("MY LOG", "MENU BUTTON CLICK");
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Enter the details");
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                final View dialogLayout = layoutInflater.inflate(R.layout.task_add, null, false);
                alertDialog.setView(dialogLayout);
                final EditText Titletxt = (EditText) dialogLayout.findViewById(R.id.txtTitle);
                final EditText Desctxt = (EditText) dialogLayout.findViewById(R.id.txtDescription);
                final DatePicker calView = (DatePicker) dialogLayout.findViewById(R.id.calDate);
                Button okayButton = (Button) dialogLayout.findViewById(R.id.btnSave);
                Button cancelButton = (Button) dialogLayout.findViewById(R.id.btnCancle);
                DatePicker clV = (DatePicker) dialogLayout.findViewById(R.id.calDate);
                okayButton.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {
                                                      String month;
                                                      String date;
                                                      if(calView.getMonth()<9) {
                                                          month = "0" + String.valueOf(calView.getMonth() + 1);
                                                      }
                                                      else {
                                                          month = String.valueOf(calView.getMonth() + 1);
                                                      }
                                                      if(calView.getDayOfMonth()<10)
                                                      {
                                                          date="0"+String.valueOf(calView.getDayOfMonth());
                                                      }
                                                      else {
                                                          date = String.valueOf(calView.getDayOfMonth());
                                                      }
                                                      To_Do to_do = new To_Do(0, Titletxt.getText().toString(), Desctxt.getText().toString(), String.valueOf(calView.getYear() + "-" + month + "-" + date), 0);
                                                      mResource.InsertDatabase(to_do);
                                                      Toast.makeText(getApplicationContext(),"Values has been Inserted", LENGTH_SHORT).show();
                                                      restart();
                                                  }
                                              }
                );
                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                alertDialog.show();
                return true;
            case R.id.menu_status:
                Intent statusactivity=new Intent(Task_completed.this,MainActivity.class);
                startActivity(statusactivity);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        MenuItem mi= menu.findItem(R.id.menu_status);
        mi.setIcon(R.mipmap.incomplete);
        return true;
    }
}
