package mkh;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.HashMap;
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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
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
	
	@Autowired
	private DataSource dataSource; 		// root-context.xml 에 설정된 dataSource를 자동으로 주입
	
	@Autowired
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
	
//	@Test
	public void testWrite() throws Exception {
		logger.info("testWrite() start!!!");
		FileInputStream fis = new FileInputStream("/Users/moonpeter/projects/portfolio/sts_springTest/uploadformail/image/images123123.jpeg");
		FileInputStream fis2 = new FileInputStream("/Users/moonpeter/projects/portfolio/sts_springTest/uploadformail/image/images123123.jpeg");

		MockMultipartFile file = new MockMultipartFile("shop_upload_thumnail", "images123123.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis);		
		MockMultipartFile file2 = new MockMultipartFile("shop_upload_img_content", "images123123.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis2);

		mockMvc.perform(multipart("/shop/write")
				.file(file)
				.file(file2)
				.param("shop_category", "사료")
				.param("shop_title", "반복 테스트를 테스트중입니다333")
				.param("shop_price", "7777")
				.param("shop_country_of_origin", "Seoul")
				.param("shop_brand", "NoBrand")
				.param("shop_text_content", "Test context for mockMVC")
				);
		logger.info("testWrite() end!!!");
	}
	
	@Test
	public void controllerTestForMainList() throws Exception {
		mockMvc.perform(get("/shop/list")).andDo(print());
	}
	
//	@Test
	public void main_list() {
		logger.info("main_list() start!!!");
		
		int page = 1;
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = sqlSession.selectOne("Shops.listCount");  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		int startRow = (page - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startRow);
		map.put("end", endRow);
		List<Shop> list = sqlSession.selectList("Shops.mainList", map);
		for(Shop shop : list) {
			logger.info("title : " + shop.getShop_title());
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
