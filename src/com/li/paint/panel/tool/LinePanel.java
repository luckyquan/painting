package com.li.paint.panel.tool;

import com.li.paint.component.MyRadioButton;
import com.li.paint.component.TitleTextField;
import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.utils.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LinePanel extends BasicPanel {

    //颜色
    private JRadioButton rb_black;
    private JRadioButton rb_red;
    private JRadioButton rb_green;
    private JRadioButton rb_blue;

    private JButton bt_color;

    //线型
    private JTextField tf_lineWidth;
    private JButton bt_confirm;
    private JRadioButton rb_line;
    private JRadioButton rb_dottedLine;
    private JRadioButton rb_dottedLine1;
    private JRadioButton rb_dottedLine2;
    private JRadioButton rb_dottedLine3;

    @Override
    protected void init() {
        super.init();
        this.setLayout(new GridLayout(5, 3));

        setupLineColor();
        setupLine();
    }

    @Override
    protected void setupEvent() {
        super.setupEvent();

        //限制只能输入数字
        tf_lineWidth.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                int keyChar = e.getKeyChar();
                if (keyChar == '.') {
                    return;
                }
                if (keyChar < KeyEvent.VK_0 || keyChar > KeyEvent.VK_9) {
                    e.consume(); //关键，屏蔽掉非法输入
                }
            }
        });


        bt_confirm.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = tf_lineWidth.getText();
                if (text != null && !text.isEmpty()) {
                    float lineWidth = Float.parseFloat(text);

                    if (lineWidth > 20) {
                        lineWidth = 20;
                        tf_lineWidth.setText("20");
                    }

                    if (lineWidth < 1) {
                        lineWidth = 1;
                        tf_lineWidth.setText("1");
                    }

                    Common.lineWidth = lineWidth;
                    System.out.println("设置线宽:" + lineWidth);

                    if (lineWidth > 5) {
                        Common.BORDER = (int) lineWidth;
                    } else {
                        Common.BORDER = Common.BORDER_MIN;
                    }
                }
            }
        });

        rb_line.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_line.isSelected()) {
                    Common.lineStyle = Common.LINE_NORMAL;
                }
            }
        });

        rb_dottedLine.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_dottedLine.isSelected()) {
                    Common.lineStyle = Common.LINE_DOTTED;
                }
            }
        });

        rb_dottedLine1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_dottedLine1.isSelected()) {
                    Common.lineStyle = Common.LINE_DOTTED1;
                }
            }
        });

        rb_dottedLine2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_dottedLine2.isSelected()) {
                    Common.lineStyle = Common.LINE_DOTTED2;
                }
            }
        });

        rb_dottedLine3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_dottedLine3.isSelected()) {
                    Common.lineStyle = Common.LINE_DOTTED3;
                }
            }
        });


        //颜色
        rb_black.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_black.isSelected()) {
                    Common.lineColor = Color.BLACK;
                }
            }
        });

        rb_red.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_red.isSelected()) {
                    Common.lineColor = Color.RED;
                }
            }
        });

        rb_green.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_green.isSelected()) {
                    Common.lineColor = Color.GREEN;
                }
            }
        });

        rb_blue.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_blue.isSelected()) {
                    Common.lineColor = Color.BLUE;
                }
            }
        });

        //调色板
        bt_color.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorChooser = new JColorChooser();
                JDialog dialog = JColorChooser.createDialog(LinePanel.this, "请选择颜色", false, colorChooser,
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Common.lineColor = colorChooser.getColor();
                                System.out.println("设置线条颜色:" + Common.lineColor);

                                bt_color.setForeground(Common.lineColor);
                            }
                        }, null);
                dialog.setVisible(true);
            }
        });
    }

    //设置颜色选择
    private void setupLineColor() {
        JTextField tf = new TitleTextField("线条颜色:");

        rb_black = new MyRadioButton("黑色");
        rb_red = new MyRadioButton("红色");
        rb_green = new MyRadioButton("绿色");
        rb_blue = new MyRadioButton("蓝色");

        bt_color = new JButton("线条调色板");

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

    //设置线型
    private void setupLine() {
        JTextField tf = new TitleTextField("线宽设置:");
        JTextField tf1 = new TitleTextField("线型设置:");

        tf_lineWidth = new JTextField();
        bt_confirm = new JButton("确认");
        rb_line = new MyRadioButton("直线");
        rb_dottedLine = new MyRadioButton("虚线");
        rb_dottedLine1 = new MyRadioButton("虚线1");
        rb_dottedLine2 = new MyRadioButton("虚线2");
        rb_dottedLine3 = new MyRadioButton("虚线3");

        ButtonGroup group = new ButtonGroup();
        group.add(rb_line);
        group.add(rb_dottedLine);
        group.add(rb_dottedLine1);
        group.add(rb_dottedLine2);
        group.add(rb_dottedLine3);
        rb_line.setSelected(true);

        tf_lineWidth.setText("1");
        tf_lineWidth.setPreferredSize(new Dimension(45, 30));

        //线型
        this.add(tf);
        this.add(tf_lineWidth);
        this.add(bt_confirm);
        this.add(tf1);
        this.add(rb_line);
        this.add(rb_dottedLine);
        this.add(rb_dottedLine1);
        this.add(rb_dottedLine2);
        this.add(rb_dottedLine3);
    }
}
