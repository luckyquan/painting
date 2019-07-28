package com.li.paint.component;

import javax.swing.*;
import java.awt.*;

public class TitleTextField extends JTextField {
    public TitleTextField(String text) {
        this.setText(text);
        this.setEditable(false);
        this.setBackground(null);
        this.setBorder(null);
        this.setFont(new Font("font", Font.BOLD, 14));
        this.setPreferredSize(new Dimension(65, 30));
    }

    public TitleTextField(String text, int width, int height) {
        this.setText(text);
        this.setEditable(false);
        this.setBackground(null);
        this.setBorder(null);
        this.setFont(new Font("font", Font.BOLD, 14));
        this.setPreferredSize(new Dimension(width, height));
    }
}