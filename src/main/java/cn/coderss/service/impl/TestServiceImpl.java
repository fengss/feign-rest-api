package cn.coderss.service.impl;

import cn.coderss.bean.DTO.TestDTO;
import cn.coderss.service.ITestService;
import org.springframework.stereotype.Service;

/**
 * 测试服务
 *
 * @author shenwei
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    public TestDTO doSomething() {
        return TestDTO.builder().code("1").data(null).msg("msg").build();
    }
}
