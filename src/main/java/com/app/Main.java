package com.app;

import com.app.View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Async Swing initialization
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
