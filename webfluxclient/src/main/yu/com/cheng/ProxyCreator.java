package com.cheng;

/**
 * 创建代理类接口
 *
 * @author cheng
 *         2018/7/7 17:13
 */
public interface ProxyCreator {

    /**
     * 创建代理类
     *
     * @param type
     * @return
     */
    Object createProxy(Class<?> type);
}
