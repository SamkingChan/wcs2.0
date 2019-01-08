package com.ridko.wcs.controller.config;

import com.ridko.wcs.service.config.ConfigService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * 配置文件更新控制器
 */
@Log
@RestController
@RequestMapping("/config")
@CrossOrigin
public class ConfigController {

    @Autowired
    ConfigService configService;

    @PostMapping("/refresh")
    public ResponseEntity refreshConfig() {
        try {
            boolean result = configService.doRefresh();
            if(!result){
                return ResponseEntity.status(400).body("配置更新失败，请检查config文件夹下的application.properties配置文件是否有错");
            }
            log.info(new Timestamp(System.currentTimeMillis()) + this.getClass().getName() + " 配置新成功！");
            return ResponseEntity.status(200).body("配置更新成功");
        } catch (Exception e) {
            log.warning(new Timestamp(System.currentTimeMillis()) + this.getClass().getName() + " 配置新失败！ " + e.getMessage());
            return ResponseEntity.status(400).body("配置更新失败：" + e.getMessage());
        }
    }


}
