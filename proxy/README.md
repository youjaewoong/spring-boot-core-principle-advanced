# 로그 추적기

- v1 : 인터페이스와 구현 클래스 - 스프링 빈으로 수동 등록
- v2 : 인터페이스 없는 구체 클래스 - 스프링 빈으로 수동 등록
- v3 : 컴포넌트 스캔으로 스프링 빈 자동 등록

- ProxyApplication :
	- scanBasePackages = "hello.proxy.app" 해당위치를 컴포넌트 스캔한다.
	- @Import({AppV1Config.class, AppV2Config.class}) 스프링 빈에 등록한다.


## app.v1
- OrderControllerV1 : 인터페이스
- OrderControllerV1Impl : 구현체
- OrderRepositoryV1 : 인터페이스
- OrderRepositoryV1Impl : 구현체
- OrderServiceV1 : 인터페이스
- OrderServiceV1Impl : 구현체


## app.v2
- OrderControllerV2 : 구현체
- OrderRepositoryV2 : 구현체
- OrderServiceV2 : 구현체


## app.v3
- OrderControllerV2 : 구현체 및 컴포넌트 등록됨
- OrderRepositoryV2 : 구현체 및 컴포넌트 등록됨
- OrderServiceV2 : 구현체 및 컴포넌트 등록됨


## hello.proxy.config.v1_proxy
- InterfaceProxyConfig : 인터페이스 Proxy 관련 Bean Configuration을 등록한다.
- ConcreteProxyConfig : 구현 Proxy 관련 Bean Configuration을 등록한다.


## hello.proxy.config.v1_proxy.interface_proxy
- OrderControllerInterfaceProxy : OrderControllerV1 인터페이스를 구현하여 proxy 객체화 한다.
- OrderRepositoryInterfaceProxy : OrderRepositoryV1 인터페이스를 구현하여 proxy 객체화 한다.
- OrderServiceInterfaceProxy : OrderServiceV1 인터페이스를 구현하여 proxy 객체화 한다.


## hello.proxy.config.v1_proxy.concrete_proxy
- OrderControllerConcreteProxy : OrderControllerV2 구현클래스를 proxy 객체화 한다.
- OrderRepositoryConcreteProxy : OrderRepositoryV2 구현클래스를 proxy 객체화 한다.
- OrderServiceConcreteProxy : OrderServiceV2 구현클래스를 구현하여 proxy 객체화 한다.


## hello.proxy.config.v2_dynamicproxy.handle
- LogTraceBasicHandler : InvocationHandler 인터페이스를 구현한다.
- LogTraceFilterHandler : InvocationHandler 인터페이스 구현 및 patterns 매개변수를 추가한다.


## hello.proxy.config.v2_dynamicproxy
- DynamicProxyBasicConfig : 동적 프록시 구현을 위한 LogTraceBasicHandler 처리 및 Controler, service, repository 등 설정한다.
- DynamicProxyFilterConfig : 동적 프록시 구현을 위한 LogTraceBasicHandler 처리 및 Controler, service, repository 등 설정 및 필터 대상을 설정한다.


## hello.proxy.config.v3_proxyfactory.advice
- LogTraceAdvice : MethodInterceptor 인터페이스를 구현한다.


## hello.proxy.config.v3_proxyfactory
- ProxyFactoryConfigV1 : Advisor를 통한 프록시 구현 및 인터페이스로 처리된 Controler, service, repository 등 설정한다.
- ProxyFactoryConfigV2 : Advisor를 통한 프록시 구현 및 구현체로 처리된 Controler, service, repository 등 등 설정한다.


## hello.proxy.config.v4_postprocessor.postprocessor
- PackageLogTraceProxyPostProcessor : 빈후처리기 BeanPostProcessor 인터페이스를 구현한다.
	- postProcessAfterInitialization : 타겟이되는 bean을 ProxyFactory에 등록한다.


## hello.proxy.config.v4_postprocessor
- BeanPostProcessorConfig :
	- bean으로 등록된 AppV1Config, AppV2Config 을 import한다.
	- PackageLogTraceProxyPostProcessor 를 bean으로 등록한다.
	- getAdvisor : pointcut, advice를 구현한다.


## hello.proxy.config.v5_autoproxy
- AutoProxyConfig :
	- build.gradle에 등록한 aop는 advice를 등록한 경우라면 프록시로 등록한다.
	- advisor1 : pointcut, advice를 등록한다.
	- advisor2 : AspectJExpressionPointcut 포인트컷 표현식을 사용하여 pointcut, advice를 등록한다. (실무에서는 이것만쓴다.)
	- advisor3 : noLog메서드는 로그를 제외하다록 포인트컷을 설정한다.


## hello.proxy.config.v6_aop
- AopConfig : LogTraceAspect Bean으로 등록한다.


## hello.proxy.config.v6_aop.aspect
- LogTraceAspect :
	- @Aspect : 어노테이이션 적용함으로 프록시 대상이 된다.
	- @Around : 포인트컷을 선언한다.
	- execute : 어드바이스를 통한 LogTrace를 구현한다.

## hello.proxy.config
- AppV1Config : v1 class bean 등록
- AppV2Config : v2 class bean 등록


## hello.advanced.trace
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
	
	
## JUINIT TEST proxyPatten
- Subject : 인터페이스
- RealSubject : Subject 구현 클래스
- ProxyPatternClient : 호출 클래스
- ProxyPatternTest : 테스트 클래스
	 - noProxyTest : ProxyPatternClient 호출
	 - cacheProxyTest : 캐싱된 ProxyPatternClient 호출


## JUINIT TEST decoratorPatten
- Component : 인터페이스
- RealComponent : Component 구현 클래스
- DecoratorPatternClient : 호출 클래스
- MessageDecorator: 데코레이터 호출 클래스
- DecoratorPatternTest : 테스트 클래스
	 - noDecorator : RealComponent 호출
	 - decorator1 : RealComponent -> MessageDecorator 객체 순으로 데이터를 꾸며준다.
	 - decorator2 : RealComponent -> MessageDecorator -> TimeDecorator 객체 순으로 데이터를 꾸며준다.


## JUINIT TEST 구체클래스로만 proxy 적용
- ConcreteLogic : 구현 클래스
	- operation : 핵심 기능 실행
- TimeProxy : ConcreteLogic 생성자로 받아 맴버변수에 할당한다.
	- operation : 재정의 하여 핵심기능을 실행한다.
- ConcreteClient : ConcreteLogic 생성자로 받아 맴버변수에 할당한다.
	- execute : operation 실행
- ConcreteProxyTest :
	- noProxy : ConcreteClient는 ConcreteLogic 핵심 기능을 주입받아 실행한다.
	- addProxy : 
		- TimeProxy는 ConcreteLogic 핵심 기능을 주입받는다.
		- ConcreteClient는 TimeProxy 핵심 기능을 주입받아 실행한다. 
		
		
## JUINIT TEST 리플렉션
- ReflectionTest : 
	- reflection0 : 일반적인 호출
	- reflection1 : 리플렉션 메소드 호출
	- reflection2 : 공통 메소드를 통한 리플렉션 메소드 호출

## JUINIT TEST 동적 프록시
- JdkDynamicProxyTest : 동적 프록시 TEST
	- dynamicA : TimeInvocationHandler 프록시 객체를 실행한다.
- AInterface : 인터페이스
- AImpl : AInterface 구현체
- BImpl : AInterface 구현체
- TimeInvocationHandler : 동적 프록시 InvocationHandler 구현체
	invoke : 프록시 대상클래스를 구현한다.

## JUINIT TEST CGLIB
- ConcreteService : 구체클래스
- ServiceInterface : 인터페이스
- ServiceImpl : ServiceInterface 구현클래스
- TimeMethodInterceptor : CGLIB MethodInterceptor 인터페이스 구현체
- CglibTest : 
	- TimeMethodInterceptor 프록시 객체를 실행한다.
	- 구체 클래스를 상속처럼 활용하여 프록시 처리한다.

## JUINIT TEST ADVICE
- TimeMethodInterceptor :
	- MethodInterceptor advice 인터페이스를 구현한다.
	- InvocationHandler 및 MethodInterceptor 프록시 처리가 가능하다.
- ProxyFactoryTest : 
	- interfaceProxy : 인터페이스 클래스를 프록시 처리한다.
	- concreteProxy : 구채클래스를 프록시 처리한다.
	- proxyTargetClass : 인터페이스지만 CGLIB 프록시 처리한다.
- AdvisorTest :
	- advisorTest1 : DefaultPointcutAdvisor 객체로 프록시 처리한다.
	- advisorTest2 : 직접 만든 포인트컷으로 프록시 처리한다.
	- advisorTest3 : 스프링이 제공하는 포인트컷
- MultiAdvisorTest :
	- multiAdvisorTest1 : 여러 프록시를 생성하여 처리한다.
	- multiAdvisorTest2 : 하나의 프록시로 여러 아드바이저를 생성하여 처리한다.

## JUINIT TEST 빈후처리
- BasicTest
	- basicConfig : bean basic 등록방법을 구현한다.
- BeanPostProcessorTest
	- BeanPostProcessorTest : BeanPostProcessor로 인터페이스를 구한하여 빈후처리로 등록방법을 구현한다.

## TEST URL

### V1 TEST
- http://localhost:8080/v1/request?itemId=hello

### V2 TEST
- http://localhost:8080/v2/request?itemId=hello

### V3 TEST
- http://localhost:8080/v3/request?itemId=ex

### V4 TEST
- http://localhost:8080/v4/request?itemId=ex

### V5 TEST
- http://localhost:8080/v5/request?itemId=hello