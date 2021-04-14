package com.jhta.petMatching.ahnyh.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.ahnyh.service.MessageService;



/*
     작성 과정
   1) servlet-context.xml에
      <task:annotation-driven/> 작성하고 Namespace를 추가한다.
      servlet-context.xml 하단에 Namespaces 탭을 클릭하고 'task - http~' 를 선택해주면 된다.
   2) log4j.xml에서
      <root>
		<priority value="info" />
		<appender-ref ref="console" />
	  </root>
	    이와 같이 <priority value="warn" /> 을 <priority value="info" />로 바꾼다.
	    
   3) MessageService, MessageServiceImpl, MessageDAO에
      messageDeleteBySchedule() 작성
      message.xml에
      <delete id="messageDeleteBySchedule"> 작성
      
   4) MessageDelete.java에
      @Autowired
      private MessageService messageservice; 작성하고
   
      scheduled_messageDelete() 작성.
 */
@Service
public class MessageDelete {
	private static final Logger logger = LoggerFactory.getLogger(MessageDelete.class);
	
	@Autowired
    private MessageService messageservice;
	
	//cron 사용법
	//seconds(초: 0~59) minutes(분: 0~59) hours(시: 0~23) day(일: 1~31)
	//month(달: 1~12)   day of week(요일: 0~6)  year(optional)
	//               초 분  시 일 달  요일 
	@Scheduled(cron="0 51 * * * *")
	public void scheduled_messageDelete() throws Exception{
		// 보낸이와 받는이 모두가 메시지를 삭제한 경우
		int result1 = messageservice.messageDeleteBySchedule1();
		// 보낸이가 발송 취소하고 메시지를 삭제한 경우
		int result2 = messageservice.messageDeleteBySchedule2();
		
		logger.info((result1+result2)+"개의 메시지가 삭제되었습니다.");
	}
}
