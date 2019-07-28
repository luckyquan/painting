package com.li.paint.frame;

import com.li.paint.panel.main.DrawPanel;
import com.li.paint.panel.main.ToolPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel drawPanel;
    private JPanel toolPanel;

    public MainFrame() {

        init();
        setupEvent();
        finish();
    }

    private void init() {
        this.setTitle("绘图");
        this.setLayout(new FlowLayout());
        this.setLocation(200, 100);

        int drawWidth = 1200;
        int drawHeight = 800;

        drawPanel = new DrawPanel(drawWidth, drawHeight);
        toolPanel = new ToolPanel(drawWidth / 4, drawHeight);
    }

    private void setupEvent() {

    }

    private void finish() {
        this.add(drawPanel);
        this.add(toolPanel);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
