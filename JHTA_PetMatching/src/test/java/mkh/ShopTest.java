package mkh;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jhta.petMatching.mkh.domain.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
//WAS 없이 MVC 패턴의 Controller를 단위 테스트하기위해서는 @WebAppConfiguration을 사용해야만 합니다.
//@WebAppConfiguration 은 스프링 MVC 테스트 할 때 사용하는 것으로서 이를 통해 WebApplicationContext를 @Autowired 하면 서블릿 설정들을 가져올수 있게 됩니다.
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class ShopTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopTest.class);
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc; 			// MockMvc 는 웹 애플리케이션을 애플리케이션 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스
	private DataSource dataSource; 		// root-context.xml 에 설정된 dataSource를 자동으로 주입
	private SqlSessionTemplate sqlSession;


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
	
	//@Test // 현재 메서드를 테스트 대상으로 지정하는 어노테이션
	public void testConnection() {
		try (Connection conn = dataSource.getConnection()) {
			logger.info("확인용 conn : " + conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
//	@Test
	public void testWriteForm() throws Exception {
		logger.info("testWriteForm() start!!!");
		this.mockMvc.perform(get("/shop/writeForm")).andDo(print())
		.andExpect(status().isOk());
		logger.info("testWriteForm() end!!!");
	}
	
	@Test
	public void testWrite() throws Exception {
		logger.info("testWrite() start!!!");
		
		FileInputStream fis = new FileInputStream("/Users/moonpeter/projects/portfolio/sts_springTest/uploadformail/image/images123123.jpeg");
		MockMultipartFile uploadFile = new MockMultipartFile("uploadFile", fis);
		uploadFile.getOriginalFilename();
		mockMvc.perform(MockMvcRequestBuilders.multipart("/shop/write")
				.file(uploadFile)
				.param("shop_category", "사료")
				.param("shop_title", "Test title for mockMVC")
				.param("shop_price", "7777")
				.param("shop_country_of_origin", "Seoul")
				.param("shop_brand", "NoBrand")
				.param("shop_text_context", "Test context for mockMVC")
//				.param("shop_upload_thumnail", uploadFile.)
//				.param("shop_upload_img_content", uploadFile)
				).andDo(print());
		logger.info("testWrite() end!!!");
	}
	
//	@Test
	public void insert() {
		logger.info("insert() start!!!");
		
		MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "hello file".getBytes());
		
		Shop shop = new Shop();
		shop.setShop_category("사료");
		shop.setShop_title("Test 제목for시퀀");
		shop.setShop_price("1000");
		shop.setShop_country_of_origin("서울");
		shop.setShop_brand("TestBrand");
		shop.setShop_thumnail("testThumnail");
		shop.setShop_grade("0");
		shop.setShop_img_content("TEST image");
		shop.setShop_text_content("TEST Content!!!");
		int result = sqlSession.insert("ShopBoards.insert", shop);
		logger.info("insert() end!!! result = " + result);
	}
	
//	@Test
	public void main_list() {
		logger.info("main_list() start!!!");
		List<Object> list = sqlSession.selectList("Shops.mainList");
		for(int i=0; i<list.size(); i++) {
			logger.info(list.get(i).toString());
		}
		logger.info("main_list() end!!!");
	}
	
//	@Test
	public void detail() {
		logger.info("detail() start!!!");
		Shop detail = sqlSession.selectOne("Shops.detail", 1);
		logger.info(detail.toString());
		logger.info("detail() end!!!");
	}
	
//	@Test
	public void getListCount() {
		logger.info("getListCount() start!!!");
		int listCount = sqlSession.selectOne("Shops.listCount");
		logger.info("listCount ===== " + listCount);
		logger.info("getListCount() end!!!");
	}
}
