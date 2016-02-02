package com.material;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.simple.material.RaisedButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		final RaisedButton btnLight = (RaisedButton) findViewById(R.id.btnLight);
		final RaisedButton btnDark1 = (RaisedButton) findViewById(R.id.btnDark1);
		final RaisedButton btnDark2 = (RaisedButton) findViewById(R.id.btnDark2);
		final RaisedButton btnDark3 = (RaisedButton) findViewById(R.id.btnDark3);
		final RaisedButton btnDark4 = (RaisedButton) findViewById(R.id.btnDark4);
        final RaisedButton btnMore = (RaisedButton) findViewById(R.id.btnMore);
        final RaisedButton btnEmpty = (RaisedButton) findViewById(R.id.btnEmpty);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLight.setEnabled(checkBox.isChecked());
				btnDark1.setEnabled(checkBox.isChecked());
				btnDark2.setEnabled(checkBox.isChecked());
				btnDark3.setEnabled(checkBox.isChecked());
				btnDark4.setEnabled(checkBox.isChecked());
				btnMore.setEnabled(checkBox.isChecked());
                btnEmpty.setEnabled(checkBox.isChecked());
            }
        });
		btnLight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "light button clicked", Toast.LENGTH_SHORT).show();
			}
		});
        btnDark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "dark button clicked", Toast.LENGTH_SHORT).show();
            }
        });
		btnDark2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "dark button clicked", Toast.LENGTH_SHORT).show();
			}
		});
		btnDark3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "dark button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnDark4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "dark button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "more button clicked", Toast.LENGTH_SHORT).show();
            }
        });
        btnEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "empty button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
