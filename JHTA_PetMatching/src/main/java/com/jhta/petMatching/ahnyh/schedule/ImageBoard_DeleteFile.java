package com.jhta.petMatching.ahnyh.schedule;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.jhta.petMatching.ahnyh.service.ImageBoardService;


@Service
public class ImageBoard_DeleteFile {
	private static final Logger logger = LoggerFactory.getLogger(ImageBoard_DeleteFile.class);
	
	@Autowired
	private ImageBoardService imageboardservice;
	
	//cron 사용법
	//seconds(초: 0~59) minutes(분: 0~59) hours(시: 0~23) day(일: 1~31)
	//month(달: 1~12)   day of week(요일: 0~6)  year(optional)
	//               초 분  시 일 달  요일 
	//@Scheduled(cron="0 15 * * * *")   //프로젝트를 합치면 파일 경로가 사람마다 다르므로 일단 작동되지 않게 주석처리함.
	public void scheduled_imageboard_DeleteFile() throws Exception{
		logger.info("이미지 게시판 파일 삭제");
		List<String> deleteFileList = imageboardservice.getDeleteFileList();
		
		//업로드된 이미지 파일의 실제 경로 ------ 아래의 경로를 변경해야함 --> 이유: 프로젝트에 합치면 각자 파일 경로가 다르므로 바꿀 필요가 있다.
		String saveFolder = "C:/Users/ciell/Documents/workspace-sts-3.9.8.RELEASE/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JHTA_PetMatching/resources/imageboard_upload";  
		
		
		//for(String filename : deleteFileList) {
		for(int i=0; i<deleteFileList.size(); i++) {
			String filename = deleteFileList.get(i);
			File file = new File(saveFolder + filename);
			if(file.exists()) {
				if(file.delete()) {
					logger.info(file.getPath()+" 삭제되었습니다.");
				}	
			}else {
				logger.info(file.getPath()+" 파일이 존재하지 않습니다.");
			}
		}
	}
}
