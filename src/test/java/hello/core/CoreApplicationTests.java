package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	// 예외로 필드 주입을 사용하는 경우
	@Autowired OrderService orderService;

	@Test
	void contextLoads() {
	}

}
