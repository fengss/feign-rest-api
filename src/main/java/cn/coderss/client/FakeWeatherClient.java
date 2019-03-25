package cn.coderss.client;

import java.util.Map;

/**
 * 获取天气服务 Feign Client
 *
 * @author shenwei
 */
public interface FakeWeatherClient {

    Map api(String city);
}
