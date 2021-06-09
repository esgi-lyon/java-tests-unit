package com.framework.SwingModules;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface MenuBarInterface {

    void demoOpen(ActionListener listener);

    void aboutOpen(ActionListener listener);

    void handleAbout(JFrame frame);

    void helpOpen(ActionListener listener);

    void handleHelp(JFrame frame);

    JMenuItem getjMenuItemQuit();

    JMenuItem getjMenuItemHome();
}
