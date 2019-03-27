package DabRhythm;

public class LanePosCalculator {

    public static float calc(int lane, float sizex){
        return ((lane - 3) * sizex) + (sizex * 0.5f) + (Main.engine.getMainWindow().getWidth()/2);
    }
}