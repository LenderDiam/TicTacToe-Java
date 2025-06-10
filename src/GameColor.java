
public enum GameColor {
    RESET("\u001B[0m"),
    X("\u001B[31m"),
    O("\u001B[34m"),
    WIN("\u001B[32m"),
    DRAW("\u001B[33m");

    private final String code;

    GameColor(String code) {
        this.code = code;
    }

    public String get() {
        return code;
    }
}