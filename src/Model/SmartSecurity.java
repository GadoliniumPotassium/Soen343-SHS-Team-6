package Model;

public class SmartSecurity extends SmartModule {
    boolean inAwayMode;
    boolean isSomeoneThere;

    /**
     * Smart security constructor
     *
     * @param name
     * @param location
     */
    public SmartSecurity(String name, String location) {
        super(name, location);
        this.inAwayMode = false;
        this.isSomeoneThere = false;
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
