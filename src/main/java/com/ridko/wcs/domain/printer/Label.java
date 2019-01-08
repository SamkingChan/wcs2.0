package com.ridko.wcs.domain.printer;


/**
 * @Author SexyChan
 * @Date 2018/11/21 15:38
 */
public class Label {
    private String ticketNo;
    private String weight;
    private String colorName;
    private String clothName;
    private String clothType;
    private String vatDye;
    private String colorNo;
    private String epc;

    public String getTicketNo() {
        if(ticketNo == null){
            this.ticketNo = "";
        }
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getWeight() {
        if(weight ==  null){
            this.weight = "";
        }
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColorName() {
        if(colorName == null){
            this.colorName = "";
        }
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getClothName() {
        if(clothName== null){
            this.clothName = "";
        }
        return clothName;
    }

    public void setClothName(String clothName) {
        this.clothName = clothName;
    }

    public String getClothType() {
        if(clothType == null){
            this.clothType = "";
        }
        return clothType;
    }

    public void setClothType(String clothType) {
        this.clothType = clothType;
    }

    public String getVatDye() {
        if(vatDye == null){
            this.vatDye = "";
        }
        return vatDye;
    }

    public void setVatDye(String vatDye) {
        this.vatDye = vatDye;
    }

    public String getColorNo() {
        if(colorNo == null){
            this.colorNo = "";
        }
        return colorNo;
    }

    public void setColorNo(String colorNo) {
        this.colorNo = colorNo;
    }

    public String getEpc() {
        return epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public Label() {
    }

    @Override
    public String toString() {
        return "布票号：" + ticketNo  +
                ", EPC：" + epc +
                ", 缸号：" + vatDye +
                ", 重量：" + weight +
                ", 颜色：" + colorName +
                ", 布种名称：" + clothName +
                ", 布种：" + clothType +
                ", 色号：" + colorNo +
                '}';
    }
}
