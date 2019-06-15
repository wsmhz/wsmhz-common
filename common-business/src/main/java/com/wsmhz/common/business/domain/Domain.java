package com.wsmhz.common.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * create by tangbj on 2018/4/25
 */
public class Domain implements Serializable {

    private Date createDate;

    private Date updateDate;

    private Date deleteDate;

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

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Domain(Date createDate, Date updateDate, Date deleteDate) {
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

    public Domain() {
    }
}
