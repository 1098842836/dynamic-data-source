package com.yl.dynamicdatasource.test.handle;


import com.yl.dynamicdatasource.annotation.EntityHandlerType;
import com.yl.dynamicdatasource.test.EntityHandler;

import java.util.Map;


/**
 * @Description //设备大类处理器
 * @Date 2022/8/3
 * @Author yl
 */
@EntityHandlerType({
        "1"
})
public class DeviceHandler implements EntityHandler {
    @Override
    public void afterGet(Map<String, Object> props) {
        System.out.println("afterGet方法");

    }

    @Override
    public void beforeGetList(Long specId, Map<String, Object> params) {
        System.out.println("beforeGetList方法");
    }
}
