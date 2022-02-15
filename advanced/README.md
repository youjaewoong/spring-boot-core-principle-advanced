# 로그 추적기

## app.v0
- OrderControllerV0
	- orderItem : orderService.orderItem call
- OrderServiceV0
	- save : orderRepository.save call
- OrderRepositoryV0
	- save : itemId가 ex 일 경우 exception 발생
	

## app.v1 로깅 적용
- OrderControllerV1
	- orderItem : orderService.orderItem call
		- begin,end logMethod call
- OrderServiceV1
	- save : orderRepository.save call
		- begin,end logMethod call
- OrderRepositoryV1
	- save : itemId가 ex 일 경우 exception 발생
		- begin,end logMethod call
		

## app.v2 파라미터동기화
- OrderControllerV2
	- orderItem : orderService.orderItem call
		- begin,end logMethod call
- OrderServiceV2
	- save : orderRepository.save call
		- beginSync,end logMethod call
- OrderRepositoryV2
	- save : itemId가 ex 일 경우 exception 발생
		- beginSync,end logMethod call
		
		
## app.v3 인터페이스 적용된 LogTrace 적용
- OrderControllerV3
	- orderItem : orderService.orderItem call
		- begin,end logMethod call
- OrderServiceV3
	- save : orderRepository.save call
		- begin,end logMethod call
- OrderRepositoryV3
	- save : itemId가 ex 일 경우 exception 발생
		- begin,end logMethod call
		

## app.v4 TemplateMethodPatten 적용
- OrderControllerV4
	- call : 
		- orderItem : orderService.orderItem call
	- execute : LogTrace 실행
- OrderServiceV4
	- call
		- save : orderRepository.save call
	- execute : LogTrace 실행
- OrderRepositoryV4
	- save : itemId가 ex 일 경우 exception 발생
		- call : 저장 로직
		- execute : LogTrace 실행

## app.v5 TemplateCallBackPatten 적용
- OrderControllerV5
	- 생성자 주입 : TraceTemplate에 LogTrace 주입
	- call : 
		- orderItem : orderService.orderItem call
	- execute : LogTrace 실행
- OrderServiceV5
	- 생성자 주입 : TraceTemplate에 LogTrace 주입
	- call
		- save : orderRepository.save call
	- execute : LogTrace 실행
- OrderRepositoryV5
	- 생성자 주입 : TraceTemplate에 LogTrace 주입
	- save : itemId가 ex 일 경우 exception 발생
		- call : 저장 로직
		- execute : LogTrace 실행
		

## advanced Configuration
- LogTraceConfig : FieldLogTrace bean 등록


## advanced.trace
- TraceId : level 및 랜덤id 구성
- TraceStatus : 로그의 상태 정보 구성
	- traceId : 내부에 트랜잭션ID와 level을 가지고 있다.
	- startTimeMs  : 로그 시작시간이다.
	- message  : 시작시 사용한 메시지이다.
	

## advanced.trace.hellotrace
- HelloTraceV1 : TraceStatus 구현객체
	- begin : logging start TraceStatus 객체 생성
	- end : logging end complate call
	- exception : exception complate call
	- complate : logging 구현 method
	- addSpace : level 구현 method
- HelloTraceV2 : TraceStatus 구현객체
	- begin : logging start TraceStatus 객체 생성
	- beginSync : 기존 TraceId 에서 createNextId() 를 통해 level을 증가시킨다.
	- end : logging end complate call
	- exception : exception complate call
	- complate : logging 구현 method
	- addSpace : level 구현 method
	
	
## advanced.trace.logtrace
- LogTrace : begin, endm, exception interface 정의
- FildLogTrace : LogTrace 구현체
	- begin : logging start TraceStatus 객체 생성
	- beginSync : 기존 TraceId 에서 createNextId() 를 통해 level을 증가시킨다.
	- end : logging end complate call
	- exception : exception complate call
	- complate : logging 구현 method
	- addSpace : level 구현 method
- ThreadLocalLogTrace : 동시성 문제 해결 적용
	- begin : logging start TraceStatus 객체 생성
	- beginSync : 기존 TraceId 에서 createNextId() 를 통해 level을 증가시킨다.
	- end : logging end complate call
	- exception : exception complate call
	- complate : logging 구현 method
	- syncTraceId : traceIdHolder를 set 한다 (ThreadLocal)
	- releaseTraceId : traceIdHolder를 remove 한다 (ThreadLocal)
	- addSpace : level 구현 method
	
## advanced.trace.callback TemplateCallBackPatten
- TraceCallback : 제네릭 call interface 정의
- TraceTemplate : TraceCallback 구현체
	- 생성자 주입 : LogTrace를 주입받는다
	- execute :
		- 변하는 값을 정의한다.
		- 변하지 않는 값을 callback 처리한다.
		
## trace.template TemplateMethodPatten
- AbstractTemplate
	- execute : 변하지 않는 로직 구성
	- call : 변하는 로직 추상 메소드 구성
	

## JUINIT TEST 동시성 처리
- HelloTraceV1Test
	- begin, end, exception test
- HelloTraceV2Test
	- begin, beginSync, end, exception test
- FieldLogTraceTest
	- begin, syncTraceId, end, exception test
- FieldServiceTest : 동시성 문제 test
- ThreadLocalServiceTest : 동시성 문제 해결 test
- ThreadLocalLogTraceTest : 동시성 문제 해결 적용 test


## JUINIT TEST TemplateMethodPatten 
- AbstractTemplate
	- execute : 변하지 않는 로직 구성
	- call : 변하는 로직 추상 메소드 구성
- SubClassLogic1
	- call : AbstractTemplate 상속
- SubClassLogic2
	- call : AbstractTemplate 상속
- TemplateMethodTest
	- logic1 : 일반로직 구성
	- logic2 : 일반로직 구성
	- templateMethodV1 : 템플릿 메서드 패턴 적용
	- 템플릿 메서드 패턴, 익명 내부 클래스 사용
	
	
## JUINIT TEST StrategyPatten 
- ContextV1 : 필드에 전략을 보관하는 방식
	- execute : 변하지 않는 로직 구성
	- call : 변하는 로직 위임
- ContextV2 : 전략을 파라미터로 전달 받는 방식
	- execute : 변하지 않는 로직 구성
	- call : 변하는 로직 위임
- Strategy : 인터페이스 정의
- StrategyLogic1
	- call : 구현체 구현
- StrategyLogic2
	- call : 구현체 구현
- ContextV1Test
	- logic1 : 일반로직 구성
	- logic2 : 일반로직 구성
	- strategyV1 : 전략패턴 적용
	- strategyV2 : 전략 패턴 익명 내부 클래스1
	- strategyV3 : 전략 패턴 익명 내부 클래스2
	- strategyV4 : 전략 패턴, 람다
- ContextV2Test
	- strategyV1 : 전략패턴 적용
	- strategyV2 : 전략 패턴 익명 내부 클래스1
	- strategyV3 : 전략 패턴, 람다
	
	
## JUINIT TEST TemplateCallBackPatten 

- Callback : 인터페이스 정의
- TimeLogTemplate
	- execute : 변하지 않는 로직 구성
	- 인자값 Callback
- TemplateCallbackTest : 변하는 로직 구현
	- callbackV1 : 템플릿 콜백 패턴 - 익명 내부 클래스
	- callbackV2 : 템플릿 콜백 패턴 - 람다


## TEST URL
### V0 TEST
- http://localhost:8080/v0/request?itemId=ex

### V1 TEST
- http://localhost:8080/v1/request?itemId=ex

### V2 TEST
- http://localhost:8080/v2/request?itemId=ex

### V3 TEST
- http://localhost:8080/v3/request?itemId=ex

### V4 TEST
- http://localhost:8080/v4/request?itemId=ex

### V5 TEST
- http://localhost:8080/v5/request?itemId=hello