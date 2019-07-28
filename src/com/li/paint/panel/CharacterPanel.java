package com.li.paint.panel;

import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import javax.swing.*;
import java.awt.*;

public class CharacterPanel extends GraphicPanel {

    private JTextArea textArea;

    public CharacterPanel(int startX, int startY, int endX, int endY) {
        super(startX, startY, endX, endY);
    }

    @Override
    protected void init() {
        super.init();
        textArea = new JTextArea("请输入文本");
        textArea.setBounds(graphicX, graphicY, graphicWidth, graphicHeight);
        textArea.setPreferredSize(new Dimension(graphicWidth, graphicHeight));
        textArea.setEditable(false);
        textArea.setForeground(Common.lineColor);
        textArea.setBackground(null);
        textArea.setOpaque(false);
        textArea.setFont(new Font("宋体", Font.PLAIN, 14));
        this.add(textArea);

//        isSelected = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        textArea.setBounds(graphicX, graphicY, graphicWidth, graphicHeight);
        textArea.setPreferredSize(new Dimension(graphicWidth, graphicHeight));

        if (isSelected) {
            textArea.setEditable(true);
            textArea.setFocusable(true);
        } else {
            textArea.setEditable(false);
            textArea.setFocusable(false);
        }
    }
}
