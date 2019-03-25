package cn.coderss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignForceClients;

/**
 * 启动类
 *
 * @author shenwei
 */
@SpringBootApplication
@EnableFeignForceClients(force = true)
public class FeignWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignWebApplication.class);
    }
}
