package com.cheng.interfaces;

import com.cheng.beans.MethodInfo;
import com.cheng.beans.ServerInfo;

/**
 * @author cheng
 *         2018/7/7 17:32
 */
public interface RestHandler {

    /**
     * 初始化服务器信息
     *
     * @param serverInfo
     */
    void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回接口
     *
     * @param methodInfo
     * @return
     */
    Object invokeRest(MethodInfo methodInfo);
}
