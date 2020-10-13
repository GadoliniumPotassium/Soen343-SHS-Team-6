package Model;

public class SmartSecurity extends SmartModule {
    boolean inAwayMode;
    boolean isSomeoneThere;

    public SmartSecurity(location whereIsModule, boolean inAwayMode, boolean isSomeoneThere) {
        super(whereIsModule);
        this.inAwayMode = inAwayMode;
        this.isSomeoneThere = isSomeoneThere;
    }

    public boolean isInAwayMode() {
        return inAwayMode;
    }

    public void setInAwayMode(boolean inAwayMode) {
        this.inAwayMode = inAwayMode;
    }

    public boolean isSomeoneThere() {
        return isSomeoneThere;
    }

    public void setSomeoneThere(boolean someoneThere) {
        isSomeoneThere = someoneThere;
    }

    @Override
    public String toString() {
        return "SmartSecurity{" +
                "inAwayMode=" + inAwayMode +
                ", isSomeoneThere=" + isSomeoneThere +
                ", whereIsModule=" + whereIsModule +
                ", name='" + name + '\'' +
                '}';
    }
}
