package hello.proxy.advisor;

import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvisorTest {

	@Test
	void advisorTest1() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

		proxy.save();
		proxy.find();
	}
}