package dev.be.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    public void asyncCall_1(){
        // Bean을 주입받아서 주입받은 Bean안에 있는 Method 호출
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();

        /*
        - 비동기로 동작할 수 있게 Sub Thread에게 위임
        - emailService.sendEmail();
        */

//        [asyncCall_1] :: http-nio-8080-exec-1
//        [sendMail] :: defaultTaskExecutor-1
//        [sendMailWithCustomThreadPool] :: messagingTaskExecutor-1
    }

    public void asyncCall_2(){
        // Bean주입이 아닌 인스턴스 선언 후 인스턴스의 Method를 호출할때 비동기로 호출하는지 확인
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();

//        [asyncCall_2] :: http-nio-8080-exec-2
//        [sendMail] :: http-nio-8080-exec-2
//        [sendMailWithCustomThreadPool] :: http-nio-8080-exec-2
    }

    public void asyncCall_3(){
        // 해당 클래스 내부 Method에 Async annotation으로 비동기로 동작하는지 확인
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();

//        [asyncCall_3] :: http-nio-8080-exec-3
//        [sendMail] :: http-nio-8080-exec-3

    }

    @Async
    public void sendMail(){
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());

    }
}
