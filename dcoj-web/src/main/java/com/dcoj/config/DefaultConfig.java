package com.dcoj.config;

/**
 * 默认配置
 *
 * @author Leon WANGQING
 */
public class DefaultConfig {

    public static final int ADMIN_ROLE = 9;

    public static final int PROGRAM_USED_TIME = 3;

    public static final int PROGRAM_USED_MEMORY = 256;

    // 排行榜刷新时间 5min
    public static final int LEADERBOARD_REFRESH_TIME = 5;

    // 验证码过期时间 min
    public static final int CAPTCHA_EXPIRED_TIME = 5;

    /** token过期时间 */
    public static final int TOKEN_EXPIRED_TIME = 7 * 60 * 24;
    /** token默认k前缀 */
    public static final String TOKEN = "token_";

}
