package com.wsmhz.common.business.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * create by tangbj on 2018/5/20
 */
@Setter
@Getter
public class FtpProperties {
    private String ip;

    private String user;

    private String pwd;

    private String httpPrefix;
}
