package com.wsmhz.common.business.dto;

/**
 * Created By TangBiJing On 2019/5/5
 * Description:
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
