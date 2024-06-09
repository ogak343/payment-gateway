package com.example.user.configuration;

import com.example.user.helper.ApplicationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        return ApplicationException.throwError(response.status(), response.reason());
    }
}