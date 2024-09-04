package src.scripts.utils.ConstComponents;

import java.awt.Dimension;

public class WindowSettings {
    private String name;
    private String title;
    private int W, H;
    private int min_W, min_H;
    private CtrlButtonSettings ctrlSettings;
    private boolean isFullScreen;

    public WindowSettings(String name, String title, int width, int height, CtrlButtonSettings ctrlSettings,
            boolean isFullScreen) {
        this.name = name;
        this.title = title;
        this.W = width;
        this.H = height;
        this.ctrlSettings = ctrlSettings;
        this.isFullScreen = isFullScreen;
    }

    public Dimension getDimension() {
        return new Dimension(this.W, this.H);
    }

    public Dimension getMinimumDimension() {
        return new Dimension(this.min_W, this.min_H);
    }

    public CtrlButtonSettings getControlSettings() {
        return this.ctrlSettings;
    }

    public String getTitle() {
        return this.title;
    }

    public String getName() {
        return this.name;
    }

    public int getW() {
        return this.W;
    }

    public int getH() {
        return this.H;
    }

    public void toggleFullScreen() {
        this.isFullScreen = !isFullScreen;
    }

    public boolean getFullScreenState() {
        return isFullScreen;
    }
}
