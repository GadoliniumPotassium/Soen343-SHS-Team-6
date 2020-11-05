package Model;

public class SmartSecurity extends SmartModule {
    private boolean inAwayMode;
    private boolean isSomeoneThere;
    private int timeSet;
    private static int idNum=1;


    /**
     * Smart security constructor
     *
     * @param location
     */
    public SmartSecurity(String location) {
        super("SmartSecurity#"+(idNum++), location);
        this.inAwayMode = false;
        this.isSomeoneThere = false;
        this.timeSet=0;
    }

    /**
     * Returns if the security system is in away mode
     *
     * @return
     */
    public boolean isInAwayMode() {
        return inAwayMode;
    }

    /**
     * toggle security system in away mode or home mode
     *
     * @param inAwayMode
     */
    public void setInAwayMode(boolean inAwayMode) {
        this.inAwayMode = inAwayMode;
    }

    /**
     * Returns if someone is in the house
     *
     * @return
     */
    public boolean isSomeoneThere() {
        return isSomeoneThere;
    }

    /**
     * Toggle if someone is in the house
     *
     * @param someoneThere
     */
    public void setSomeoneThere(boolean someoneThere) {
        isSomeoneThere = someoneThere;
    }

    /**
     * this method allows the user to set the time in seconds before contacting the authorities
     * @param time
     */
    public void setTimeSet(int time){
        this.timeSet=time;
    }

    /**
     * this returns the time set by user before contacting the authorities
     * @return
     */
    public int getTimeSet() {
        return timeSet;
    }

    @Override
    public String toString() {
        return "SmartSecurity{" +
                "inAwayMode=" + inAwayMode +
                ", isSomeoneThere=" + isSomeoneThere +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
