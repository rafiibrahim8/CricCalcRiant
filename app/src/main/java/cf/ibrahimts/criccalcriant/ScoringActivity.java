package cf.ibrahimts.criccalcriant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ScoringActivity extends AppCompatActivity {

    private TextView scoreMain,target,runReq,extraRun,totalOv,ovRem,overDone,crr,rrr;
    private TextView batman1,batman1_run,batman1_ball,batman1_4s,batman1_6s,batman1_sr,batman2,batman2_run,batman2_ball,batman2_4s,batman2_6s,batman2_sr,ballman,ballman_over,ballman_run,ballman_wicket,ballman_maiden,ballman_eco;
    private LinearLayout batManMenu,ballManMenu;
    private Button r0,r1,r2,r3,r4,r5,r6,r7,wd,nb,bye,lbye,wicket,undo,redo,copyToCb;
    private JSONObject setup;
    private String opTeam;
    private Calculate calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        setupViews();
        setupBtnOnClick();
        setUpJsonViews();

    }

    private void setUpJsonViews() {
        try {
            setup = new JSONObject(getIntent().getStringExtra("jsonObject"));
            opTeam = setup.getString("team2");
            if(setup.getInt("innings")==1){
                target.setVisibility(View.INVISIBLE);
                runReq.setVisibility(View.INVISIBLE);
                rrr.setVisibility(View.INVISIBLE);
            }

            if(setup.getBoolean("isBatting")) ballManMenu.setVisibility(View.GONE);
            else batManMenu.setVisibility(View.GONE);
            calc = new Calculate(setup.getInt("tOv"),setup.getInt("innings")==1);

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    private void setupViews() {
        scoreMain = findViewById(R.id.scoreMain);
        target = findViewById(R.id.target);
        runReq = findViewById(R.id.runReq);
        extraRun = findViewById(R.id.extraRun);
        totalOv = findViewById(R.id.totalOv);
        ovRem = findViewById(R.id.ovRem);
        overDone = findViewById(R.id.overDone);
        crr = findViewById(R.id.crr);
        rrr = findViewById(R.id.rrr);

        batManMenu = findViewById(R.id.batManMenu);
        ballManMenu = findViewById(R.id.ballManMenu);

        r0 = findViewById(R.id.r0);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        r5 = findViewById(R.id.r5);
        r6 = findViewById(R.id.r6);
        r7 = findViewById(R.id.r7);
        wd = findViewById(R.id.wd);
        nb = findViewById(R.id.nb);
        bye = findViewById(R.id.bye);
        lbye = findViewById(R.id.lbye);
        wicket = findViewById(R.id.wicket);
        undo = findViewById(R.id.undo);
        redo = findViewById(R.id.redo);
        copyToCb = findViewById(R.id.copyToCb);

        batman1 = findViewById(R.id.batman1);
        batman1_run = findViewById(R.id.batman1_run);
        batman1_ball = findViewById(R.id.batman1_ball);
        batman1_4s = findViewById(R.id.batman1_4s);
        batman1_6s = findViewById(R.id.batman1_6s);
        batman1_sr = findViewById(R.id.batman1_sr);
        batman2 = findViewById(R.id.batman2);
        batman2_run = findViewById(R.id.batman2_run);
        batman2_ball = findViewById(R.id.batman2_ball);
        batman2_4s = findViewById(R.id.batman2_4s);
        batman2_6s = findViewById(R.id.batman2_6s);
        batman2_sr = findViewById(R.id.batman2_sr);
        ballman = findViewById(R.id.ballman);
        ballman_over = findViewById(R.id.ballman_over);
        ballman_run = findViewById(R.id.ballman_run);
        ballman_wicket = findViewById(R.id.ballman_wicket);
        ballman_maiden = findViewById(R.id.ballman_maiden);
        ballman_eco = findViewById(R.id.ballman_eco);

    }

    private void setupBtnOnClick() {
        r0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setScore(String teamName,int run,int wickets){
        scoreMain.setText(teamName+": "+run+"/"+wickets);
    }

    private void setTarget(int run){
        target.setText("Target: "+run);
    }

    private void setExtra(int run){
        extraRun.setText("Extra: "+run);
    }

    private void setRunReq(int run){
        runReq.setText("Run Req: "+run);
    }

    private void setOv(int over){
        overDone.setText("Ov: "+over);
    }

    private void setTotalOv(int over){
        totalOv.setText("Total Ov: "+over);
    }

    private void setOvRem(int over){
        ovRem.setText("Ov Rem: "+over);
    }

    private void setRRR(int rr){
        rrr.setText("RRR: "+rr);
    }

    private void setCRR(int rr){
        crr.setText("CRR: "+rr);
    }
}
