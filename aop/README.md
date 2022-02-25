# 로그 추적기

## hello.aop.order
- OrderController
	- orderItem : orderService.orderItem call
- OrderService
	- save : orderRepository.save call
- OrderRepository
	- save : itemId가 ex 일 경우 exception 발생
	
	
## hello.aop.order.aop
- AspectV1 : @Around 사용으로 aop 기본 구성
- AspectV2 : @Pointcut 사용으로 @Around 맵핑하여 aop 구성
- AspectV3 : @Pointcut 조합으로 @Around 맵핑하여 aop 구성
- AspectV4Pointcut : @Pointcut 참조하여 @Around 맵핑하여 aop 구성
- AspectV5Order : @Order로 aop 실행 순서 구성
- AspectV6Advice : 어드바이스 종류에 따라 aop 구성
	- doBefore : 조인포인트 실행전 실행
	- doReturn : 메소드 실행이 정상적으로 반환될때 실행
	- doThrowing : 메서드 실행이 예외를 던져서 종료될 때 실행
	- doAfter : 메서드 실행이 종료되면 실행된다. (finally를 생각하면 된다.)


##hello.aop.member
- MemberService :
- MemberServiceImpl :
	- hello : @MethodAop 어노테이션을 지정


##hello.aop.member.annotation
- ClassAop : 특정 클래스에 custom interface를 지정
- MethodAop : 특정 메소드에 custom interface를 지정


##hello.aop.exam
- ExamRepository : 
	- save
		- @Trace 어노테이션 aop 설정
		- @Retry 어노테이션 aop 설정
- ExamService : 
	- request : @Trace 어노테이션 aop 설정


##hello.aop.exam.aop
- TraceAspect : 로그 AOP를 설정한다.
- RetryAspect : 재시도 AOP를 설정한다.


##hello.aop.exam.annotation;
- Trace : @Trace
	- 커스텀 어노테이션
	- 로그를 남긴다.
- Retry : @Retry
	- 커스텀 어노테이션
	- 재시도한다.


## hello.aop.internalcall
- CallServiceV0
	- external : 내부 internal 메소드 test
	- internal : 기본 로그
- CallServiceV1 : 자기자신 의존성 주입 및 setter 설정
	- external : 내부 internal 메소드 test
- CallServiceV2 : ObjectProvider(Provider), ApplicationContext를 사용해서 지연(LAZY) 조회
	- external : 내부 internal 메소드 test
- CallServiceV3 : 구조를 변경(internal 분리)
	- external : 내부 internal 메소드 test
	
	
## hello.aop.internalcall.aop
- CallLogAspect : hello.aop.internalcall..*.*(..) aop를 주입한다.
	
	
## JUINIT TEST 기본 파라미터
- AopTest
	- aopInfo : 프록시를 타고 있는지 확인
	- success : 정상 파라미터 일 경우 성공 확인
	- exception : 에러 파라미터 일 경우 성공 확인


## JUINIT TEST Execution 
- ExecutionTest
	- printMethod : 리플렉션으로 hello mtehod 경로확인
	- exactMatch : aop 대상여부 확인
	- allMatch : 전체 package aop 확인
	- nameMatch : hello 메소드 aop 확인
	- nameMatchStar1 : hel로 시작되는 메소드 aop 확인
	- nameMatchStar2 : el이 포함되어 있는 메소드 aop 확인
	- nameMatchFalse : nono로 메소드 aop 확인
	- packageExactMatch1 : 명확한 pacakge의 메소드 aop 확인
	- packageExactMatch2 : member의 모든하위.모든하위에 메소드 aop 확인
	- packageExactMatchFalse : hello.aop.*.*(..)) 해당 pacakge 메소 aop 확인
	- packageMatchSubPackage1 : member 하위 package 모든 메소드 aop 확인
	- packageMatchSubPackage2 : aop 하위 package 모든 메소드 aop 확인
	- typeExactMatch : 특정 클래스의 메소드 aop 확인
	- typeMatchSuperType :  부모 클래스에 없는 메소드 aop 확인 (true)
	- typeMatchSuperTypeMethodFalse : 부모 클래스에 없는 메소드 aop 확인 (false)


## JUINIT TEST within 
- WithinTest : 타입이 매칭되면 그 안의 메서드(조인 포인트)들이 자동으로 매칭된다
	- withinExact : MemberServiceImpl의 모든 메소드 호출
	- withinStar :  *Service* 포함되는 클래스에 모든 메소드 호출
	- withinSubPackage :  모든 메소드 호출
	- withinSuperTypeFalse : within 부모 클래스로 조인포인트로 aop 확인 (false)
	- executionSuperTypeTrue : execution 부모 클래스로 조인포인트로 aop 확인 (true_)


## JUINIT TEST args 
- ArgsTest
	- args : args 에 타입별로 확인한다.
	- argsVsExecution : args는 상위타입별로 확인이 가능하다. execution 불가능하다.


## JUINIT TEST @target, @within  
- AtTargetAtWithinTest
	- AtTargetAtWithinAspect
		- atTarget : 인스턴스 기준으로 모든 메서드의 조인 포인트를 선정, 부모 타입의 메서드도 적용
		- atWithin : 선택된 클래스 내부에 있는 메서드만 조인 포인트로 선정, 부모 타입의 메서드는 적용되지 않음


## JUINIT TEST annotation
- AtAnnotationTest
	- AtAnnotationAspect
		- doAtAnnotation : MethodAop 어노테이션이 있는 aop 적용
		
		
## JUINIT TEST bean
- BeanTest
	- BeanAspect
		- doLog : 특정 bean 이름으로 aop 적용
		
		
## JUINIT TEST ParameterTest
- ParameterTest
	- ParameterAspect
		- allMember :  joinPoint.getArgs()[0] 와 같이 매개변수를 전달 받는다.
		- logArgs1 : args(arg,..) 와 같이 매개변수를 전달 받는다.
		- logArgs2 : @Around 매개변수를 직접 전달받는다.
		- logArgs3 : @Before 매개변수를 직접 전달받는다.
		- thisArgs : 프록시 객체를 전달 받는다.
		- targetArgs : 실제 대상 객체를 전달 받는다.
		- atTarget : 타입의 애노테이션을 전달 받는다.
		- atWithin : 타입의 애노테이션을 전달 받는다.
		- atAnnotation : 애노테이션의 값을 전달 받는다.


## JUINIT TEST this,target
- ThisTargetTest
	- ThisTargetAspect : JDK, CGLIB 프록시의 차이에대한 aop
		- doThisInterface :  this타입은 부모 타입을 허용한다.
		- doTargetInterface : target타입은 부모 타입을 허용한다.
		- doThis : 
			- JDK프록시 : this타입은 구체클래스 AOP적용되지 않는다.
			- CGLIB프록시 : AOP적용 된다.
		- doTarget : target타입은 구체클래스 적용된다.
		

## JUINIT TEST AOP 실전
- ExamTest
	- @Import(TraceAspect.class) aop를 주입받는다.
	- test : 5번의 request로 5회때 exception을 발생시킨다.


## JUINIT TEST실무 주의사항
- CallServiceV0Test
	- external : 호출 시 내부 internal 메소드는 aop로 주입되지 않는다.
	- internal : 호출 시 aop 주입받는다.
- CallServiceV1Test
	- external : 호출 시 내부 internal 메소드는 aop로 주입받는다.
- CallServiceV2Test
	- external : 호출 시 지연(LAZY) 조회로 처리된 internal 메소드는 aop로 주입받는다.
- CallServiceV3Test
	- external : 호출 시 구조 변경된 internal 메소드는 aop로 주입받는다.
- ProxyCastingTest : JDK 동적 프록시의 한계 TEST
	- jdkProxy: 
		- 인터페이스 캐스팅 성공
		- 구체클래스 캐스팅 실패
	- cglibProxy:
		- 인터페이스 캐스팅 성공
		- 구체클래스 캐스팅 성공
- ProxyDIAspect : hello.aop..*.*(..) 해당되는 메소드 실행
	- ProxyDITest : CGLIB, JDK 동적 프록시 등 DI TEST

