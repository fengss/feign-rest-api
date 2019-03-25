package cn.coderss.bean.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shenwei
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDTO{
    /**
     * 编码
     */
    private String code;
    private String msg;
    private String data;
}
