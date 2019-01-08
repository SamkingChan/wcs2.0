package com.ridko.wcs.domain.common;

public class Config {

    private String mqttClient;
    private String mqttIp;
    private String mqttPort;
    private String mqttUsername;
    private String mqttPassword;
    private String mqttTopic;
    private String impinjIp;
    private String impinjPower;
    private String zebraIp;
    private String zebraPort;

    public String getMqttClient() {
        return mqttClient;
    }

    public void setMqttClient(String mqttClient) {
        this.mqttClient = mqttClient;
    }

    public String getMqttIp() {
        return mqttIp;
    }

    public void setMqttIp(String mqttIp) {
        this.mqttIp = mqttIp;
    }

    public String getMqttPort() {
        return mqttPort;
    }

    public void setMqttPort(String mqttPort) {
        this.mqttPort = mqttPort;
    }

    public String getMqttUsername() {
        return mqttUsername;
    }

    public void setMqttUsername(String mqttUsername) {
        this.mqttUsername = mqttUsername;
    }

    public String getMqttPassword() {
        return mqttPassword;
    }

    public void setMqttPassword(String mqttPassword) {
        this.mqttPassword = mqttPassword;
    }

    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    public String getImpinjIp() {
        return impinjIp;
    }

    public void setImpinjIp(String impinjIp) {
        this.impinjIp = impinjIp;
    }

    public String getImpinjPower() {
        return impinjPower;
    }

    public void setImpinjPower(String impinjPower) {
        this.impinjPower = impinjPower;
    }

    public String getZebraIp() {
        return zebraIp;
    }

    public void setZebraIp(String zebraIp) {
        this.zebraIp = zebraIp;
    }

    public String getZebraPort() {
        return zebraPort;
    }

    public void setZebraPort(String zebraPort) {
        this.zebraPort = zebraPort;
    }
}
