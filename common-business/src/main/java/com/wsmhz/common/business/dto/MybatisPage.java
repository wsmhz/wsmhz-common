package com.wsmhz.common.business.dto;

/**
 * create by tangbj on 2018/5/12
 * Mybatis 分页信息封装
 */
public class MybatisPage<T> {
    private long total;
    private T rows;

    public MybatisPage(long total, T rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getRows() {
        return rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }
}
