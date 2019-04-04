package com.wsmhz.common.business.utils;

import com.wsmhz.common.business.exception.BussinessException;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言判断工具类
 */
public class AssertUtil<T> {

    private T t;

    private AssertUtil(T t) {
        this.t = t;
    }

    /**
     * 包装对象
     */
    public static <T> AssertUtil<T> of(T value) {
        return new AssertUtil<>(value);
    }

    /**
     * 当对象为 Null 时抛出异常
     * @param object
     *         对象
     * @param message
     *         异常消息
     */
    public static <T> T requireNonNull(T object, String message) {
        if (Objects.isNull(object)) {
            throw new BussinessException(message);
        }
        return object;
    }

    /**
     * 当集合为空时抛出异常
     * @param collection
     *         集合
     * @param message
     *         异常消息
     */
    public static void requireNotEmpty(Collection collection, String message) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new BussinessException(message);
        }
    }

    /**
     * 当字符串为空时抛出异常
     * @param string
     *         字符串
     * @param message
     *         异常消息
     */
    public static String requireNotBlank(String string, String message) {
        if (Objects.isNull(string) || string.isEmpty()) {
            throw new BussinessException(message);
        }
        return string;
    }

    /**
     * 当结果为False时抛出异常
     * @param result
     *         获取返回值
     * @param message
     *         异常消息
     */
    public static void requireTrue(boolean result, String message) {
        if (!result) {
            throw new BussinessException(message);
        }
    }
}
