package com.wsmhz.common.business.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * create by tangbj on 2018/5/20
 */
@ConfigurationProperties(prefix = "wsmhz.business")
@Getter
@Setter
public class BusinessProperties {

    private FtpProperties ftp;
}
