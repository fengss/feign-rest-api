package cn.coderss.controller;

import cn.coderss.client.FakeWeatherClient;
import cn.coderss.client.WeatherClient;
import com.ybxx.util.bean.BO.ResultBO;
import com.ybxx.util.result.Results;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

/**
 * 首页控制器
 *
 * @author shenwei
 */
@RestController
@Slf4j
@Validated
public class IndexController {

    @Autowired
    WeatherClient weatherClient;

    @Autowired
    FakeWeatherClient fakeWeatherClient;

    @GetMapping("")
    public ResultBO index(){
        log.info("data:{}", fakeWeatherClient.api("杭州"));
        return Results.success("获取成功", weatherClient.work("湖州"));
    }

    @GetMapping("test")
    public ResultBO test(@RequestParam(value = "telephone", defaultValue = "")
                             @NotEmpty(message = "手机号不能为空")
                             @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}", message = "手机号格式不匹配") String telephone,
                         @RequestParam(value = "password", defaultValue = "")
                             @NotEmpty(message = "密码不能为空") String reqPassword){
        return Results.success("tel:"+ telephone + ",reqPassword:" + reqPassword);
    }
}
