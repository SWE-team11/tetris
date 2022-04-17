package tetris.model;

public class ConfigModel {
    protected ConfigModel() {
    }

    public static ConfigModel getInstance() {
        return new ConfigModel();
    }
    public static final int HEIGHT = 20;
    public static final int WIDTH = 10;
}
