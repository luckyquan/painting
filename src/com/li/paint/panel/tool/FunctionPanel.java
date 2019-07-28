package com.li.paint.panel.tool;

import com.li.paint.component.MyRadioButton;
import com.li.paint.component.TitleTextField;
import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.panel.basic.GraphicPanel;
import com.li.paint.utils.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FunctionPanel extends BasicPanel {

    //绘图
    private JRadioButton rb_select;
    private JRadioButton rb_pencil;
    private JRadioButton rb_straight_line;
    private JRadioButton rb_rectangle;
    private JRadioButton rb_broken_line;
    private JRadioButton rb_right_angle_line;
    private JRadioButton rb_polygon;
    private JRadioButton rb_circular;
    private JRadioButton rb_oval;
    private JRadioButton rb_arc;
    private JRadioButton rb_curve;
    private JRadioButton rb_rounded_rectangle;
    private JRadioButton rb_character;
    private JRadioButton rb_cut_inner;
    private JRadioButton rb_cut_outer;
    private JRadioButton rb_name;

    private JComboBox<String> cb_name;

    @Override
    protected void init() {
        super.init();
        this.setLayout(new GridLayout(6, 3));

        JTextField tf = new TitleTextField("图形设置:");

        rb_select = new MyRadioButton("选取图形");
        rb_pencil = new MyRadioButton("画笔");
        rb_straight_line = new MyRadioButton("直线");
        rb_rectangle = new MyRadioButton("矩形");
        rb_broken_line = new MyRadioButton("折线");
        rb_right_angle_line = new MyRadioButton("直角线");
        rb_polygon = new MyRadioButton("多边形");
        rb_circular = new MyRadioButton("圆形");
        rb_oval = new MyRadioButton("椭圆");
        rb_arc = new MyRadioButton("圆弧");
        rb_curve = new MyRadioButton("曲线");
        rb_rounded_rectangle = new MyRadioButton("圆角矩形");
        rb_character = new MyRadioButton("文本");
        rb_cut_inner = new MyRadioButton("外裁剪");
        rb_cut_outer = new MyRadioButton("内裁剪");
        rb_name = new MyRadioButton("汉字");

        cb_name = new JComboBox<>();
        cb_name.addItem("张");
        cb_name.addItem("忠");
        cb_name.addItem("乾");
        cb_name.addItem("李");
        cb_name.addItem("光");
        cb_name.addItem("耀");
        cb_name.addItem("国");
        cb_name.addItem("权");
        cb_name.addItem("盛");
        cb_name.addItem("鼎");
        cb_name.addItem("乐");
        cb_name.addItem("荣");
        cb_name.addItem("蔡");
        cb_name.addItem("纵");
        cb_name.addItem("横");
        cb_name.addItem("王");
        cb_name.addItem("森");
        cb_name.addItem("佩");
        cb_name.addItem("怡");
        cb_name.setSelectedIndex(0);

        ButtonGroup group = new ButtonGroup();
        group.add(rb_select);
        group.add(rb_pencil);
        group.add(rb_straight_line);
        group.add(rb_rectangle);
        group.add(rb_broken_line);
        group.add(rb_right_angle_line);
        group.add(rb_polygon);
        group.add(rb_circular);
        group.add(rb_oval);
        group.add(rb_arc);
        group.add(rb_curve);
        group.add(rb_rounded_rectangle);
        group.add(rb_character);
        group.add(rb_cut_inner);
        group.add(rb_cut_outer);
        group.add(rb_name);
        rb_pencil.setSelected(true);

        //绘图
        this.add(tf);
        this.add(rb_select);
        this.add(rb_pencil);
        this.add(rb_straight_line);
        this.add(rb_broken_line);
        this.add(rb_right_angle_line);
        this.add(rb_circular);
        this.add(rb_oval);
        this.add(rb_arc);
        this.add(rb_curve);
        this.add(rb_rectangle);
        this.add(rb_rounded_rectangle);
        this.add(rb_polygon);
        this.add(rb_character);
        this.add(rb_cut_inner);
        this.add(rb_cut_outer);
        this.add(rb_name);
        this.add(cb_name);
    }

    @Override
    protected void setupEvent() {
        super.setupEvent();

        rb_select.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_select.isSelected()) {
                    addAllListener();
                    Common.type = Common.TYPE_SELECT;
                }
            }
        });

        rb_pencil.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_pencil.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_PENCIL;
                }
            }
        });

        rb_straight_line.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_straight_line.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_STRAIGHT_LINE;
                }
            }
        });

        rb_rectangle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_rectangle.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_RECTANGLE;
                }
            }
        });

        rb_broken_line.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_broken_line.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_BROKEN_LINE;
                }
            }
        });

        rb_right_angle_line.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_right_angle_line.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_RIGHT_ANGLE_LINE;
                }
            }
        });

        rb_polygon.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_polygon.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_POLYGON;
                }
            }
        });

        rb_circular.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_circular.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_CIRCULAR;
                }
            }
        });

        rb_oval.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_oval.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_OVAL;
                }
            }
        });

        rb_arc.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_arc.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_ARC;
                }
            }
        });

        rb_curve.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_curve.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_CURVE;
                }
            }
        });

        rb_rounded_rectangle.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_rounded_rectangle.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_ROUNDED_RECTANGLE;
                }
            }
        });

        rb_character.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_character.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_CHARACTER;
                }
            }
        });

        rb_cut_inner.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_cut_inner.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_CUT_INNER;
                }
            }
        });

        rb_cut_outer.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_cut_outer.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_CUT_OUTER;
                }
            }
        });

        rb_name.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rb_name.isSelected()) {
                    removeAllListener();
                    Common.type = Common.TYPE_NAME;
                }
            }
        });

        cb_name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        Common.type = Common.TYPE_NAME;
                        String name = (String) e.getItem();
                        System.out.println("选择汉字:" + name);

                        switch (name) {
                            case "张":
                                Common.name = Common.name1;
                                break;

                            case "忠":
                                Common.name = Common.name2;
                                break;

                            case "乾":
                                Common.name = Common.name3;
                                break;

                            case "李":
                                Common.name = Common.name4;
                                break;

                            case "光":
                                Common.name = Common.name5;
                                break;

                            case "耀":
                                Common.name = Common.name6;
                                break;

                            case "国":
                                Common.name = Common.name7;
                                break;

                            case "权":
                                Common.name = Common.name8;
                                break;

                            case "盛":
                                Common.name = Common.name9;
                                break;

                            case "鼎":
                                Common.name = Common.name10;
                                break;
                            case "乐":
                                Common.name = Common.name11;
                                break;
                            case "荣":
                                Common.name = Common.name12;
                                break;
                            case "蔡":
                                Common.name = Common.name13;
                                break;
                            case "纵":
                                Common.name = Common.name14;
                                break;
                            case "横":
                                Common.name = Common.name15;
                                break;
                            case "王":
                                Common.name = Common.name16;
                                break;
                            case "森":
                                Common.name = Common.name17;
                                break;
                            case "佩":
                                Common.name = Common.name18;
                                break;
                            case "怡":
                                Common.name = Common.name19;
                                break;

                        }

                        break;

                    case ItemEvent.DESELECTED:
                        break;
                }
            }
        });
    }

    private void addAllListener() {
        JPanel toolPanel = (JPanel) this.getParent();
        Container frame = toolPanel.getParent();
        if (frame.getComponentCount() == 0) {
            return;
        }
        JPanel drawPanel = (JPanel) frame.getComponent(0);
        for (int i = 0; i < drawPanel.getComponentCount(); i++) {
            GraphicPanel graphicPanel = (GraphicPanel) drawPanel.getComponent(i);
            graphicPanel.addMouseListener(graphicPanel.mouseListener);
            graphicPanel.addMouseMotionListener(graphicPanel.mouseMotionListener);
        }
    }

    private void removeAllListener() {
        if (Common.type.equals(Common.TYPE_SELECT)) {
            JPanel toolPanel = (JPanel) this.getParent();
            Container frame = toolPanel.getParent();
            if (frame.getComponentCount() == 0) {
                return;
            }
            JPanel drawPanel = (JPanel) frame.getComponent(0);
            for (int i = 0; i < drawPanel.getComponentCount(); i++) {
                GraphicPanel graphicPanel = (GraphicPanel) drawPanel.getComponent(i);
                graphicPanel.removeMouseListener(graphicPanel.mouseListener);
                graphicPanel.removeMouseMotionListener(graphicPanel.mouseMotionListener);

                graphicPanel.isSelected = false;
            }
        }
    }
}
