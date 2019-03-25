package cn.coderss.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author shenwei
 */
@FeignClient(name = "weather", url = "https://www.tianqiapi.com/")
public interface WeatherClient {

    @GetMapping(value = "api/?version=v1", consumes = {"Content-Type: application/json;charset:utf-8;"})
    Map work(@RequestParam("city") String city);
}
