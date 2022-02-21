package hello.aop.exam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import hello.aop.exam.aop.TraceAspect;

@Import(TraceAspect.class)
@SpringBootTest
public class ExamTest {

	@Autowired
	ExamService examService;

	@Test
	void test() {
		for (int i = 0; i < 5; i++) {
			examService.request("data" + i);
		}
	}
}