package com.li.paint.panel.basic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BasicPanel extends JPanel implements MouseListener, MouseMotionListener {

    public int width;
    public int height;


    public BasicPanel() {
        super();

        init();
        setupEvent();
        finish();
    }

    public BasicPanel(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.setPreferredSize(new Dimension(width, height));

        init();
        setupEvent();
        finish();
    }

    protected void init() {

    }

    protected void setupEvent() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    protected void finish() {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
