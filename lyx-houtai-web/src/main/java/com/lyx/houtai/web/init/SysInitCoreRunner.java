package com.lyx.houtai.web.init;

import com.lyx.houtai.web.util.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Order(1)
@Slf4j
public class SysInitCoreRunner implements CommandLineRunner {

    @Override
    public void run(String... var1) throws Exception {
        log.info("===============================================");
        log.info("===============开始系统初始化执行===============");
        log.info("===============================================");
        try {
            log.info("===============开始加载sql监控本地配置文件===============");
            log.info("本次启动服务的唯一标识为：{}", UUID.randomUUID().toString());
            log.info("本次启动服务的ip为：{}", IPUtil.getLocalIP());
            log.info("===============加载sql监控本地配置文件结束===============");
        } catch (Exception e) {
            log.error("核心初始化异常: {}", e.getMessage(),e);
        }
        log.info("=====================初始化sql监控常量开始=====================");
        log.info("初始化是否开启sql打印SqlMonitorConstant.SQL_MONITOR："+"1111");
        log.info("初始化超过多少秒算慢sql（毫秒）SqlMonitorConstant.SLOW_SQL_MILLISECOND："+"0");
        log.info("初始化查询的返回结果超过多少条进行记录SqlMonitorConstant.SELECT_SQL_RETURN_COUNT："+"1000");
        log.info("初始化返回的对象大小超过多少进行记录(字节)SqlMonitorConstant.SELECT_SQL_RETURN_OBJ_SIZE："+"10240");
        log.info("=====================初始化sql监控常量结束=====================");
        log.info("===============================================");
        log.info("===============结束系统初始化执行===============");
        log.info("===============================================");
    }


}
