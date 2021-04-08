package mkh;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jhta.petMatching.mkh.domain.ShopBoard;

@RunWith(SpringJUnit4ClassRunner.class)
//WAS 없이 MVC 패턴의 Controller를 단위 테스트하기위해서는 @WebAppConfiguration을 사용해야만 합니다.
//@WebAppConfiguration 은 스프링 MVC 테스트 할 때 사용하는 것으로서 이를 통해 WebApplicationContext를 @Autowired 하면 서블릿 설정들을 가져올수 있게 됩니다.
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ShopTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	
	// MockMvc 는 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스
	private MockMvc mockMvc;

	// 매번 테스트를 진행할 때 마다 테스트 하기전 MockMvc mockMvc 객체를 만들어 주기위해
	// @Before 에노테이션으로 setup() 메소드를 생성
	@Before
	public void beforeTest() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		logger.info("======= Test Start!!! =======");
	}
	
	@After
	public void afterTest() {
		logger.info("======= Test End!!! =======");
	}
	
	@Autowired
	private DataSource dataSource; // root-context.xml 에 설정된 dataSource를 자동으로 주입

	//@Test // 현재 메서드를 테스트 대상으로 지정하는 어노테이션
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()) {
			logger.info("확인용 conn : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	private SqlSessionTemplate sqlsession;
	
	@Test
	public void insert() {
		logger.info("insert() start!!!");
		ShopBoard shopBoard = new ShopBoard();
		shopBoard.setShop_num(10);
		shopBoard.setShop_category("사료");
		shopBoard.setShop_title("Test Title");
		shopBoard.setShop_price("1000");
		shopBoard.setShop_country_of_origin("서울");
		shopBoard.setShop_brand("TestBrand");
		shopBoard.setShop_thumnail("testThumnail");
		shopBoard.setShop_text_content("TEST Content!!!");
		int result = sqlsession.insert("ShopBoards.insert", shopBoard);
		logger.info("insert() end!!! result = " + result);
	}
	
}
