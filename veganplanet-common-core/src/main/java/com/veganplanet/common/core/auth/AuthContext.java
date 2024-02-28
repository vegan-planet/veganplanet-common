package com.veganplanet.common.core.auth;

import com.veganplanet.common.core.request.model.AuthUserInfo;

/**
 * 登录用户信息
 *
 * @date 2024/2/27 22:38
 */
public class AuthContext {

    /**
     * 登录用户信息
     */
    private static final ThreadLocal<AuthUserInfo> LOGIN_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置登录用户信息
     *
     * @param authUserInfo 登录用户信息
     */
    public static void setLoginInfo(AuthUserInfo authUserInfo) {
        LOGIN_THREAD_LOCAL.set(authUserInfo);
    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    public static AuthUserInfo getLoginInfo() {
        return LOGIN_THREAD_LOCAL.get();
    }

    /**
     * 清除登录用户信息
     */
    public static void clearLoginInfo() {
        LOGIN_THREAD_LOCAL.remove();
    }

}
