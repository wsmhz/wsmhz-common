package com.wsmhz.common.business.exception;

import com.wsmhz.common.business.response.ServerResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by tangbj on 2018/5/19
 * 在进入Controller之前，譬如请求一个不存在的地址，404错误。
 */
@RestController
public class FinalExceptionHandler implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public ServerResponse<String> error(HttpServletResponse response, HttpServletRequest request) {
        // 错误处理逻辑
        if(response.getStatus() == HttpStatus.FORBIDDEN.value()){
            return ServerResponse.createByErrorMessage("你没有权限访问");
        }else{
            return ServerResponse.createByErrorMessage("请求服务器错误");
        }
    }
}
