package com.veganplanet.common.core.request.model;

import lombok.Data;

/**
 * 用户信息
 *
 * @date 2024/2/27 22:41
 */
@Data
public class AuthUserInfo {
    /**
     * 用户编号
     */
    private Long userNo;
    /**
     *用户名
     */
    private String userName;
    /**
     *手机号
     */
    private String phone;
    /**
     *头像
     */
    private String avatar;
    /**
     *昵称
     */
    private String nickName;
}
