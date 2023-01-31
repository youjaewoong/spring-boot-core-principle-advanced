# spring-core-principle-advanced

### [advanced](https://github.com/youjaewoong/spring-core-principle-advanced/tree/master/advanced)
로그추적기, TemplateCallBackPatten 적용 등 V1 ~ V5 까지의 레벨로 점차적으로 개선해나가는 프로젝트 입니다.
- [TraceTemplate : TemplateCallBackPattern 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/trace/callback/TraceTemplate.java)
- [ThreadLocalLogTrace : 공통 log 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/trace/callback/TraceCallback.java)
- [LogTraceConfig : 공통 log 를 bean으로 주입시킨 후 싱글톤으로 구성](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/LogTraceConfig.java)
- [OrderControllerV5 : TraceTemplate 적용 및 API 호출](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/app/v5/OrderControllerV5.java)

### [aop](https://github.com/youjaewoong/spring-core-principle-advanced/tree/master/aop)
aop를 학습하며 custom interface를 통한 로그, 재시도  기능이 포함된 프로젝트 입니다.
- [AspectV6Advice : aop 기능](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/aop/src/main/java/hello/aop/order/aop/AspectV6Advice.java)
- [Pointcuts : ponitCut 정의](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/aop/src/main/java/hello/aop/order/aop/Pointcuts.java)
- [ExamTest : 재시도, 로깅 어노테이션이 선언된 테스트 기능](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/aop/src/test/java/hello/aop/exam/ExamTest.java)
- [RetryAspect : aop 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/aop/src/main/java/hello/aop/exam/aop/RetryAspect.java)
- [TraceAspect  aop 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/aop/src/main/java/hello/aop/exam/aop/TraceAspect.java)

### [proxy](https://github.com/youjaewoong/spring-core-principle-advanced/tree/master/proxy)
디자인패턴, 리플렉션, 동적프록시, CGLIB, 빈후처리 등의 기술 TEST가 포함된 프로젝트입니다.
- [ReflectionTest : 리플렉션 test 기능](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/proxy/src/test/java/hello/proxy/jdkdynamic/ReflectionTest.java)
