public class FalseGuess {
    private String rightName;
    private String falseName;
    private String flag;

    public FalseGuess(String rightName, String falseName, String flag) {
        this.rightName = rightName;
        this.falseName = falseName;
        this.flag = flag;
    }

    public String getRightName() {
        return rightName;
    }

    public String getFalseName() {
        return falseName;
    }

    public String getFlag() {
        return flag;
    }
}
