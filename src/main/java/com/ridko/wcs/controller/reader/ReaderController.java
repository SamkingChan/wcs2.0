package com.ridko.wcs.controller.reader;

import com.impinj.octane.*;
import lombok.extern.java.Log;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.QoS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author SexyChan
 * @Date 2018/11/23 16:31
 */

@Log
@RestController
@RequestMapping("/reader")
@CrossOrigin
public class ReaderController {

    @Autowired
    private FutureConnection futureConnection;

    @Autowired
    PropertiesConfiguration propertiesConfiguration;

    private static List<String> history = new CopyOnWriteArrayList<>();

    private ImpinjReader reader = new ImpinjReader();

    @GetMapping("/start")
    public ResponseEntity validate() {
        try {

            history.clear();

            reader.disconnect();

            reader.connect(propertiesConfiguration.getString("impinj.ip"));

            Settings settings = reader.queryDefaultSettings();

            ReportConfig report = settings.getReport();

            report.setIncludeAntennaPortNumber(true);

            report.setMode(ReportMode.Individual);

            // The reader can be set into various modes in which reader
            // dynamics are optimized for specific regions and environments.
            // The following mode, AutoSetDenseReader, monitors RF noise and interference and then automatically
            // and continuously optimizes the reader's configuration
            settings.setReaderMode(ReaderMode.AutoSetDenseReader);

            // set some special settings for antenna 1
            AntennaConfigGroup antennas = settings.getAntennas();
            antennas.disableAll();
            antennas.enableById(new short[]{1});
            antennas.getAntenna((short) 1).setIsMaxTxPower(false);
            antennas.getAntenna((short) 1).setTxPowerinDbm(propertiesConfiguration.getDouble("impinj.power"));

            reader.applySettings(settings);

            reader.setTagReportListener((impinjReader, tagReport) -> {
                List<Tag> tagList = tagReport.getTags();
                for (Tag tag : tagList) {
                    if (!history.contains(tag.getEpc().toHexString())) {
                        String epc = tag.getEpc().toHexString();
                        history.add(epc);
                        log.info(new Timestamp( System.currentTimeMillis())+" 当前读取到EPC："+epc+"  共读取到"+history.size()+"个EPC");
                        futureConnection.publish(new UTF8Buffer(propertiesConfiguration.getString("mqtt.topic")), new UTF8Buffer(epc), QoS.AT_LEAST_ONCE, false);
                    }
                }
            });
            reader.start();
        } catch (OctaneSdkException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace(System.out);
        }
        return ResponseEntity.status(200).body("成功！");
    }


    @GetMapping("/stop")
    public ResponseEntity close() {
        try {
            history.clear();
            reader.stop();
            reader.disconnect();
            return ResponseEntity.status(200).build();
        } catch (OctaneSdkException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).build();
        }

    }
}
