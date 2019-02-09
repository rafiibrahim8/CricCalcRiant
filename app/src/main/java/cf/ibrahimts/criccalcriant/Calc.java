package cf.ibrahimts.criccalcriant;

import android.util.Log;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

public class Calc {
    private Vector <LogData>vector;
    private String batTeam;
    //private BatMan batMan1,batMan2;
    private BallMan[] ballMan;
    private BatMan[] batMan;
    private int target,runDone,extraRun,runReq,totalOver,overDone,ballDone,ballRem,overRem,wicket,ballManCount,currentBallMan,vtSize;
    private int curLogPos;
    private boolean strike,noBallMan;
    private double crr,rrr;
    private int batManPos1,batManPos2,batManCount;

    public Calc(String batting,int tOv){
        target=runDone=extraRun=runReq=overDone=ballDone=ballRem=overRem=wicket=currentBallMan=vtSize=0;
        crr=rrr=0;
        vector=new Vector<LogData>();
        batTeam=batting;
        totalOver=tOv;
        strike=noBallMan=true;
        ballMan=new BallMan[tOv+1]; //+1 because I don't want to crash in final match
        batMan=new BatMan[11];
        batMan[0]=new BatMan();
        batMan[1]=new BatMan();
        batMan[0].setStrike(true);

        batManPos1=0;batManPos2=1;

        ballMan[0]=new BallMan();
        ballManCount=-1;
        batManCount=2;
        curLogPos=-1;
    }

    public void setBatMan(String nam,int which){
        if(which==1)
            batMan[batManPos1].setName(nam);
        else
            batMan[batManPos2].setName(nam);
        handleVector();

    }

    private void handleVector() {
        if(vtSize>=curLogPos)
            for(int i=curLogPos+1;i<vector.size();i++){
                vector.remove(i);
                Log.i("::Loop","Yeah");
            }
        vtSize=vector.size();
        Log.i("::Hand",""+vtSize);

    }

    public void setBallMan(String nam){
        int ix=-1;
        if(ballDone!=0) {
            ballMan[currentBallMan].setName(nam);

            for(int i=0;i<=ballManCount;i++){
                if(ballMan[i].getName().equalsIgnoreCase(nam)){
                    ix=i;
                    break;
                }
            }
            if(ix>-1 && ix!=currentBallMan){
                ballMan[ix].ballPlayed+=ballMan[currentBallMan].ballPlayed;
                ballMan[ix].wicket+=ballMan[currentBallMan].wicket;
                ballMan[ix].maiden+=ballMan[currentBallMan].maiden;
                ballMan[ix].run+=ballMan[currentBallMan].run;
                ballMan[ix].currentOverRun+=ballMan[currentBallMan].currentOverRun;
                currentBallMan=ix;
                ballManCount--;
            }
            return;
        }
        int index=-1;
        noBallMan=false;
        for(int i=0;i<=ballManCount;i++){
            if(ballMan[i].getName().equalsIgnoreCase(nam)){
                index=i;
                break;
            }
        }

        if(index>-1){
            currentBallMan=index;
        }

        else{
            currentBallMan=++ballManCount;
            ballMan[currentBallMan]=new BallMan();
            ballMan[currentBallMan].setName(nam);
        }

        handleVector();
    }
    public void setTarget(String target0){
        target=Integer.parseInt(target0);
    }


    public void updateRun(int x, boolean isExtra){
        runDone+=x;
        if(isExtra){
            extraRun+=x;
        }
        else{
            if(strike)
                batMan[batManPos1].updateRun(x);
            else
                batMan[batManPos2].updateRun(x);
        }
        if(x%2==1)
            changeStrike();
        ballMan[currentBallMan].updateRun(x);
        if(ballDone==0)
            changeStrike();
        handleVector();
    }

    public void updateWicket(int who,boolean isRunOut){
        wicket++;
        if(wicket>10)
            wicket=10;
        if(who==1){
            if(strike==true)
                changeStrike();
            batMan[batManPos1].setOuted();

            if(wicket<10){
                batManPos1=batManCount++;
                batMan[batManPos1]=new BatMan();
            }

        }
        else{
            if(strike==false)
                changeStrike();
            batMan[batManPos2].setOuted();
            if(wicket<10){
                batManPos2=batManCount++;
                batMan[batManPos2]=new BatMan();
            }

        }
        if(!isRunOut)
            ballMan[currentBallMan].updateWicket();
        handleVector();
    }

    public void setTotalOver(String tOver){
        totalOver=Integer.parseInt(tOver);
    }

    public void updateOneBall(){
        ballDone++;
        ballMan[currentBallMan].updateBall();
        if(ballDone>5)
        {
            ballDone=0;
            overDone++;
            noBallMan=true;
        }
        handleVector();
    }

    public void calculate(){
        if(target>0)
            runReq=target-runDone;
        else
            runReq=0;
        int ballRemain=6*(totalOver-overDone)-ballDone;
        overRem=ballRemain/6;
        ballRem=ballRemain%6;
        rrr=6*(double)runReq/ballRemain;
        crr=6*(double)runDone/(overDone*6+ballDone);
    }

    public String getRunReq(){
        return "Run Req: "+runReq;
    }

    public String getScore(){
        return batTeam+": "+runDone+"/"+wicket;
    }

    public String getOver(){
        return "Ov: "+overDone+"."+ballDone;
    }

    public String getOverRem(){
        return "Ov Rem: "+overRem+"."+ballRem;
    }

    public String getCRR(){
        return "CRR: "+getCRRFormated();
    }

    public String getRRR(){
        return "RRR: "+ getRRRFormated();
    }

    public String getExtraRun(){
        return "Extra Run: "+extraRun;
    }

    public String getTarget(){
        return "Target: "+target;
    }

    public int getIntTarget(){
        return target;
    }

    public String getTotalOver(){
        return "Total Ov: "+totalOver;
    }

    public String getReport(){
        StringBuilder sb=new StringBuilder();
        sb.append("\n\n"+getScore());
        sb.append("\n"+getExtraRun());
        if(target>0)
        {
            sb.append("\nTarget: "+target);
            sb.append("\nRun Required: "+runReq);
        }

        sb.append("\nOver: "+overDone+"."+ballDone);
        sb.append("\nOver Remaining: "+overRem+"."+ballRem);

        sb.append("\nCurrent Run Rate: "+getCRRFormated());

        if(target>0)
            sb.append("\nRequired Run Rate: "+getRRRFormated());

        sb.append("\nBating:\n");
        sb.append("\n"+batMan[batManPos1].getReport());
        sb.append("\n"+batMan[batManPos2].getReport());
        sb.append("\nBowling:\n");
        sb.append("\n"+ballMan[currentBallMan].getReport());

        return sb.toString();
    }

    public void addLog(){
        //if(!(new LogData(batMan1,batMan2,ballMan[currentBallMan],runDone,extraRun,overDone,ballDone,wicket,ballManCount,currentBallMan,strike).equals(vector.elementAt(curLogPos)))) {
        curLogPos++;
        if (curLogPos > 9) {
            vector.remove(0);
            curLogPos--;
        }

        LogData ld= null;
        try {
            ld = new LogData((BatMan) batMan[batManPos1].clone(), (BatMan) batMan[batManPos2].clone(), (BallMan) ballMan[currentBallMan].clone(), runDone, extraRun, overDone, ballDone, wicket, ballManCount, currentBallMan, strike,batManPos1,batManPos2,batManCount);
            vector.addElement(ld);
        } catch (CloneNotSupportedException e) {
            Log.e("LogFailed","LAERR"+e.toString());
        }

        vtSize = vector.size();
        Log.i("OnLOg","::AddLog");
        log();

        //}
    }

    public boolean isUndoAble(){
        if (curLogPos<1)
            return false;
        return true;
    }

    public boolean isRedoAble(){
        if (curLogPos>8 || vtSize-1<=curLogPos)
            return false;
        return true;
    }
    public void undo(){
        LogData logData=(LogData)vector.elementAt(--curLogPos);
        this.currentBallMan=logData.currentBallMan;
        this.batManPos1=logData.batManPos1;
        this.batManPos2=logData.batManPos2;
        this.batManCount=logData.batManCount;
        this.batMan[batManPos1]=logData.batMan1;
        this.batMan[batManPos2]=logData.batMan2;
        this.ballMan[currentBallMan]=logData.ballMan;

        this.ballMan[currentBallMan].ballPlayed=logData.ballMan.ballPlayed;

        this.runDone=logData.runDone;
        this.extraRun=logData.extraRun;
        this.overDone=logData.overDone;
        this.ballDone=logData.ballDone;
        this.wicket=logData.wicket;
        this.ballManCount=logData.ballManCount;
        this.strike=logData.strike;
        Log.d("UNDo","::CurrentBallMan"+logData.currentBallMan+"ballP"+logData.ballMan.ballPlayed+"CLP:"+curLogPos);
        calculate();
    }

    public void redo(){
        LogData logData=(LogData)vector.elementAt(++curLogPos);
        this.currentBallMan=logData.currentBallMan;
        this.batManPos1=logData.batManPos1;
        this.batManPos2=logData.batManPos2;
        this.batManCount=logData.batManCount;
        this.batMan[batManPos1]=logData.batMan1;
        this.batMan[batManPos2]=logData.batMan2;
        try {
            this.ballMan[currentBallMan]=(BallMan)logData.ballMan.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.runDone=logData.runDone;
        this.extraRun=logData.extraRun;
        this.overDone=logData.overDone;
        this.ballDone=logData.ballDone;
        this.wicket=logData.wicket;
        this.ballManCount=logData.ballManCount;
        this.strike=logData.strike;
        calculate();
    }

    public void updateWdNbRun() {
        runDone++;
        extraRun++;
        ballMan[currentBallMan].updateRun(1);
    }

    public String getBatMan(int which){
        if(which==1)
            return batMan[batManPos1].getName();
        return batMan[batManPos2].getName();
    }

    public void changeStrike(){
        if(strike)
        {
            strike=false;
            batMan[batManPos1].setStrike(false);
            batMan[batManPos2].setStrike(true);
        }

        else{
            strike=true;
            batMan[batManPos1].setStrike(true);
            batMan[batManPos2].setStrike(false);
        }
    }

    public BatMan getBatManFull(int which){
        if (which==1)
            return batMan[batManPos1];
        return batMan[batManPos2];
    }

    public BallMan getBallManFull(){
        return ballMan[currentBallMan];
    }

    public boolean isNoBallMan() {
        return noBallMan;
    }

    public boolean getStrike() {
        return strike;
    }

    private void log(){
        for(int i=0;i<vector.size();i++){
            LogData ld =(LogData) vector.elementAt(i);
            Log.i("NN","WWX"+ld.ballMan.ballPlayed+"Name"+ld.ballMan.name+"BD: "+ld.ballDone);
        }

    }

    public boolean isInningsOver(){
        if(target>0 && runDone>=target)
            return true;
        return (ballRem==0 && overRem==0) || wicket==10;
    }

    public String getInningsReport(String team1,String team2,String wins) {
        StringBuilder sb=new StringBuilder();
        String lbatteam,lballteam;
        if(team1.equalsIgnoreCase(batTeam)){
            lbatteam=team1;
            lballteam=team2;
        }

        else{
            lbatteam=team2;
            lballteam=team1;
        }
        sb.append(team1+" vs "+team2);

        sb.append("\n"+((target==0)?"1st":"2nd")+" Innings");
        sb.append("\n"+getScore());
        if(!wins.isEmpty()){
            sb.append("\n"+getTarget());
            sb.append("\n"+getWinnerReport(wins));
        }

        sb.append("\n\n"+lbatteam+" Batting:");
        for(int i=0;i<batManCount;i++){
            sb.append("\n"+batMan[i].getFinalReport());
        }
        sb.append("\n\n"+this.getExtraRun());
        sb.append("\nRun Rate: "+getCRRFormated());
        sb.append("\nOver: "+overDone+"."+ballDone);

        sb.append("\n\n"+lballteam+" Bowling:");
        for(int i=0;i<=ballManCount;i++){
            sb.append("\n"+ballMan[i].getReport());
        }
        sb.append("\nWicket: "+wicket);
        sb.append("\nOver: "+overDone+"."+ballDone);
        return sb.toString();


    }

    public int whoWins(){
        if(runDone>=target)
            return 1;
        else if(runDone==target)
            return 0;
        else
            return -1;
    }

    public String getWinnerReport(String wins){
        StringBuilder sb=new StringBuilder();
            if(wins.equalsIgnoreCase(batTeam))
                sb.append(wins+" Wins "+"by "+(10-wicket)+ " Wicket(s)");
            else
                sb.append(wins+" Wins "+"by "+(target-1-runDone)+ " Run(s)");
            return sb.toString();

    }

    public int getRunDone() {
        return runDone;
    }

    private String getRRRFormated(){
        double lrrr;
        if(Double.isNaN(rrr))
            lrrr=0;
        else
            lrrr=rrr;
        return new DecimalFormat("#0.00").format(lrrr);
    }

    private String getCRRFormated(){
        double lcrr;
        if(Double.isNaN(crr))
            lcrr=0;
        else
            lcrr=crr;
        return new DecimalFormat("#0.00").format(lcrr);
    }
}

