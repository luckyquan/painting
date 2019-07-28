package com.li.paint.panel.main;

import com.li.paint.panel.basic.BasicPanel;
import com.li.paint.panel.tool.FillPanel;
import com.li.paint.panel.tool.FunctionPanel;
import com.li.paint.panel.tool.LinePanel;
import com.li.paint.panel.tool.TransPanel;

import javax.swing.*;
import java.awt.*;

public class ToolPanel extends BasicPanel {

    private JPanel functionPanel;
    private JPanel linePanel;
    private JPanel fillPanel;
    private JPanel transPanel;

    public ToolPanel(int width, int height) {
        super(width, height);
    }

    @Override
    protected void init() {
        super.init();
        this.setBackground(Color.LIGHT_GRAY);

        functionPanel = new FunctionPanel();
        linePanel = new LinePanel();
        fillPanel = new FillPanel();
        transPanel = new TransPanel();
    }

    @Override
    protected void finish() {
        super.finish();

        this.add(functionPanel);
        this.add(linePanel);
        this.add(fillPanel);
        this.add(transPanel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
