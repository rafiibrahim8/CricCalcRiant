package cf.ibrahimts.criccalcriant;

import android.util.Log;

/**
 * Created by Ibrahim on 05-Mar-18.
 */

public class LogData{
    public BatMan batMan1,batMan2;
    public BallMan ballMan;
    public boolean strike;
    public int runDone, extraRun, overDone, ballDone, wicket, ballManCount, currentBallMan,batManPos1,batManPos2,batManCount;

    public LogData(BatMan batMan1,BatMan batMan2,BallMan ballMan,int runDone,int extraRun,int overDone,int ballDone,int wicket,int ballManCount, int currentBallMan,boolean strike,int batManPos1,int batManPos2,int batManCount){
        this.batMan1=batMan1;
        this.batMan2=batMan2;
        this.ballMan=ballMan;
        this.runDone=runDone;
        this.extraRun=extraRun;
        this.overDone=overDone;
        this.ballDone=ballDone;
        this.wicket=wicket;
        this.ballManCount=ballManCount;
        this.currentBallMan=currentBallMan;
        this.strike=strike;
        this.batManCount=batManCount;
        this.batManPos1=batManPos1;
        this.batManPos2=batManPos2;

        Log.i("OnLDC","::x"+this.ballMan.ballPlayed);
    }


}
