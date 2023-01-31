# spring-core-principle-advanced
스프링 핵심 원리 - 고급편

### [advanced](https://github.com/youjaewoong/spring-core-principle-advanced/tree/master/advanced)
로그추적기, TemplateCallBackPatten 적용 등 V1 ~ V5 까지의 레벨로 점차적으로 개선해나가는 프로젝트 입니다.
- [TraceTemplate : TemplateCallBackPattern 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/trace/callback/TraceTemplate.java)
- [ThreadLocalLogTrace : 공통 log 구현체](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/trace/callback/TraceCallback.java)
- [LogTraceConfig : 공통 log 를 bean으로 주입시킨 후 싱글톤으로 구성](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/LogTraceConfig.java)
- [OrderControllerV5 : TraceTemplate 적용 및 API 호출](https://github.com/youjaewoong/spring-core-principle-advanced/blob/master/advanced/src/main/java/hello/advanced/app/v5/OrderControllerV5.java)
