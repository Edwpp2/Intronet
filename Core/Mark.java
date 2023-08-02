package Core;

import java.util.HashMap;
import java.util.Vector;

public class Mark {
    private static final long serialVersionUID = -5688064113814295498L;
    public Vector<Double> firstAtt;
    public Vector<Double> secondAtt;
    public double pointsForFinal;
    public int absenceCount;
    private boolean finalHeld = false;

    public Mark() {
        this.finalHeld = false;
        this.firstAtt = new Vector<Double>();
        this.secondAtt = new Vector<Double>();
        this.absenceCount = 0;
        this.pointsForFinal = 0;
    }
    public void putPointForFirstAtt(Double point){
        firstAtt.add(point);
    }
    public void putPointForSecondAtt(Double point){
        secondAtt.add(point);
    }
    public double getAverageForFirstAtt(){
        double result = 0.0;
        for(Double points : firstAtt){
            result += points;
        }
        return result/firstAtt.size();
    }
    public double getAverageForSecondAtt(){
        double result = 0.0;
        for(Double points : secondAtt){
            result += points;
        }
        return result/secondAtt.size();
    }
    public void putPointsForFinal(Double point){
        finalHeld=true;
        pointsForFinal = point;
    }
    public boolean isRetake()
    {
        return (getAverageForFirstAtt() + getAverageForSecondAtt())/2 < 30 || (pointsForFinal < 20) && finalHeld || absenceCount > 9;
    }
}
