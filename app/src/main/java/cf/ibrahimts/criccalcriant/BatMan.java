package cf.ibrahimts.criccalcriant;

import java.text.DecimalFormat;

public class BatMan implements Cloneable{
    public String name;
    private String out;
    private String strike;
    public int run,four,six,ballPlayed;
    public void setName(String name1){
        name=name1;
        run=four=six=ballPlayed=0;
    }

    public String getName(){
        return name;
    }

    public String getSR(){
        double lsr;
        if(ballPlayed==0 && run==0)
            lsr=0;
        else
            lsr=(double) (run*100)/ballPlayed;;
        return "SR: "+new DecimalFormat("#0.0").format(lsr);
    }

    private String overPlayed(){
        return ballPlayed/6+"."+ballPlayed%6;
    }

    public String getReport(){
        if(name.isEmpty())
            return "";
        return strike+name+"-"+run+"("+overPlayed()+")"+"-6("+six+")"+"-4("+four+")-"+getSR();
    }

    public String getFinalReport(){
        if(name.isEmpty())
            return "";
        return name+out+"-"+run+"("+overPlayed()+")"+"-6("+six+")"+"-4("+four+")-"+getSR();
    }

    public BatMan(){
        name="";
        out="*";
        run=four=six=ballPlayed=0;
        strike="";
    }
    public void setOuted(){
        out="";
    }

    public void setStrike(boolean arg){
        if(arg)
            strike="*";
        else strike="";
    }

    public void updateRun(int run0){
        run+=run0;
        ballPlayed++;
        if(run0==4)
            four++;
        else if(run0==6)
            six++;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

