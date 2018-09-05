package com.wsmhz.common.data.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * create by tangbj on 2018/4/25
 */
public class Domain implements Serializable {

    private Date createDate;

    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
