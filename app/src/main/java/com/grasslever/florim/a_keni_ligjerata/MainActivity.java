package com.grasslever.florim.a_keni_ligjerata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    String[] itemname ={
            "Fakulteti Filozofik",
            "Fakulteti i Filologjise",
            "Fakulteti Ekonomik",
            "Fakulteti Teknik",
            "Fakulteti i Mjekesise",
            "Fakulteti i Edukimit",
            "Fakulteti i Bujqesise dhe Veterinarise",
            "Fakulteti i Shkencave Matematike-Natyrore",
            "Fakulteti Juridik",
            "Fakulteti i Arteve",
            "Fakulteti i Edukimit Fizik dhe i Sportit"
    };

    Integer[] imgid = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
            R.drawable.pic10,
            R.drawable.pic11,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        CustomListAdapter adapter=new CustomListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= itemname[+position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                switch (position)
                {
                    case 1: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 2: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 3: startActivity(new Intent(getApplicationContext(), Kati4Activity.class));
                        break;
                    case 4: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 5: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 6: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 7: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 8: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 9: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 10: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    case 11: Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                        break;
                    default: Toast.makeText(getApplicationContext(), "Wrond item selected!", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }
}
