package com.ridko.wcs.service.config.impl;

import com.google.gson.Gson;
import com.ridko.wcs.service.config.ConfigService;
import com.ridko.wcs.utils.HttpClientUtil;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConfigServiceImpl implements ConfigService {

    /**
     * 云端服务器IP
     */
    @Value("${wms.ip}")
    private String wmsIp;

    /**
     * 云端服务器端口
     */
    @Value("${wms.port}")
    private String wmsPort;

    /**
     * 云端服务器登录账号
     */
    @Value("${wms.account}")
    private String account;

    /**
     * 云端服务器登陆密码
     */
    @Value("${wms.password}")
    private String password;


    @Autowired
    PropertiesConfiguration propertiesConfiguration;

    @Override
    public boolean doRefresh() throws Exception {
        String json = HttpClientUtil.doGet("http://" + wmsIp + ":" + wmsPort + "/shYf/sh/rfid/getwcsconfig.sh?account=" + account + "&password=" + password);
        Gson gson = new Gson();
        if ("".equals(json)) {
            return false;
        }
        Map map = gson.fromJson(json, Map.class);
        String code = map.get("code").toString();
        if ("0".equals(code)) {
            return false;
        }
        String mqttClient = map.get("mq_client").toString();
        String mqttIp = map.get("mq_ip").toString();
        String mqttPort = map.get("mq_port").toString();
        String mqttUsername = map.get("mq_username").toString();
        String mqttPassword = map.get("mq_password").toString();
        String mqttTopic = map.get("mq_topic").toString();
        String impinjIp = map.get("read_ip").toString();
        String impinjPower = map.get("read_power").toString();
        String zebraIp = map.get("print_ip").toString();
        String zebraPort = map.get("print_port").toString();

//        String printerType = map.get("printer_type").toString();
//        String zebraModel = map.get("zebra_model").toString();

//        String mqttClient = config.getMqttClient();
//        String mqttIp = config.getMqttIp();
//        String mqttPort = config.getMqttPort();
//        String mqttUsername = config.getMqttUsername();
//        String mqttPassword = config.getMqttPassword();
//        String mqttTopic = config.getMqttTopic();
//        String impinjIp = config.getImpinjIp();
//        String impinjPower = config.getImpinjPower();
//        String zebraIp = config.getZebraIp();
//        String zebraPort = config.getZebraPort();
        propertiesConfiguration.setProperty("mqtt.client", mqttClient);
        propertiesConfiguration.setProperty("mqtt.ip", mqttIp);
        propertiesConfiguration.setProperty("mqtt.port", mqttPort);
        propertiesConfiguration.setProperty("mqtt.username", mqttUsername);
        propertiesConfiguration.setProperty("mqtt.password", mqttPassword);
        propertiesConfiguration.setProperty("mqtt.topic", mqttTopic);
        propertiesConfiguration.setProperty("impinj.ip", impinjIp);
        propertiesConfiguration.setProperty("impinj.power", impinjPower);
        propertiesConfiguration.setProperty("printer.ip", zebraIp);
        propertiesConfiguration.setProperty("printer.port", zebraPort);
//        propertiesConfiguration.setProperty("printer.type",printerType);
//        propertiesConfiguration.setProperty("zebra.model",zebraModel);
        return true;
    }
}
