package com.example.card.helper;

import com.example.card.contants.ErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class ApplicationException extends ErrorResponseException {

    public ApplicationException(ProblemDetail detail) {
        super(HttpStatusCode.valueOf(detail.getStatus()), detail, null);
    }

    public static ApplicationException error(ErrorCode errorCode) {
        return new ApplicationException(ProblemDetail
                .forStatusAndDetail(HttpStatusCode.valueOf(errorCode.getCode()), errorCode.name()));
    }
}
