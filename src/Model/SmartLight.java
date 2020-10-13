package Model;

public class SmartLight extends SmartModule {

    private final boolean isDimmable;
    private int lightPercentage;

    public SmartLight(boolean isDimmable, int lightPercentage,location where) {
        super(where);
        this.isDimmable = isDimmable;
        this.lightPercentage = lightPercentage;

    }


}
