# 로그 추적기

## app.v0
- OrderControllerV0
	- orderItem : orderService.orderItem call
- OrderServiceV0
	- save : orderRepository.save call
- OrderRepositoryV0
	- save : itemId가 ex 일 경우 exception 발생
	

## trace
- TraceId : level 및 랜덤id 구성
- TraceStatus : 로그의 상태 정보 구성
	- traceId : 내부에 트랜잭션ID와 level을 가지고 있다.
	- startTimeMs  : 로그 시작시간이다.
	- message  : 시작시 사용한 메시지이다.
	
	
## trace.logtrace
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
	
## trace.callback TemplateCallBackPatten
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
- 



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