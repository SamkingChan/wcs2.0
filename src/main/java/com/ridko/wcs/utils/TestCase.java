package com.ridko.wcs.utils;

import com.ridko.wcs.service.printer.impl.PrinterServiceImpl;

/**
 * @author chenshengjin
 */
public class TestCase {
    public static void main(String[] args) {
        String  zpl = "^XA" +
                "^MMT" +
                "^PW980" +
                "^LL0406" +
                "^LS0" +
                "^CI26^SEE:GB.DAT^CW1,E:FONTR.FNT" +
                "^FT99,162^A1N,45,45^FH\\^FD嘉^FS" +
                "^FT99,214^A1N,45,45^FH\\^FD谦^FS" +
                "^FT99,266^A1N,45,45^FH\\^FD纺^FS" +
                "^FT99,318^A1N,45,45^FH\\^FD织^FS" +
                "^FO84,29^GB780,325,2^FS" +
                "^FO766,264^GB0,89,2^FS" +
                "^FO617,175^GB0,179,2^FS" +
                "^FO155,174^GB709,0,2^FS" +
                "^FO522,174^GB0,180,2^FS" +
                "^FO155,31^GB0,324,2^FS" +
                "^FO155,263^GB709,0,2^FS" +
                "^FO250,30^GB0,325,2^FS" +
                "^FT98,88^A1N,45,45^FH\\^FDJQ^FS" +
                "^FT526,324^A1N,42,42^FH\\^FD重量^FS" +
                "^FT526,236^A1N,42,42^FH\\^FD色号^FS" +
                "^FT159,148^A1N,42,42^FH\\^FD缸号^FS" +
                "^FT159,322^A1N,42,42^FH\\^FD布种^FS" +
                "^FT158,232^A1N,42,42^FH\\^FD颜色^FS" +
                "^FO155,92^GB709,0,2^FS" +
                "^FT174,78^A1N,50,50^FH\\^FD" +"001" + "^FS" +
                "^FT622,240^A1N,52,52^FH\\^FD" + "JQ222" + "^FS" +
                "^FT254,328^A1N,48,48^FH\\^FD" + "A18022" + "^FS" +
                "^FT621,327^A1N,52,52^FH\\^FD" + "23.8" + "^FS" +
                "^FT254,151^A1N,52,52^FH\\^FD" + "JQ61111" + "^FS" +
                "^FT252,79^A1N,48,48^FH\\^FD" + "豆腐干豆腐干豆腐干" + "^FS" +
                "^FT253,238^A1N,52,52^FH\\^FD" + "爱是一道绿光" + "^FS" +
                "^FT783,329^A1N,52,52^FH\\^FDKG^FS" +
                "^RFW,H,1,2,1^FD3400^FS" +
                "^RFW,H,2,12,1^FD" + "111111111111111111111112" + "^FS" +
                "^PQ1,0,1,Y^XZ";
        PrinterServiceImpl printerService = new PrinterServiceImpl();;
        printerService.print(zpl);
    }
}
