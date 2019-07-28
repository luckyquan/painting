package com.li.paint.component;

import javax.swing.*;
import java.awt.*;

public class MyRadioButton extends JRadioButton {

    public MyRadioButton(String text) {
        this.setText(text);
        this.setBackground(null);
        this.setForeground(Color.BLACK);
//        this.setIcon(new Icon() {
//            @Override
//            public void paintIcon(Component c, Graphics g, int x, int y) {
//
//            }
//
//            @Override
//            public int getIconWidth() {
//                return 0;
//            }
//
//            @Override
//            public int getIconHeight() {
//                return 0;
//            }
//        });
    }
}
