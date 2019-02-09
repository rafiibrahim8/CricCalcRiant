package cf.ibrahimts.criccalcriant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import org.json.JSONException;
import org.json.JSONObject;

public class SetupActivity extends AppCompatActivity {

    EditText opTeam,totalOver,target;
    RadioButton rBatting,ings1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        Button next = findViewById(R.id.btnNext);
        opTeam = findViewById(R.id.opName);
        totalOver = findViewById(R.id.totalOver);
        target = findViewById(R.id.target);
        rBatting = findViewById(R.id.batting);
        ings1 = findViewById(R.id.ings1);
        getPermission();

        //Log.e("CH", getCacheDir().getAbsolutePath());

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAct(ings1.isChecked()? 1:2);
            }
        });
    }

    private void startAct(int innings){

        Intent intent = new Intent(SetupActivity.this,PlayerNamesActivity.class);
        if(opTeam.getText().toString().isEmpty() || totalOver.getText().toString().isEmpty() || (target.getText().toString().isEmpty() && innings==2)){
            new AlertDialog.Builder(SetupActivity.this).setMessage("Missing Argument(s).\nTry Again.").setPositiveButton("Okay",null).show();
        }
        else {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("isBatting",rBatting.isChecked());
                jsonObject.put("team2",opTeam.getText().toString());
                jsonObject.put("tOv",Integer.parseInt(totalOver.getText().toString()));
                jsonObject.put("target",Integer.parseInt(target.getText().toString().isEmpty()?"0":target.getText().toString()));
                jsonObject.put("innings",innings);
            }catch (JSONException ex){}
            intent.putExtra("jsonObject",jsonObject.toString());
            startActivity(intent);
        }
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
