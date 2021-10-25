package com.tyr.gateway.config;

import com.alibaba.fastjson.JSON;
import com.netflix.client.ClientException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.tyr.base.bean.response.ResponseData;
import com.tyr.base.utils.Constants;
import com.tyr.base.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Class : ServiceFallbackProvider
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/23 17:50
 * @Version : 1.0
 */
@Slf4j
@Component
public class ServiceFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        return Constants.BUSINESS_SERVICE;
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.error("服务异常", cause);
        ResponseData<Void> res;
        if (cause instanceof HystrixTimeoutException || cause instanceof ClientException) {
            res = ResponseUtil.timeout();
        } else {
            res = ResponseUtil.hystrix();
        }
        return this.fallback(JSON.toJSONString(res));
    }


    public ClientHttpResponse fallback(String message) {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(message.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };

    }
}
