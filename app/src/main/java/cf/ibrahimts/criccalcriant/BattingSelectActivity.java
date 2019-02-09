package cf.ibrahimts.criccalcriant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BattingSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batting_select);
        getPermission();
        Button btnBS1 = (Button) findViewById(R.id.btnBS1);
        Button btnBS2 = (Button) findViewById(R.id.btnBS2);
        btnBS1.setText(getIntent().getStringExtra("team1"));
        btnBS2.setText(getIntent().getStringExtra("team2"));

        btnBS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAc(true);
            }
        });

        btnBS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAc(false);
            }
        });
    }

    private void startAc(boolean isFirstButton){
        Intent intent;

        if(getIntent().getIntExtra("innings",0)==1)
            intent=new Intent(BattingSelectActivity.this,FirstInningsActivity.class);
        else
            intent=new Intent(BattingSelectActivity.this,SecondInningsActivity.class);

        if(isFirstButton)
            intent.putExtra("whoBatting",1);
        else
            intent.putExtra("whoBatting",2);
        intent.putExtra("team1",getIntent().getStringExtra("team1"));
        intent.putExtra("team2",getIntent().getStringExtra("team2"));
        intent.putExtra("tOv",Integer.parseInt(getIntent().getStringExtra("tOv")));
        intent.putExtra("target",getIntent().getStringExtra("target"));
        startActivity(intent);
    }

    private void getPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }
}
