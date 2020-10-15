package Model;

public class SmartSecurity extends SmartModule {
    boolean inAwayMode;
    boolean isSomeoneThere;

    public SmartSecurity(String name, String location) {
        super(name, location);
        this.inAwayMode = false;
        this.isSomeoneThere = false;
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
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
