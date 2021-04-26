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
		FileInputStream fis_thumnail = new FileInputStream("/Users/moonpeter/Desktop/mediatest/image/naverlogo.jpg");
		FileInputStream fis = new FileInputStream("/Users/moonpeter/Desktop/mediatest/image/tomato.jpeg");
		FileInputStream fis2 = new FileInputStream("/Users/moonpeter/projects/portfolio/sts_springTest/uploadformail/image/images123123.jpeg");
		FileInputStream fis3 = new FileInputStream("/Users/moonpeter/Desktop/mediatest/image/tomato.jpeg");
		FileInputStream fis4 = new FileInputStream("/Users/moonpeter/projects/portfolio/sts_springTest/uploadformail/image/images123123.jpeg");
		FileInputStream fis5 = new FileInputStream("/Users/moonpeter/Desktop/mediatest/image/tomato.jpeg");

		MockMultipartFile file_thumnail = new MockMultipartFile("shop_upload_thumnail", "vegi.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis_thumnail);		
		MockMultipartFile file = new MockMultipartFile("shop_upload_img_content", "tomato.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis);
		MockMultipartFile file2 = new MockMultipartFile("shop_upload_img_content_2", "images123123.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis2);
		MockMultipartFile file3 = new MockMultipartFile("shop_upload_img_content_3", "tomato.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis3);
		MockMultipartFile file4 = new MockMultipartFile("shop_upload_img_content_4", "images123123.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis4);
		MockMultipartFile file5 = new MockMultipartFile("shop_upload_img_content_5", "tomato.jpeg", MediaType.MULTIPART_FORM_DATA_VALUE, fis5);

		mockMvc.perform(multipart("/shop/write")
				.file(file_thumnail)
				.file(file)
				.file(file2)
				.file(file3)
				.file(file4)
				.file(file5)
				.param("shop_category", "생활용품")
				.param("shop_title", "반복 테스트를 테스트중입니다777")
				.param("shop_price", "7777")
				.param("shop_country_of_origin", "Seoul")
				.param("shop_brand", "NoBrand")
				.param("shop_text_content", "Test context for mockMVC")
				).andDo(print());
		logger.info("testWrite() end!!!");
	}
	
//	@Test
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
	public void list_ajax() {
		logger.info("list_ajax() start!!!");
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
			logger.info("title : " + shop.getShop_thumnail_original());
		}
		
		logger.info("list_ajax() end!!!");
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
	

//	@Test
	public void getCategoryListCount() {
		logger.info("getCategoryListCount() start!!!");
		int categoryListCount = sqlSession.selectOne("Shops.categoryListCount", "외출용품");
		logger.info("categoryListCount ===== " + categoryListCount);
		logger.info("getCategoryListCount() end!!!");
	}
	
//	@Test
	public void categoryList() {
		logger.info("categoryList() start!!!");
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
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", String.valueOf(startRow));
		map.put("end", String.valueOf(endRow));
		map.put("category", "미용/목욕");
		List<Shop> list = sqlSession.selectList("Shops.categoryList", map);
		for(Shop shop : list) {
			logger.info("title : " + shop.getShop_title());
			logger.info("category : " + shop.getShop_category());
		}
		logger.info("categoryList() end!!!");
	}
	
//	@Test
	public void category_list_ajax() {
		logger.info("category_list_ajax() start!!!");
				
		int page = 2;
		int limit = 9; // 한 페이지에 보여줄 게시글의 수 
		int listCount = sqlSession.selectOne("Shops.categoryListCount", "장난감");  
		int maxPage = (listCount + limit -1) / limit;
		int startPage = ((page-1) / 9) * 9 + 1;
		int endPage = startPage + 9 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		logger.info("!!!!!!!!!! : " + endPage +" / " + listCount);
		
		int startRow = (page - 1) * limit +1;
		int endRow = startRow + limit -1;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("start", String.valueOf(startRow));
		map.put("end", String.valueOf(endRow));
		map.put("category", "장난감");
		List<Shop> list = sqlSession.selectList("Shops.categoryList", map);
		for(Shop shop : list) {
			logger.info("title : " + shop.getShop_title());
			logger.info("title : " + shop.getShop_category());
		}
		
		logger.info("category_list_ajax() end!!!");
	}
	
//	@Test
	public void searchList() {
		logger.info("searchList() start!!!");
		String searchWord = "건강";
		int listCount = sqlSession.selectOne("Shops.searchListCount", searchWord);
		List<Shop> list = sqlSession.selectList("Shops.searchList", searchWord);
		for (Shop shop : list) {
			logger.info("title : " + shop.getShop_title());
		}
		logger.info("listCount ==== " + listCount);
		logger.info("searchList() end!!!");
	}
	
//	@Test
	public void shopDelete() {
		logger.info("shopDelete() start!!!");
		int result = sqlSession.delete("Shops.shopDelete", 56);
		logger.info("result === " + result);
		logger.info("shopDelete() end!!!");
	}
	
//	@Test
	public void shopModifyForm() {
		logger.info("shopModifyForm() start!!!");
		Shop list = sqlSession.selectOne("Shops.shopModify", 50);
		logger.info(list.getShop_title());
		logger.info("shopModifyForm() end!!!");
	}
	
	@Test
	public void shopModify() {
		logger.info("shopModify() start!!!");
		int result = sqlSession.update("Shops.shopModify", 46);
		logger.info("shopModify() end!!!");
	}
	
}