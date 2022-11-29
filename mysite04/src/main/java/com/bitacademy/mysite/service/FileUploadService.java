package com.bitacademy.mysite.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.exception.FileUploadServiceException;

@Service
@PropertySource("classpath:com/bitacademy/mysite/config/web/fileupload.properties")
public class FileUploadService {
	
	// private static String RESTORE_PATH = "/mysite-uploads"; // 파일을 저장할경로
	// private static String URL_BASE = "/gallery/images"; // 
	
	@Autowired
	private Environment env;
	
	public String restore(MultipartFile multipartFile) throws FileUploadServiceException {
		String url = null;
		
		try {
			if(multipartFile.isEmpty()) {
				return url;
			}
			
			// 저장해야할 곳에 폴더가 있나없나 확인 없으면 설정했던 저장경로를 만들어줌
		    File restoreDirectory = new File(env.getProperty("fileupload.uploadLocation"));
		    if(!restoreDirectory.exists()) {
		    	restoreDirectory.mkdirs();
		    }
			
			String originalFilename = multipartFile.getOriginalFilename(); // 파일 이름
			// originalFilename.lastIndexOf('.') + 1 // 파일이름중 파일 확장명인 N번째 수를 찾는코드
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.') + 1); // N번째 수부터 읽음. 즉 확장명
			String restoreFilename = generateSaveFilename(extName);
			
			Long fileSize = multipartFile.getSize();
			
			System.out.println("############" + originalFilename);
			System.out.println("############" + restoreFilename);
			System.out.println("############" + fileSize);
			
			// 업로드한 파일을 저장할 경로설정 (반드시 이클립스 외부에다가 저장해주기!))
			byte[] data = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(env.getProperty("fileupload.uploadLocation") + "/" + restoreFilename);
			os.write(data);
			os.close();
			
			// 외부에 있는 저장한 경로와 가상 url을 맵핑해준다 (리소스 맵핑)
			url = env.getProperty("fileupload.resourceMapping") + "/" + restoreFilename;
			
		} catch (IOException e) {
			throw new FileUploadServiceException(e.toString());
		}
		
		return url;
	}
	
	// 파일명을 밀리세컨드 초까지 구분시킴
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
