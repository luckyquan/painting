package com.li.paint.panel.tool;

import com.li.paint.component.MyRadioButton;
import com.li.paint.component.TitleTextField;
import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.utils.Common;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FillPanel extends BasicPanel {

    //填充
    private JRadioButton rb_black;
    private JRadioButton rb_red;
    private JRadioButton rb_green;
    private JRadioButton rb_blue;

    private JButton bt_color;

    private JRadioButton rb_noFill;
    private JRadioButton rb_colorFill;
    private JRadioButton rb_imgFill;

    private JButton bt_file;

    @Override
    protected void init() {
        super.init();
        this.setLayout(new GridLayout(4, 3));

        setupFillColor();
        setupFill();
    }

    @Override
    protected void setupEvent() {
        super.setupEvent();

        //填充
        rb_noFill.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_noFill.isSelected()) {
                    Common.fillState = Common.FILL_NO;
                }
            }
        });

        rb_colorFill.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_colorFill.isSelected()) {
                    Common.fillState = Common.FILL_COLOR;
                }
            }
        });

        rb_imgFill.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_imgFill.isSelected()) {
                    Common.fillState = Common.FILL_IMG;
                }
            }
        });


        //颜色
        rb_black.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_black.isSelected()) {
                    Common.fillColor = Color.BLACK;
                }
            }
        });

        rb_red.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_red.isSelected()) {
                    Common.fillColor = Color.RED;
                }
            }
        });

        rb_green.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_green.isSelected()) {
                    Common.fillColor = Color.GREEN;
                }
            }
        });

        rb_blue.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_blue.isSelected()) {
                    Common.fillColor = Color.BLUE;
                }
            }
        });

        //文件选择
        bt_file.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png");
                chooser.setFileFilter(filter);
                chooser.setMultiSelectionEnabled(false);

                int returnVal = chooser.showOpenDialog(bt_file);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    Common.filePath = chooser.getSelectedFile().getAbsolutePath();
                    System.out.println("设置图片:" + Common.filePath);

                    bt_file.setText(Common.filePath);
                }
            }
        });

        //调色板
        bt_color.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(FillPanel.this, "请选择颜色", false, colorChooser,
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Common.fillColor = colorChooser.getColor();
                                System.out.println("设置填充颜色:" + Common.fillColor);

                                bt_color.setForeground(Common.fillColor);
                            }
                        }, null);
                dialog.setVisible(true);
            }
        });
    }

    private void setupFillColor() {
        JTextField tf = new TitleTextField("填充颜色:");

        rb_black = new MyRadioButton("黑色");
        rb_red = new MyRadioButton("红色");
        rb_green = new MyRadioButton("绿色");
        rb_blue = new MyRadioButton("蓝色");

        bt_color = new JButton("填充调色板");

        ButtonGroup group = new ButtonGroup();
        group.add(rb_black);
        group.add(rb_red);
        group.add(rb_green);
        group.add(rb_blue);
        rb_black.setSelected(true);

        this.add(tf);
        this.add(rb_black);
        this.add(rb_red);
        this.add(rb_green);
        this.add(rb_blue);
        this.add(bt_color);
    }

    //设置填充
    private void setupFill() {
        JTextField tf = new TitleTextField("填充模式:");

        rb_noFill = new MyRadioButton("不填充");
        rb_colorFill = new MyRadioButton("颜色填充");
        rb_imgFill = new MyRadioButton("图案填充");

        bt_file = new JButton("选择图片");

        ButtonGroup group = new ButtonGroup();
        group.add(rb_noFill);
        group.add(rb_colorFill);
        group.add(rb_imgFill);
        rb_noFill.setSelected(true);

        //填充
        this.add(tf);
        this.add(rb_noFill);
        this.add(rb_colorFill);
        this.add(rb_imgFill);
        this.add(bt_file);
    }
}
