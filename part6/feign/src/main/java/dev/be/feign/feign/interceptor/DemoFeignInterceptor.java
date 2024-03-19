package dev.be.feign.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

@RequiredArgsConstructor(staticName = "of")
public class DemoFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) { // 필요에 따라 template 필드 값을 활용하자!

        // get 요청일 경우
        if (template.method() == HttpMethod.GET.name()) {
            System.out.println("[GET] [DemoFeignInterceptor] queries : " + template.queries());
            // ex) [GET] [DemoFeignInterceptor] queries : {name=[CustomName], age=[1]}
            return;
        }

        // post 요청일 경우
        String encodedRequestBody = StringUtils.toEncodedString(template.body(), UTF_8);
        System.out.println("[POST] [DemoFeignInterceptor] requestBody : " + encodedRequestBody);
        // ex) [POST] [DemoFeignInterceptor] requestBody : {"name":"customName","age":1}

        // Do Something
        // ex) requestBody 값 수정 등등

        // 새로운 requestBody 값으로 설정
        template.body(encodedRequestBody);
    }
}
