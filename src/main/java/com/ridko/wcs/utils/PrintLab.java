package com.ridko.wcs.utils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;

import java.io.UnsupportedEncodingException;


/**
 * @author 26699
 * 
 */
public class PrintLab {


    public interface printdll extends Library {
        // CDFPSK为dll名称,CDFPSK目录的位置为:C:\Windows\System32下面,
        printdll Instance = (printdll) Native.loadLibrary((Platform.isWindows() ? "CDFPSK" : "c"), printdll.class);
//        printdll Instance = (printdll) Native.loadLibrary(PathUtil.DLL_PATH+"CDFPSK.dll", printdll.class);

        //动态链接库CDFPSK.dll中的方法.
        int OpenPort(int px); // 打开通讯端口

        int PTK_SetPrintSpeed(int px); // 设置打印速度

        int PTK_SetDarkness(int id); // 设置打印头发热温度

        int ClosePort(); // 关闭使用 OpenPort函数打开的通讯端口

        int PTK_FormDel(String pid); // 删除存储在打印机里的一个或所有表格

        int PTK_FormDownload(String pid); // 存储一个表格到打印机； 此命令与 此命令与 PTK_FormEnd 函数配对使用。

        int PTK_FormEnd(); // 结束存储表格 (Form)，此函数与 PTK_FormDownload配对使用

        int PTK_ExecForm(String pid); // 运行指定的表格

        int PTK_Download(); // 下载变量或系列号变量

        int PTK_DownloadInitVar(String pstr); // 初始化变量或系列号变量

        int PTK_PrintLabel(int number, int cpnumber); // 命令打印机执行工作

        int PTK_DefineCounter(int id, int maxNum, short ptext, String pstr, String pMsg); // 定义一个序列号变量

        int PTK_DrawTextTrueTypeW(int x, int y, int FHeight, int FWidth, String FType, int Fspin, int FWeight, boolean FItalic,
                                  boolean FUnline, boolean FStrikeOut, String id_name, String data); // 打印一行 TrueTypeFont文字，并且宽度和高可以微调

        int PTK_DrawBarcode(int px, int py, int pdirec, String pCode, int pHorizontal, int pVertical, int pbright, char ptext, String pstr); // 打印一个条码。

        int PTK_DrawBarcode(int px, int py, int pdirec, String pCode, int NarrowWidth, int pHorizontal, int pVertical, int pbright,
                            char ptext, String pstr); // 打印一个条码。

        int PTK_SetLabelHeight(int lheight, int gapH, int gapOffset, boolean bFlag);// 设置标签的高度和定位间隙 \黑线 \穿孔的高度。

        int PTK_SetLabelWidth(int lwidth); // 设置标签的宽度。

        int PTK_ClearBuffer(); // 清除打印机缓冲内存的容

        int PTK_DrawRectangle(int px, int py, int thickness, int pEx, int pEy); // 画矩形

        int PTK_DrawLineOr(int px, int py, int pLength, int pH); // 画直线 (两直线相交处作”或”处理 )。

        int PTK_DrawBar2D_QR(int x, int y, int w, int v, int o, int r, int m, int g, int s, String pstr); // 打印一个 QR 条码

        int PTK_DrawBar2D_QREx(int x, int y, int o, int r, int g, int s, int v, String id_name, String pstr); // 打印一个 QR
                                                                                                              // （图形方式）
        int PTK_DrawBar2D_Pdf417(int x, int y, int w, int v, int s, int c, int px, int py, int r, int l, int t, int o, String pstr); // 打印一个

        int PTK_PcxGraphicsDel(String pid); // 删除存储在 打印机 RAM或 FLASH存储器里的一个或所有图形

        int PTK_PcxGraphicsDownload(String pcxname, String pcxpath); // 存储一个 PCX格式的图形到打印机

        int PTK_DrawPcxGraphics(int px, int py, String gname); // 打印指定的图形

        int PTK_DrawText(int px, int py, int pdirec, int pFont, int pHorizontal, int pVertical, char ptext, String pstr); // 打印一行文本字

        int PTK_DrawText(int px, int py, int pdirec, int pFont, int pHorizontal, int pVertical, int ptext, String pstr);

        int PTK_DrawTextEx(int px, int py, int pdirec, int pFont, int pHorizontal, int pVertical, char ptext, String pstr, boolean varible); // 打印一行文本文字，内容可以是常量、序列号变或组合符串。

        int PTK_DrawBar2D_DATAMATRIX(int x, int y, int w, int h, int o, int m, String pstr); // 打印DataMatrix二维条码

        int PTK_DrawBar2D_HANXIN(int x, int y, int w, int v, int o, int r, int m, int g, int s, String pstr); // 打印一个汉信码二维条码

        int PTK_RWRFIDLabel(int nRWMode, int nWForm, int nStartBlock, int nWDataNum, int nWArea, String pstr); // 读写RFID标签。

        int PTK_ErrorReportUSB(int USBport); // 发送错误查询指令到打印机并且指定USB端口接收和分析打印机当前错误代码。

        int PTK_SetRFLabelPWAndLockRFLabel(int nOperationMode, int OperationnArea, String pstr);// 设置RFID标签密码和锁定RFID标签


        int PTK_ReadRFTagDataUSB(int usbPort, int nDataBlock, int nRFPower, boolean bFeed, byte[] strRFData);
        int PTK_ReadRFTagDataUSB(int usbPort, int nDataBlock, int nRFPower, boolean bFeed, Pointer strRFData);

        int PTK_PrintPCX(int px, int py, String filename);//函数是打印一个 函数是打印一个 PCXPCXPCX格式的图形。
        
        int PTK_SetRFID(int nReservationParameters, int nReadWriteLocation, int ReadWriteArea, int nMaxErrNum, int nErrProcessingMethod);
        
        int PTK_Connect(String IPAddress, int Port);


        void PTK_CloseConnect();

        int PTK_DrawDiagonal(int px, int py, int thickness, int pEx, int pEy);          // 打印斜线

        // 使用 byte[] 接收 LPTSTR类型 字符串
        int PTK_ReadRFTagDataNet(String IPAddress, int port, int nDataBlock, int nRFPower, boolean bFeed, byte[] strRFData);
        int PTK_ReadRFTagDataNet(String IPAddress, int port, int nDataBlock, int nRFPower, boolean bFeed, Pointer strRFData);
        int PTK_ReadRFTagDataNet(String IPAddress, int port, int nDataBlock, int nRFPower, boolean bFeed, char[] strRFData);
		void PTK_EnableFLASH();
    }

   

}
