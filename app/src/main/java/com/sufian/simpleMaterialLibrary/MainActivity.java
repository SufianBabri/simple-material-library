package com.sufian.simpleMaterialLibrary;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.simple.material.FlatButton;
import com.simple.material.RaisedButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		final RaisedButton btnLight = (RaisedButton) findViewById(R.id.btnLight);
		final RaisedButton btnDark1 = (RaisedButton) findViewById(R.id.btnDark1);
		final RaisedButton btnDark2 = (RaisedButton) findViewById(R.id.btnDark2);
        final RaisedButton btnMore = (RaisedButton) findViewById(R.id.btnMore);
        final RaisedButton btn1 = (RaisedButton) findViewById(R.id.btn1);
        final RaisedButton btn2 = (RaisedButton) findViewById(R.id.btn2);
        final FlatButton btn3 = (FlatButton) findViewById(R.id.btn3);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				btnLight.setEnabled(checkBox.isChecked());
				btnDark1.setEnabled(checkBox.isChecked());
				btnDark2.setEnabled(checkBox.isChecked());
				btnMore.setEnabled(checkBox.isChecked());
				btn1.setEnabled(checkBox.isChecked());
				btn2.setEnabled(checkBox.isChecked());
				btn3.setEnabled(checkBox.isChecked());
			}
        });
		View.OnClickListener onClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
			}
		};
		btnLight.setOnClickListener(onClickListener);
        btnDark1.setOnClickListener(onClickListener);
		btnDark2.setOnClickListener(onClickListener);
        btnMore.setOnClickListener(onClickListener);
        btn1.setOnClickListener(onClickListener);
        btn2.setOnClickListener(onClickListener);
        btn3.setOnClickListener(onClickListener);
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
        if (id == R.id.action_about) {
			new MaterialDialog.Builder(this)
					.title(R.string.action_about)
					.content(Html.fromHtml(getString(R.string.description, getVersionCode())))
					.positiveText(R.string.visit_github)
					.negativeText(R.string.close)
					.onPositive(new MaterialDialog.SingleButtonCallback() {
						@Override
						public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
							String url = "https://github.com/SufianBabri/simple-material-library";
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse(url));
							if (intent.resolveActivity(getPackageManager()) == null) {
								Toast.makeText(MainActivity.this, "Do you really have no web browser on your device?", Toast.LENGTH_LONG).show();
							}
							else {
								startActivity(intent);
							}
						}
					})
					.show();
			return true;
        }

        return super.onOptionsItemSelected(item);
    }

	private String getVersionCode() {
		try {
			return getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		}
		catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
}
