package src.scripts.Windows.Components.WindowContent.interfaces;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SpringLayout;

public interface AppWindowComponentInterface {
    public void alignSelf(SpringLayout layout, Component target);
    public void setSelfSize(Dimension size);
}
