package cf.ibrahimts.criccalcriant;

import java.text.DecimalFormat;

public class BallMan implements Cloneable{
    public String name;
    public int run,wicket,ballPlayed,maiden;
    public int currentOverRun;
    public void setName(String name1){
        name=name1;
    }

    public String getName(){
        return name;
    }

    public String getEcon(){
        double ecn;
        if(ballPlayed==0 && run==0)
            ecn=0;
        else
            ecn=(double) (run*6)/ballPlayed;;
        return "ECN: "+new DecimalFormat("#0.0").format(ecn);
    }

    private String overPlayed(){
        return String.valueOf(ballPlayed/6+"."+ballPlayed%6);
    }

    public String getReport(){
        if(name.isEmpty())
            return "";
        return name+"-"+run+"("+overPlayed()+")-"+wicket+"W-"+maiden+"M-"+getEcon();
    }

    public BallMan(){
        name="";
        run=wicket=ballPlayed=currentOverRun=0;
    }

    public void updateWicket(){
        wicket++;
    }

    public void updateRun(int x) {
        run+=x;
        currentOverRun+=x;
    }

    public void updateBall() {
        ballPlayed++;
        if(ballPlayed%6==0){
            if(currentOverRun==0)
                maiden++;
            currentOverRun=0;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
