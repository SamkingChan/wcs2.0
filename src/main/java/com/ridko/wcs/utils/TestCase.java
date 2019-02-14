package com.ridko.wcs.utils;

import com.ridko.wcs.domain.printer.Label;
import com.ridko.wcs.service.printer.impl.PrinterServiceImpl;

import java.util.List;

/**
 * @author chenshengjin
 */
public class TestCase {
    public static void main(String[] args) {

        try {
            Label label = new Label();
            label.setClothName("JQ6662##精绵斜纹拉架单卫衣");
            label.setClothType("JQ6662 ");
            label.setColorName("17##深宝45454");
            label.setColorNo("C39461");
            label.setEpc("30350A537000411542249761");
            label.setTicketNo("041");
            label.setWeight("24.9");
            label.setVatDye("A71016073");
            PostekPrinter.getInstance("192.168.1.252", 9100).speed(0).printTag(label);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
