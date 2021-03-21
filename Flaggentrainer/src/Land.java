class Land {
    private String name;
    private int id;
    private String flag;
    private String TDL;

    public Land(String name, int id, String flag, String TDL) {
        this.name = name;
        this.id = id;
        this.flag = flag;
        this.TDL = TDL;
    }

    public int getId() {
        return id;
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
}