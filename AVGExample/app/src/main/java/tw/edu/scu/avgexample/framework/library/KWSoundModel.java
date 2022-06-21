package tw.edu.scu.avgexample.framework.library;

public class KWSoundModel {

    private int resId;

    private int soundPoolId;

    public KWSoundModel(int resId) {
        this.resId = resId;
    }

    public int getSoundPoolId() {
        return soundPoolId;
    }

    public void setSoundPoolId(int soundPoolId) {
        this.soundPoolId = soundPoolId;
    }
}
