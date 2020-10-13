package Model;

public class SmartModule {


    protected enum location {kitchen, master, living_room, deck, hallway, laundry, bathroom, bedroom_1, bedroom_2, garage}

    protected location whereIsModule = null;
    protected String name;

    public SmartModule(location whereIsModule) {
        this.whereIsModule = whereIsModule;
    }

    public location getWhereIsModule() {
        return whereIsModule;
    }

    public void setWhereIsModule(location whereIsModule) {
        this.whereIsModule = whereIsModule;
    }
}
