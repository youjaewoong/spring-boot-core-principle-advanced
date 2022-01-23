package hello.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import hello.proxy.pureproxy.proxy.code.Subject;

public class ProxyPatternTest {
	
	@Test
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		client.execute();
		client.execute();
		client.execute();
	}
	
	@Test
	void cacheProxyTest() {
		RealSubject realSubject = new RealSubject();
		Subject cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
		client.execute();
		client.execute();
		client.execute();
	}
}
