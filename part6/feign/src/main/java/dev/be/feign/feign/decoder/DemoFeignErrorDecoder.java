package dev.be.feign.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class DemoFeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus httpStatus = HttpStatus.resolve(response.status());

        if(httpStatus == HttpStatus.NOT_FOUND){
            System.out.println("[DemoFeignErrorDecoder] Http status : " + httpStatus);
            throw new RuntimeException(String.format("[RuntimeException] http Status is %s", httpStatus));
        }

        return errorDecoder.decode(s, response);
    }
}
