package com.exam2;

import com.exam1.View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Async Swing initialization
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
