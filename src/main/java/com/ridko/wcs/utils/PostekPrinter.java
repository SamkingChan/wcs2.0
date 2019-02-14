package com.ridko.wcs.utils;


import com.ridko.wcs.domain.printer.Label;
import com.ridko.wcs.utils.PrintLab.*;

import java.util.List;

/**
 * @author : Administrator
 */
public class PostekPrinter {

    // ip 地址
    private String ipAddress;
    // 端口号
    private int port = 9100;
    // 打印速度
    private int speed = 100;
    // 单例
    private static PostekPrinter printer = null;

    /**
     * 获取实例
     * @param ipAddress ip地址
     * @param port 端口号
     * @return
     */
    public static PostekPrinter getInstance(String ipAddress, int port) {
        if (printer == null) {
            printer = new PostekPrinter(ipAddress, port);
        }
        return printer;

    }

    /**
     * 获取实例
     * @return
     * @throws Exception
     */
    public static PostekPrinter getInstance() throws Exception {
        if (printer == null) {
            throw new Exception("需要打印机ip地址跟 端口号");
        }
        return printer;

    }

    /**
     * 速度, 范围为0到100
     * @param speed
     * @return
     * @throws Exception
     */
    public PostekPrinter speed(int speed) throws Exception {
        if (speed < 0 || speed > 100) {
            throw new Exception("打印速度只能在0到100之间");
        }
        speed -= speed % 10; // 取整
        getInstance().speed = speed;
        return getInstance();
    }

    public boolean printTags(List<Label> labels) throws Exception {
        print_before();
        int ok = printdll.Instance.PTK_Connect(ipAddress, port);
        boolean success = false;
        if (ok == 0) {
            for (Label label : labels) {
                success = printOneTag(label);
            }
        } else {
            throw new Exception("打印机连接失败");
        }
        printdll.Instance.PTK_CloseConnect();
        return success;
    }

    public boolean printTag(Label label) throws Exception {
        int ok = printdll.Instance.PTK_Connect(ipAddress, port);
        boolean success = false;
        if (ok == 0) {
            success = printOneTag(label);
        }else {
            throw new Exception("打印机连接失败");
        }

        printdll.Instance.PTK_CloseConnect();
        return success;
    }

    private boolean printOneTag(Label label) throws Exception {
        System.setProperty("jna.encoding", "GBK");

        // 写内容

        int left_top_x = 770;
        int left_top_y = 520;

        int right_bottom_x = 1;
        int right_bottom_y = 10;

        //  字体垂直位移， 数值越大， 位置越靠上
       int font_vertical_margin = 18;

        // 矩形
        printdll.Instance.PTK_DrawRectangle(right_bottom_x, right_bottom_y, 5, left_top_x, left_top_y);


        // 水平线数目
        int horizontal_line_size = 5;
        // 竖线
        int vertical_len = left_top_y - right_bottom_y;
        left_top_x += 75;
//        printdll.Instance.PTK_DrawLineOr(left_top_x - 75, right_bottom_y, 5, vertical_len);
        printdll.Instance.PTK_DrawLineOr(left_top_x - 208, right_bottom_y, 5, vertical_len);
        printdll.Instance.PTK_DrawLineOr(left_top_x - 470, right_bottom_y, 5, vertical_len / 5);
        printdll.Instance.PTK_DrawLineOr(left_top_x - 590, right_bottom_y, 5, vertical_len / 5);
        printdll.Instance.PTK_DrawLineOr(left_top_x - 750, right_bottom_y, 5, vertical_len / 5);
        left_top_x -= 75;

        // 横线
        int horizontal_len = left_top_x - right_bottom_x;
        int vertical_dimension = vertical_len / horizontal_line_size;
        printdll.Instance.PTK_DrawLineOr(right_bottom_x, right_bottom_y + vertical_dimension, horizontal_len, 5);
        printdll.Instance.PTK_DrawLineOr(right_bottom_x, right_bottom_y + vertical_dimension * 2, horizontal_len, 5);
        printdll.Instance.PTK_DrawLineOr(right_bottom_x, right_bottom_y + vertical_dimension * 3, horizontal_len, 5);
        printdll.Instance.PTK_DrawLineOr(right_bottom_x, right_bottom_y + vertical_dimension * 4, horizontal_len, 5);

        // 第一列
        int first = vertical_len / 6 - 2;
        left_top_x -= 4;

        left_top_y -= font_vertical_margin;
//        printdll.Instance.PTK_DrawText(left_top_x, left_top_y - 10, 2, 6, 2, 2, 'N', "JQ");
//        printdll.Instance.PTK_DrawText(left_top_x, left_top_y - first * 2, 2, 6, 2, 2, 'N', "嘉");
//        printdll.Instance.PTK_DrawText(left_top_x, left_top_y - first * 3, 2, 6, 2, 2, 'N', "谦");
//        printdll.Instance.PTK_DrawText(left_top_x, left_top_y - first * 4, 2, 6, 2, 2, 'N', "坊");
//        printdll.Instance.PTK_DrawText(left_top_x, left_top_y - first * 5, 2, 6, 2, 2, 'N', "织");
//        left_top_x += 4;

        //向左移动
        left_top_x +=70;
//
        // 第二列
        int second = vertical_len / horizontal_line_size + 5;
        int second_move_to_right = 90;

        printdll.Instance.PTK_DrawText(left_top_x - second_move_to_right, left_top_y - 4, 2, 1, 2, 2, 'N', label.getTicketNo());
        printdll.Instance.PTK_DrawText(left_top_x - second_move_to_right, left_top_y - second, 2, 6, 2, 2, 'N', "缸号");
        printdll.Instance.PTK_DrawText(left_top_x - second_move_to_right, left_top_y - second * 2, 2, 6, 2, 2, 'N', "颜色");
        printdll.Instance.PTK_DrawText(left_top_x - second_move_to_right, left_top_y - second * 3, 2, 6, 2, 2, 'N', "色号");
        printdll.Instance.PTK_DrawText(left_top_x - second_move_to_right, left_top_y - second * 4, 2, 6, 2, 2, 'N', "布种");


        // 第三列
        int third = vertical_len / horizontal_line_size + 5;
        int third_move_to_right = 210;
        printdll.Instance.PTK_DrawText(left_top_x - third_move_to_right, left_top_y - 4, 2, 1, 2, 2, 'N', label.getClothName());
        printdll.Instance.PTK_DrawText(left_top_x - third_move_to_right, left_top_y - third, 2, 1, 2, 2, 'N', label.getVatDye());

        printdll.Instance.PTK_DrawText(left_top_x - third_move_to_right, left_top_y - third * 2, 2, 1, 2, 2, 'N', label.getColorName());
        printdll.Instance.PTK_DrawText(left_top_x - third_move_to_right, left_top_y - third * 3, 2, 1, 2, 2, 'N', label.getColorNo());
        printdll.Instance.PTK_DrawText(left_top_x - third_move_to_right, left_top_y - third * 4, 2, 1, 2, 2, 'N', label.getClothType());


        // 第四列
        int fourth = vertical_len / horizontal_line_size + 5;
        int fourth_move_to_right = 460;
//        printdll.Instance.PTK_DrawText(left_top_x - fourth_move_to_right, left_top_y - fourth * 3, 2, 6, 2, 2, 'N', "色号");
        printdll.Instance.PTK_DrawText(left_top_x - fourth_move_to_right, left_top_y - fourth * 4, 2, 6, 2, 2, 'N', "重量");


        // 第五列
        int fifth = vertical_len / horizontal_line_size + 5;
        int fifth_move_to_right = 600;
//        printdll.Instance.PTK_DrawText(left_top_x - fifth_move_to_right, left_top_y - fifth * 2, 2, 1, 2, 2, 'N', label.getColorNo());
        printdll.Instance.PTK_DrawText(left_top_x - fifth_move_to_right, left_top_y - fifth * 4, 2, 1, 2, 2, 'N', label.getWeight());

        // 第六列
        int sixth = vertical_len / horizontal_line_size + 5;
        int sixth_move_to_right = 750;
        printdll.Instance.PTK_DrawText(left_top_x - sixth_move_to_right, left_top_y - sixth * 4, 2, 1, 2, 2, 'N', "KG");


        left_top_y += font_vertical_margin;

        int isWRFID = printdll.Instance.PTK_RWRFIDLabel(1, 0, 2, 12, 1, label.getEpc());
        if (isWRFID == 0) {
            System.out.println("RFID写入成功 " + label.getEpc());

        } else {
            System.out.println("RFID写入失败");
            throw new Exception("RFID写入失败");
        }

        //
        int isPrint = printdll.Instance.PTK_PrintLabel(1, 1);
        if (isPrint == 0) {
            System.out.println("打印标签成功");
            return true;
        } else {
            System.out.println("打印标签失败");
            return false;
        }
    }

    private PostekPrinter() {

    }

    private PostekPrinter(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    private void print_before() throws Exception {
        if (ipAddress == "" || ipAddress == null) {
            throw new Exception("Postek 打印机地址没有设置");
        }
        if (port > 65535 || port < 0) {
            throw new Exception("Postek 打印机端口范围不正确");
        }
        printdll.Instance.PTK_SetPrintSpeed(speed);

    }


}
