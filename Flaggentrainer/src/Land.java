public class Land {
    private String name;
    private int id;
    private String flag;
    private String TDL;

    Land(String n, int i, String f, String tdl) {
        name = n;
        id = i;
        flag = f;
        TDL = tdl;
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
