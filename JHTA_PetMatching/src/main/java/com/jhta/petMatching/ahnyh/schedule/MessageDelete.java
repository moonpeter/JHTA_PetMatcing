package com.jhta.petMatching.ahnyh.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.ahnyh.service.MessageService;



/*
     �ۼ� ����
   1) servlet-context.xml��
      <task:annotation-driven/> �ۼ��ϰ� Namespace�� �߰��Ѵ�.
      servlet-context.xml �ϴܿ� Namespaces ���� Ŭ���ϰ� 'task - http~' �� �������ָ� �ȴ�.
   2) log4j.xml����
      <root>
		<priority value="info" />
		<appender-ref ref="console" />
	  </root>
	    �̿� ���� <priority value="warn" /> �� <priority value="info" />�� �ٲ۴�.
	    
   3) MessageService, MessageServiceImpl, MessageDAO��
      messageDeleteBySchedule() �ۼ�
      message.xml��
      <delete id="messageDeleteBySchedule"> �ۼ�
      
   4) MessageDelete.java��
      @Autowired
      private MessageService messageservice; �ۼ��ϰ�
   
      scheduled_messageDelete() �ۼ�.
 */
@Service
public class MessageDelete {
	private static final Logger logger = LoggerFactory.getLogger(MessageDelete.class);
	
	@Autowired
    private MessageService messageservice;
	
	//cron ����
	//seconds(��: 0~59) minutes(��: 0~59) hours(��: 0~23) day(��: 1~31)
	//month(��: 1~12)   day of week(����: 0~6)  year(optional)
	//               �� ��  �� �� ��  ���� 
	@Scheduled(cron="0 51 * * * *")
	public void scheduled_messageDelete() throws Exception{
		// �����̿� �޴��� ��ΰ� �޽����� ������ ���
		int result1 = messageservice.messageDeleteBySchedule1();
		// �����̰� �߼� ����ϰ� �޽����� ������ ���
		int result2 = messageservice.messageDeleteBySchedule2();
		
		logger.info((result1+result2)+"���� �޽����� �����Ǿ����ϴ�.");
	}
}
