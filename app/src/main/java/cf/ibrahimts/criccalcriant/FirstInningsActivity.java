package cf.ibrahimts.criccalcriant;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FirstInningsActivity extends AppCompatActivity {

    private EditText etScore, etOver, etCRR, etTOv, etOvRem, etBatMan1, etBatMan2, etBallMan,etExtraRun;
    private Button btnBatMan1, btnBatMan2, btnBallMan, btnr0, btnr1, btnr2, btnr3, btnr4, btnr6, btnwdnb, btnout, btnrExtra, btnChS, btnCTC, btnUndo,btnRedo;
    private Calc calc;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_innings);

        clipboardManager=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
        calc=new Calc(getBatTeam(),getIntent().getIntExtra("tOv",20));
        addBTN();
        addET();
        addOCL();
        disableScoreET();
        calc.calculate();
        calc.addLog();
        display();
    }

    private void disableScoreET() {
        disableET(etScore,1);
        disableET(etOver,1);
        disableET(etCRR,1);
        disableET(etTOv,1);
        disableET(etOvRem,1);
        disableET(etExtraRun,1);

    }

    private void addBTN() {
        btnBatMan1 = (Button) findViewById(R.id.i1btnBatMan1);
        btnBatMan2 = (Button) findViewById(R.id.i1btnBatMan2);
        btnBallMan = (Button) findViewById(R.id.i1btnBallMan);
        btnr0 = (Button) findViewById(R.id.i1btnr0);
        btnr1 = (Button) findViewById(R.id.i1btnr1);
        btnr2 = (Button) findViewById(R.id.i1btnr2);
        btnr3 = (Button) findViewById(R.id.i1btnr3);
        btnr4 = (Button) findViewById(R.id.i1btnr4);
        btnr6 = (Button) findViewById(R.id.i1btnr6);
        btnwdnb = (Button) findViewById(R.id.i1btnwdnb);
        btnout = (Button) findViewById(R.id.i1btnout);
        btnrExtra = (Button) findViewById(R.id.i1btnrextra);
        btnChS = (Button) findViewById(R.id.i1btnChS);
        btnCTC = (Button) findViewById(R.id.i1btnCTC);
        btnUndo = (Button) findViewById(R.id.i1btnUndo);
        btnRedo = (Button) findViewById(R.id.i1btnRedo);
    }

    private void addET() {
        etScore = (EditText) findViewById(R.id.i1etScore);
        etOver = (EditText) findViewById(R.id.i1etOver);
        etCRR = (EditText) findViewById(R.id.i1etCRR);
        etTOv = (EditText) findViewById(R.id.i1TOv);
        etOvRem = (EditText) findViewById(R.id.i1OvRem);
        etExtraRun = (EditText) findViewById(R.id.i1etExtraRun);
        etBatMan1 = (EditText) findViewById(R.id.i1etBatMan1);
        etBatMan2 = (EditText) findViewById(R.id.i1etBatMan2);
        etBallMan = (EditText) findViewById(R.id.i1BallMan);
    }

    private void addOCL() {
        btnBatMan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etBatMan1.isEnabled()){
                    calc.setBatMan(etBatMan1.getText().toString(),1);
                    disableET(etBatMan1,0);
                    btnBatMan1.setText(R.string.edit);
                }

                else{
                    etBatMan1.setEnabled(true);
                    etBatMan1.setText(calc.getBatManFull(1).getName());
                    btnBatMan1.setText(R.string.set);
                }
            }
        });

        btnBatMan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etBatMan2.isEnabled()){
                    calc.setBatMan(etBatMan2.getText().toString(),2);
                    disableET(etBatMan2,0);
                    btnBatMan2.setText(R.string.edit);
                }

                else{
                    etBatMan2.setEnabled(true);
                    etBatMan2.setText(calc.getBatManFull(2).getName());
                    btnBatMan2.setText(R.string.set);
                }
            }
        });

        btnBallMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etBallMan.isEnabled()){
                    calc.setBallMan(etBallMan.getText().toString());
                    disableET(etBallMan,0);
                    btnBallMan.setText(R.string.edit);
                }

                else{
                    etBallMan.setEnabled(true);
                    etBallMan.setText(calc.getBallManFull().getName());
                    btnBallMan.setText(R.string.set);
                }
            }
        });

        btnr0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(0,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(1,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(2,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(3,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnr4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(4,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnr6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                calc.updateRun(6,false);
                calc.calculate();
                calc.addLog();
                display();
            }
        });

        btnwdnb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateWdNbRun();
                showWdNbDialog();

            }
        });

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;

                handleOut();
            }
        });

        btnrExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.updateOneBall();
                handleExtraRun();

            }
        });

        btnChS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                calc.changeStrike();
                calc.addLog();
                display();
            }
        });

        btnCTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isArgsEmpty())
                    return;
                String report=getIntent().getStringExtra("team1")+" vs "+getIntent().getStringExtra("team2")+calc.getReport();

                    ClipData clipData= ClipData.newPlainText("Cricket Score",report);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(FirstInningsActivity.this,R.string.copySucc,Toast.LENGTH_SHORT).show();


            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.undo();
                display();
                if(calc.isInningsOver())
                    handleInningsOverButton(true);
            }
        });

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc.redo();
                display();
            }
        });
    }

    private void handleExtraRun() {
        CharSequence items[]=new CharSequence[]{"1","2","3","4","6"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Choose Extra Run");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        calc.updateRun(1,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 1:
                        calc.updateRun(2,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 2:
                        calc.updateRun(3,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 3:
                        calc.updateRun(4,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 4:
                        calc.updateRun(6,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    default:
                        break;
                }

            }
        }).show();
    }

    private void showWdNbDialog() {
        CharSequence items[]=new CharSequence[]{"Run Out","0","1","2","3","4","6"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Choose Extra Out/Run");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        handleRunOut(true);
                        break;
                    case 1:
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 2:
                        calc.updateRun(1,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 3:
                        calc.updateRun(2,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 4:
                        calc.updateRun(3,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 5:
                        calc.updateRun(4,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 6:
                        calc.updateRun(6,true);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    default:
                        break;

                }
            }
        }).show();
    }

    private void handleRunOut(final boolean isExtra) {
        CharSequence items[]=new CharSequence[]{"0","1","2","3","4","6"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Any Run Before Out");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 1:
                        calc.updateRun(1,isExtra);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 2:
                        calc.updateRun(2,isExtra);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 3:
                        calc.updateRun(3,isExtra);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 4:
                        calc.updateRun(4,isExtra);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    case 5:
                        calc.updateRun(6,isExtra);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    default:
                        break;
                }

                handleWhoOut(true);
            }
        }).show();
    }

    private void handleOut() {
        CharSequence items[]=new CharSequence[]{"Run Out","Others"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Choose One");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        calc.updateOneBall();
                        askExtra();
                        break;

                    case 1:
                        // Prev was here calc.updateRun(0,false);
                        int w=calc.getStrike()?1:2;
                        calc.updateOneBall();
                        calc.updateRun(0,false);
                        calc.updateWicket(w,false);
                        if(w==1){
                            etBatMan1.setEnabled(true);
                            btnBatMan1.setText(R.string.set);
                            etBatMan1.getText().clear();
                        }
                        else
                        {
                            etBatMan2.setEnabled(true);
                            btnBatMan2.setText(R.string.set);
                            etBatMan2.getText().clear();
                        }
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    default:
                        break;
                }
            }
        }).show();

    }

    private void askExtra() {
        CharSequence items[]=new CharSequence[]{"Had Bat\'s Man No Run","Had Bat\'s Man Run","Had Extra No Run","Had Extra Run", "Had Extra Run with Wd/Nb","Had Wd/Nb Only"};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Choose One");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        calc.updateRun(0,false);
                        handleWhoOut(true);
                        break;

                    case 1:
                        handleRunOut(false);
                        break;

                    case 2:
                        handleWhoOut(true);
                        break;

                    case 3:
                        handleRunOut(true);
                        break;

                    case 4:
                        calc.updateWdNbRun();
                        handleRunOut(true);
                        break;

                    case 5:
                        calc.updateWdNbRun();
                        handleWhoOut(true);
                        break;
                    default:
                        break;
                }
            }
        }).show();

    }

    private void handleWhoOut(final boolean isRunOut){
        CharSequence items[]=new CharSequence[]{calc.getBatMan(1),calc.getBatMan(2)};
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this);
        builder.setTitle("Who is Out?");
        builder.setCancelable(false);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        etBatMan1.setEnabled(true);
                        btnBatMan1.setText(R.string.set);
                        etBatMan1.getText().clear();
                        calc.updateWicket(1,isRunOut);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;

                    case 1:
                        etBatMan2.setEnabled(true);
                        btnBatMan2.setText(R.string.set);
                        etBatMan2.getText().clear();
                        calc.updateWicket(2,isRunOut);
                        calc.calculate();
                        calc.addLog();
                        display();
                        break;
                    default:
                        break;
                }
            }
        }).show();

    }

    private void display() {
        btnUndo.setEnabled(calc.isUndoAble());
        btnRedo.setEnabled(calc.isRedoAble());

        etScore.setText(calc.getScore());
        etOver.setText(calc.getOver());
        etCRR.setText(calc.getCRR());
        etTOv.setText(calc.getTotalOver());
        etOvRem.setText(calc.getOverRem());
        etExtraRun.setText(calc.getExtraRun());
        etBatMan1.setText(calc.getBatManFull(1).getReport());
        etBatMan2.setText(calc.getBatManFull(2).getReport());
        etBallMan.setText(calc.getBallManFull().getReport());
        if(calc.isNoBallMan()) {
            etBallMan.setEnabled(true);
            etBallMan.getText().clear();
            btnBallMan.setText(R.string.set);
        }
        else
            etBallMan.setText(calc.getBallManFull().getReport());
        if(calc.isInningsOver()) {
            etBallMan.setText(calc.getBallManFull().getReport());
            handleInningsOverButton(false);
            handleInningsOver();
        }

    }

    private void handleInningsOver() {
        AlertDialog.Builder builder=new AlertDialog.Builder(FirstInningsActivity.this).setTitle("Innings Over.\nStart 2nd Innings?");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String report=calc.getInningsReport(getIntent().getStringExtra("team1"),getIntent().getStringExtra("team2"),"");
                ClipData clipData= ClipData.newPlainText("Cricket Score",report);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(FirstInningsActivity.this,R.string.copySucc,Toast.LENGTH_SHORT).show();

                /*
                Collected From:
                        https://stackoverflow.com/questions/8654990/how-can-i-get-current-date-in-android
                        https://stackoverflow.com/questions/26880063/how-to-get-current-date-and-time-in-android
                        By:
                        Several Contributors
                 */
                String date=new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime());

                /*
                    Collected From:
                        https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
                    By:
                    SharkAlley ( https://stackoverflow.com/users/559634/sharkalley )
                    Ulala ( https://stackoverflow.com/users/5645958/ulala )
                 */

                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/CricCalc";
                File dir = new File(path);
                dir.mkdirs();
                File file =new File(path+"/"+getIntent().getStringExtra("team1")+"-"+getIntent().getStringExtra("team2")+"_Ings-1_"+date+".txt");
                if (ContextCompat.checkSelfPermission(FirstInningsActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    save(file, report.replace("\n", "\r\n"));
                }
                /*
                File file=new File(getApplicationContext().getFilesDir(),getIntent().getStringExtra("team1")+"-"+getIntent().getStringExtra("team1")+"_Ings-1_"+date+".txt");
                FileOutputStream stream = null;
                String report=calc.getInningsReport(getIntent().getStringExtra("team1"),getIntent().getStringExtra("team1"),"");
                try {
                    stream = new FileOutputStream(file);
                    try {
                        stream.write(report.getBytes());
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                */



                Intent intent=new Intent(FirstInningsActivity.this,SecondInningsActivity.class);
                intent.putExtra("team1",getIntent().getStringExtra("team1"));
                intent.putExtra("team2",getIntent().getStringExtra("team2"));
                intent.putExtra("tOv",getIntent().getIntExtra("tOv",20));
                intent.putExtra("target",Integer.toString(calc.getRunDone()+1));
                intent.putExtra("whoBatting",(getIntent().getIntExtra("whoBatting",1)==1)?2:1);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No",null);
        builder.setCancelable(false);
        builder.show();
    }

    private void handleInningsOverButton(boolean btnCond) {
        btnBatMan1.setEnabled(btnCond);
        btnBatMan2.setEnabled(btnCond);
        btnBallMan.setEnabled(btnCond);
        btnr0.setEnabled(btnCond);
        btnr1.setEnabled(btnCond);
        btnr2.setEnabled(btnCond);
        btnr3.setEnabled(btnCond);
        btnr4.setEnabled(btnCond);
        btnr6.setEnabled(btnCond);
        btnwdnb.setEnabled(btnCond);
        btnout.setEnabled(btnCond);
        btnChS.setEnabled(btnCond);
        btnrExtra.setEnabled(btnCond);

    }

    private boolean isArgsEmpty(){
        boolean btn= "Set".equalsIgnoreCase(btnBallMan.getText().toString()) || "Set".equalsIgnoreCase(btnBatMan1.getText().toString()) || "Set".equalsIgnoreCase(btnBatMan2.getText().toString());
        if(btn || etBatMan1.getText().toString().isEmpty() || etBatMan2.getText().toString().isEmpty() || etBallMan.getText().toString().isEmpty()){
            new AlertDialog.Builder(FirstInningsActivity.this).setMessage("Missing Argument(s)\nOr, Button is not Set.").setPositiveButton("Okay",null).show();
            return true;
        }
        return false;
    }

    private void disableET(EditText et,int mode){
        /* Source: https://stackoverflow.com/questions/4297763/disabling-of-edittext-in-android
        Answer by: Maksym Kalin ( https://stackoverflow.com/users/2615957/maksym-kalin )
         */

        if(mode==0) {
            et.setEnabled(false);
            et.setBackgroundColor(Color.TRANSPARENT);
            et.setTextColor(Color.BLACK);
        }

        else {
            et.setFocusable(false);
            et.setCursorVisible(false);
            et.setEnabled(false);
            et.setKeyListener(null);
            et.setBackgroundColor(Color.TRANSPARENT);
            et.setTextColor(Color.BLACK);
        }
    }

    private String getBatTeam(){
        if(getIntent().getIntExtra("whoBatting",1)==1)
            return getIntent().getStringExtra("team1");
        return getIntent().getStringExtra("team2");
    }




    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(FirstInningsActivity.this).setMessage("You are about to exit.").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                exitApp();
            }
        }).setNegativeButton("Cancel",null).show();
    }

    private void exitApp(){
        /*
        Collected from:
        https://gist.github.com/CreatorB/99cdb013a4888453b8a0
         */
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.gc();
        System.exit(0);
    }

    private void save(File file, String data)
    {
        /*
        Collected From: Pixel_95
            https://www.youtube.com/watch?v=x3pyyQbwLko
         */
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
        }
        catch (FileNotFoundException e) {e.printStackTrace();}
        try
        {
            try
            {
                fos.write(data.getBytes());
            }
            catch (IOException e) {e.printStackTrace();}
        }
        finally
        {
            try
            {
                fos.close();
            }
            catch (IOException e) {e.printStackTrace();}
        }
    }

}
