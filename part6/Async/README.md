
### Bean을 주입받아서 Bean안에 있는 Method 호출
--------------------------

	System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
	
	emailService.sendMail();
	emailService.sendMailWithCustomThreadPool();

결과 
	[asyncCall_1] :: http-nio-8080-exec-1
	[sendMail] :: defaultTaskExecutor-1
	[sendMailWithCustomThreadPool] :: messagingTaskExecutor-1

### 인스턴스 선언 후 인스턴스의 Method를 호출
--------------------------
	System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
	
	EmailService emailService = new EmailService();
	emailService.sendMail();
	emailService.sendMailWithCustomThreadPool();

결과 
	[asyncCall_2] :: http-nio-8080-exec-2
	[sendMail] :: http-nio-8080-exec-2
	[sendMailWithCustomThreadPool] :: http-nio-8080-exec-2


### 클래스 내부 Method에 Async annotation으로 호출
--------------------------
	System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
	sendMail();

결과
	[asyncCall_3] :: http-nio-8080-exec-3
	[sendMail] :: http-nio-8080-exec-3

## asyncCall_1만 비동기로 작동한 이유
![[Pasted image 20240319175435.png]]
![Pasted image 20240319175435](https://github.com/choiseongho1/fastcampus-10-project-backend-web/assets/67409492/830145a8-27be-4cc4-acb7-ccfefead5239)
1. **Caller**: 이것은 비동기 메서드를 호출하는 객체 또는 코드를 나타냅니다. 사용자 또는 다른 서비스가 될 수 있으며, 작업을 요청하고 결과를 기다리지 않고 바로 다음 작업으로 넘어갈 수 있습니다.   
2. **Proxy Object**: Caller에 의해 호출되면, 실제 작업을 비동기적으로 처리하기 위해 비동기 메서드를 캡슐화하는 프록시 객체로 요청을 전달합니다. 이 객체는 비동기 실행을 관리하고, 완료 시점이나 상태 변경 등을 Caller에 알리는 역할을 할 수 있습니다.
3. **Async Method**: 실제로 비동기적으로 실행될 메서드입니다. 이 메서드는 새로운 스레드나 스레드 풀에 의해 처리되며, 완료되면 그 결과를 Proxy Object를 통해 반환합니다.

비동기 처리 흐름
- Caller가 작업을 요청합니다(1번).
- Proxy Object는 이 요청을 받고 Async Method에 작업 실행을 위임합니다(2번).
- Async Method는 비동기적으로 실행되어, 작업을 완료하고 결과를 Proxy Object에 반환합니다.
- Caller는 Async Method의 완료를 기다리지 않고 바로 다른 작업을 계속할 수 있으며, 결과가 준비되면 이를 처리할 수 있습니다.
