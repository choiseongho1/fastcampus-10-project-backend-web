package dev.be.feign.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(staticName = "of")
public class DemoFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        //Get 요청인 경우
        if(template.method() == HttpMethod.GET.name()){
            System.out.println("[Get] [DemoFeignInterceptor] queries : " + template.queries());
            return ;
        }

        //Post 요청인 경우
        String encodedRequestBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        System.out.printf("[Post] [DemoFeignInterceptor] requestBody : " + encodedRequestBody);

        String convertRequestBody = encodedRequestBody;
        template.body(convertRequestBody);

    }
}
