class Land {
    private final String name,TDL,flag;

    public Land(String name, String TDL, String flag) {
        this.name = name;
        this.TDL = TDL;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getTDL() {
        return TDL;
    }

    public String getFlag() {
        return flag;
    }

    public Land copy() {
        return new Land(name,TDL,flag);
    }
}