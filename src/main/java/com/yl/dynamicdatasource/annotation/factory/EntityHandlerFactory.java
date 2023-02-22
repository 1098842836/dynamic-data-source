package com.yl.dynamicdatasource.annotation.factory;

import com.yl.dynamicdatasource.test.EntityHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体处理器工厂
 *
 * @author Kang
 * @Date 2021-09-17
 */
@Slf4j
@Component
public class EntityHandlerFactory extends ApplicationObjectSupport implements InitializingBean {
    /**
     * Spring容器上下文
     */
    private static ApplicationContext ctx;

    /**
     * 处理类集合
     */
    private volatile static Map<String, Class<? extends EntityHandler>> MAP = new ConcurrentHashMap<>();

    /**
     * 是否已注册
     *
     * @param specId
     * @return
     */
    public static boolean registered(String specId) {
        return MAP.get(specId) != null;
    }

    /**
     * 注册处理器
     *
     * @param specId
     * @param clazz
     * @return
     */
    public static Class<? extends EntityHandler> register(String specId, Class<? extends EntityHandler> clazz) {
        return MAP.put(specId, clazz);
    }

    /**
     * 通过规格，查询处理类，如果未查到，则查询其父类的处理器，一直到顶层
     *
     * @param specId 规格ID
     * @return 处理类实例
     */
    public EntityHandler getInstance(String specId  ) {
        if (null == specId) {
            throw new IllegalArgumentException("[specId]不能为空.");
        }
        Class<? extends EntityHandler> clazz = MAP.get(specId);
        if (null != clazz) {
            return ctx.getBean(clazz.getName(), clazz);
        } else {
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ctx = this.getApplicationContext();
    }
}
