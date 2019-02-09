package cf.ibrahimts.criccalcriant;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.JsonWriter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.jar.Attributes;

public class PlayerNamesActivity extends AppCompatActivity {
    private File file;
    private JSONObject json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);

        LinearLayout playerNames = findViewById(R.id.playerNames);
        final EditText[] player = new EditText[11];

        file = new File(getExternalFilesDir(null)+"/playerNames.json");
        boolean fileExist = file.exists();
        json = null;
        if (fileExist){
            try{
                FileInputStream fis = new FileInputStream(file);
                json = new JSONObject(IOUtils.toString(fis,"UTF-8"));
                fis.close();
            } catch(Exception ex){}

        }
        for(int i=0;i<11;i++){
            player[i] = new EditText(this);
            player[i].setHint("Enter Player-"+(i+1)+" Name");
            player[i].setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
            player[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            playerNames.addView(player[i],i);

            if(!fileExist) continue;

            try{
                player[i].setText(json.getString("player"+i));
            } catch (JSONException e){}


            //File file = getExternalFilesDir(null);
            //Log.e("XX",file.getAbsolutePath());
        }

        Button start = findViewById(R.id.btnStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                json = new JSONObject();
                for(int i=0;i<11;i++){
                    try {
                        String str = player[i].getText().toString();
                        Log.e("player"+i,str);
                        if(str.isEmpty()){
                            new AlertDialog.Builder(PlayerNamesActivity.this).setMessage("Missing Argument(s).\nTry Again.").setPositiveButton("Okay",null).show();
                            return;
                        }
                        json.put("player"+i,str);
                    } catch (JSONException e) {
                        //Log.e("EX","ONEX");
                        e.printStackTrace();
                    }
                }

                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(json.toString().getBytes("UTF-8"));
                    fos.close();
                } catch (Exception e){}

                Intent intent = new Intent(PlayerNamesActivity.this,ScoringActivity.class);
                intent.putExtra("jsonObject",getIntent().getStringExtra("jsonObject"));
                //Log.e("JSON",getIntent().getStringExtra("jsonObject"));
                startActivity(intent);
                finish();
            }
        });


    }
}
