package com.ridko.wcs.config;

import lombok.extern.java.Log;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

/**
 * MQTT 配置
 *
 * @author smitea
 * @since 2018-10-17
 */
@Log
@Configuration
public class MqttConfig {

  @Autowired
  PropertiesConfiguration propertiesConfiguration;

  @Bean
  public MQTT mqtt() throws URISyntaxException {
    MQTT mqtt = new MQTT();
    mqtt.setClientId(propertiesConfiguration.getString("mqtt.client"));
    mqtt.setHost(propertiesConfiguration.getString("mqtt.ip"), propertiesConfiguration.getInt("mqtt.port"));
    mqtt.setUserName(propertiesConfiguration.getString("mqtt.username"));
    mqtt.setPassword(propertiesConfiguration.getString("mqtt.password"));
    return mqtt;
  }

  /**
   * MQTT连接器(在创建该Bean的时候就已连接到ActiveMQ中),通过该连接器就可以实现消息的推送和订阅
   *
   * @param mqtt MQTT配置参数
   * @return MQTT连接器
   */
  @Bean
  public FutureConnection futureConnection(MQTT mqtt) {
    FutureConnection futureConnection = mqtt.futureConnection();
    futureConnection.connect().then(new Callback<Void>() {
      @Override
      public void onSuccess(Void aVoid) {
        log.info("MQTT 连接成功!");
      }

      @Override
      public void onFailure(Throwable throwable) {
        log.warning("MQTT 连接失败:!" + throwable.getMessage());
      }
    });
    return futureConnection;
  }
}
