package com.zyc.mrcweb.util;

import com.minlia.cloud.body.StatefulBody;
import com.minlia.cloud.body.impl.FailureResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH, produces = {MediaType.APPLICATION_JSON_VALUE})
    public StatefulBody buildBody(HttpServletRequest request) {
        Map<String, Object> errorAttributes = getErrorAttributes(request);
        Integer status = (Integer) errorAttributes.get("status");
        String path = (String) errorAttributes.get("path");
        String messageFound = (String) errorAttributes.get("message");
        String message = "";
        String trace = "";
        if (!StringUtils.isEmpty(path)) {
            message = String.format("Requested path %s with result %s", path, messageFound);
        }
        trace = (String) errorAttributes.get("trace");
        if (!StringUtils.isEmpty(trace)) {
            message += String.format(" and trace %s", trace);
        }
        return FailureResponseBody.builder().code(0).status(status).message(message).build();
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        WebRequest webRequest=new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
    }
}