package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileupService {

	//파일 업로드 처리
	public String restore(MultipartFile file) {
		System.out.println("[FileupService.restore()");
		
		String saveDir = "C:\\javaStudy\\upload\\";
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		
		//원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);
		
		//확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);
		
		//저장파일이름(관리때문에 겹치지 않는 새 이름 부여)
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		System.out.println(saveName);
		
		//파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);
		
		
		//파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize "+fileSize);
		
		//1. 파일 서버하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2. 파일정보를 db에 저장 --과제
		
		return saveName;
	}
}
