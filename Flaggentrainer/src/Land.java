class Land {
    private String name;
    private String flag;
    private String TDL;

    public Land(String name, String flag, String TDL) {
        this.name = name;
        this.flag = flag;
        this.TDL = TDL;
    }

    public String getFlag() {
        return flag;
    }

    public String getTDL() {
        return TDL;
    }

    public String getName() {
        return name;
    }

    public Land copy() {
        return new Land(name,flag,TDL);
    }
}