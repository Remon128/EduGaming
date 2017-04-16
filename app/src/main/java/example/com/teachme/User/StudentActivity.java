package example.com.teachme.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import example.com.teachme.R;

public class StudentActivity extends AppCompatActivity {

    ListView courselv ;
    ArrayList<String>  courseList;
    TextView test ;
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

        Intent i = getIntent();
        String json = i.getStringExtra("json");

        test.setText(json);
        courselv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getBaseContext(),"Welcome to "+courseList.get((int)id) ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    void fillList()
    {
        courseList.add("Math");
        courseList.add("Science");
        courseList.add("Programming");
    }

}
