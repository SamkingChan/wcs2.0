package com.ridko.wcs.service.printer.impl;

import com.ridko.wcs.service.printer.PrinterService;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author SexyChan
 * @Date 2018/11/22 09:19
 */
@Service
public class PrinterServiceImpl implements PrinterService {

    @Autowired
    PropertiesConfiguration propertiesConfiguration;

    private Connection connection;


    @Override
    public Boolean print(String zpl) {
        try {
            // 创建打印机连接
            connection = new TcpConnection(propertiesConfiguration.getString("printer.ip"), propertiesConfiguration.getInt("printer.port"));
//            connection = new TcpConnection("192.168.0.102", 9100);
            byte[] content = zpl.getBytes("GB18030");
            connection.open();
            connection.write(content);
            connection.close();
            return true;
        } catch (Exception e) {
            try {
                connection.close();
            } catch (ConnectionException ce) {
                ce.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
    }
}
