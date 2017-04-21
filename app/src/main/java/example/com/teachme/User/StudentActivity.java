package example.com.teachme.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import example.com.teachme.R;
import example.com.teachme.model.User;

public class StudentActivity extends AppCompatActivity {

    ListView courselv ;
    ArrayList<String>  courseList;
    TextView test ;
    SharedPreferences settings;
    Button logout ;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        courselv = (ListView)findViewById(R.id.courselv);
        courseList = new ArrayList<String>();
        test = (TextView)findViewById(R.id.testdata);
        fillList();

        ArrayAdapter<String> courseAdapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,courseList);
        courselv.setAdapter(courseAdapter);

        Toast.makeText(this,getIntent().getStringExtra("hello"),Toast.LENGTH_SHORT).show();



        courselv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(),"Welcome to "+courseList.get((int)id) ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    public void logout(View view) {
        settings = getSharedPreferences("mySharedPref", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("connected", false);
        editor.apply();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
    void fillList()
    {
        courseList.add("Math");
        courseList.add("Science");
        courseList.add("Programming");
    }



}
