package com.li.paint.utils;

import java.awt.*;

public class Common {

    //绘图
    public static String type = Common.TYPE_PENCIL;
    public static final String TYPE_SELECT = "select";
    public static final String TYPE_PENCIL = "pencil";
    public static final String TYPE_STRAIGHT_LINE = "straight_line";
    public static final String TYPE_RECTANGLE = "rectangle";
    public static final String TYPE_BROKEN_LINE = "broken_line";
    public static final String TYPE_RIGHT_ANGLE_LINE = "right_angle_line";
    public static final String TYPE_POLYGON = "polygon";
    public static final String TYPE_CIRCULAR = "circular";
    public static final String TYPE_OVAL = "oval";
    public static final String TYPE_ARC = "arc";//圆弧
    public static final String TYPE_CURVE = "curve";//曲线
    public static final String TYPE_ROUNDED_RECTANGLE = "rounded_rectangle";
    public static final String TYPE_CHARACTER = "character";
    public static final String TYPE_CUT_INNER = "cut_inner";
    public static final String TYPE_CUT_OUTER = "cut_outer";
    public static final String TYPE_NAME = "name";

    //间距
    public static int BORDER = Common.BORDER_MIN;
    public static final int BORDER_MIN = 5;

    //颜色
    public static Color lineColor = Color.BLACK;
    public static Color fillColor = Color.BLACK;

    //填充
    public static int fillState = Common.FILL_NO;
    public static final int FILL_NO = 1;
    public static final int FILL_COLOR = 2;
    public static final int FILL_IMG = 3;

    //线型
    public static float lineWidth = 1.0f;

    public static int lineStyle = Common.LINE_NORMAL;
    public static final int LINE_NORMAL = 1;
    public static final int LINE_DOTTED = 2;//虚线
    public static final int LINE_DOTTED1 = 3;//虚线
    public static final int LINE_DOTTED2 = 4;//虚线
    public static final int LINE_DOTTED3 = 5;//虚线


    //图片
    public static String filePath = "C:\\Users\\李振刚\\Desktop\\timg.jpg";


    //汉字
    public static int[][] name = Common.name1;
    public static final int[][] name1 = new int[64][64];
    public static final int[][] name2 = new int[64][64];
    public static final int[][] name3 = new int[64][64];
    public static final int[][] name4 = new int[64][64];
    public static final int[][] name5 = new int[64][64];
    public static final int[][] name6 = new int[64][64];
    public static final int[][] name7 = new int[64][64];
    public static final int[][] name8 = new int[64][64];
    public static final int[][] name9 = new int[64][64];
    public static final int[][] name10 = new int[64][64];
    public static final int[][] name11 = new int[64][64];
    public static final int[][] name12 = new int[64][64];
    public static final int[][] name13 = new int[64][64];
    public static final int[][] name14 = new int[64][64];
    public static final int[][] name15 = new int[64][64];
    public static final int[][] name16 = new int[64][64];
    public static final int[][] name17 = new int[64][64];
    public static final int[][] name18 = new int[64][64];
    public static final int[][] name19 = new int[64][64];
}
