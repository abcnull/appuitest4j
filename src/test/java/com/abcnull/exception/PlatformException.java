package com.abcnull.exception;

/**
 * 自定义异常：platformName 有问题
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/3 23:13
 */
public class PlatformException extends RuntimeException {
    /**
     * 手机系统名不匹配异常
     */
    public PlatformException() {
        super();
    }

    /**
     * 手机系统名不匹配异常
     *
     * @param s message
     */
    public PlatformException(String s) {
        super(s);
    }
}
