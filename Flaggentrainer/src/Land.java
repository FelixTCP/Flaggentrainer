class Land {
    private String name,name2,name3,name4,TDL,flag;

    public Land(String name, String TDL, String name2, String name3, String name4, String flag) {
        this.name = name;
        this.name2 = name2;
        this.name3 = name3;
        this.name4 = name4;
        this.TDL = TDL;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public String getName2() {
        return name2;
    }

    public String getName3() {
        return name3;
    }

    public String getName4() {
        return name4;
    }

    public String getTDL() {
        return TDL;
    }

    public String getFlag() {
        return flag;
    }

    public Land copy() {
        return new Land(name,TDL,name2,name3,name4,flag);
    }
}