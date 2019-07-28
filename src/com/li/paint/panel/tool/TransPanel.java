package com.li.paint.panel.tool;

import com.li.paint.component.TitleTextField;
import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.panel.main.DrawPanel;
import com.li.paint.utils.Common;
import com.li.paint.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class TransPanel extends BasicPanel {

    private JButton bt_symmetry;
    private JButton bt_rotate;
    private JButton bt_clear;

    private JButton bt_save;
    private JButton bt_load;

    @Override
    protected void init() {
        super.init();
        this.setLayout(new GridLayout(2, 3));

        bt_symmetry = new JButton("对称");
        bt_rotate = new JButton("旋转");
        bt_clear = new JButton("清空");

        bt_save = new JButton("保存");
        bt_load = new JButton("重绘");
    }

    @Override
    protected void setupEvent() {
        super.setupEvent();

        bt_symmetry.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel toolPanel = (JPanel) TransPanel.this.getParent();
                JPanel frame = (JPanel) toolPanel.getParent();
                if (frame.getComponentCount() > 0) {
                    DrawPanel drawPanel = (DrawPanel) frame.getComponent(0);

                    for (int i = 0; i < drawPanel.getComponentCount(); i++) {
                        GraphicPanel graphicPanel = (GraphicPanel) drawPanel.getComponent(i);

                        if (graphicPanel.isSelected) {
                            graphicPanel.isSymmetry = !graphicPanel.isSymmetry;
                            break;
                        }
                    }
                }
            }
        });

        bt_clear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel toolPanel = (JPanel) TransPanel.this.getParent();
                JPanel frame = (JPanel) toolPanel.getParent();
                if (frame.getComponentCount() > 0) {
                    DrawPanel drawPanel = (DrawPanel) frame.getComponent(0);
                    drawPanel.removeAll();

                    drawPanel.pencilImage = new BufferedImage(drawPanel.width, drawPanel.height, BufferedImage.TYPE_INT_ARGB);
                    drawPanel.pencilG = drawPanel.pencilImage.getGraphics();
                }
            }
        });

        bt_save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel toolPanel = (JPanel) TransPanel.this.getParent();
                JPanel frame = (JPanel) toolPanel.getParent();
                if (frame.getComponentCount() > 0) {
                    DrawPanel drawPanel = (DrawPanel) frame.getComponent(0);

                    List<GraphicPanel> graphicPanelList = new ArrayList<>();
                    for (int i = 0; i < drawPanel.getComponentCount(); i++) {
                        GraphicPanel graphicPanel = (GraphicPanel) drawPanel.getComponent(i);

                        if (Common.TYPE_RECTANGLE.equals(graphicPanel.type)) {
                            graphicPanelList.add(graphicPanel);
                        }
                    }

                    Utils.saveGraphics(graphicPanelList);
                }
            }
        });

        bt_load.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel toolPanel = (JPanel) TransPanel.this.getParent();
                JPanel frame = (JPanel) toolPanel.getParent();
                if (frame.getComponentCount() > 0) {
                    DrawPanel drawPanel = (DrawPanel) frame.getComponent(0);

                    List<GraphicPanel> graphicPanelList = Utils.loadGraphics();
                    for (GraphicPanel graphicPanel : graphicPanelList) {
                        drawPanel.add(graphicPanel);
                    }
                }
            }
        });
    }

    @Override
    protected void finish() {
        super.finish();

        JTextField tf = new TitleTextField("功能选择:");

        this.add(tf);
        this.add(bt_symmetry);
        this.add(bt_clear);

        this.add(bt_save);
        this.add(bt_load);
    }
}
