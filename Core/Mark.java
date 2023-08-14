package Core;

import java.io.Serial;

import java.io.Serializable;
import java.util.Vector;

public class Mark implements Serializable {
    @Serial
    private static final long serialVersionUID = -5688064113814295498L;
    public Vector<Double> firstAtt;
    public Vector<Double> secondAtt;
    public double pointsForFinal;
    public int absenceCount;
    public boolean finalHeld = false;

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
    public void putAcscenseCount(){
        this.absenceCount++;
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
        return ((getAverageForFirstAtt() + getAverageForSecondAtt()) < 30 || (pointsForFinal < 15 && finalHeld))  || absenceCount >= 9;
    }
    public double getFinalPoint(){
        return (getAverageForFirstAtt() + getAverageForSecondAtt() * 0.6) + (pointsForFinal *0.4);
    }
}
