package stay.walker.com.notifi;

public class NotifiEvent {

    public static final int STOP = 0x91;
    private int what;

    public NotifiEvent(int what) {
        this.what = what;
    }

    public int what() {
        return what;
    }
}
