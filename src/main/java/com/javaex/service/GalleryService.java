package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Repository
public class GalleryService {

	@Autowired
	private GalleryDao galleryDao;

	// 파일 db 업로드
	public int restore(MultipartFile file, String content, int userNo) {
		System.out.println("[GalleryService.restore()]");

		String saveDir = "C:\\javaStudy\\upload\\";

		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		// 원파일이름
		String orgName = file.getOriginalFilename();
		System.out.println(orgName);

		// 확장자
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println(exName);

		// 저장파일이름(관리때문에 겹치지 않는 새 이름 부여)
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		System.out.println(saveName);

		// 파일패스
		String filePath = saveDir + "\\" + saveName;
		System.out.println(filePath);

		// 파일사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize " + fileSize);

		// 1. 파일 서버하드디스크에 저장
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

		// 2. 파일정보를 db에 저장
		GalleryVo galleryVo = new GalleryVo(userNo, content, filePath, orgName, saveName, fileSize);

		return galleryDao.insertImg(galleryVo);
	}

	//이미지 목록 가져오기
	public List<GalleryVo> getGalleryList() {
		System.out.println("[GalleryService.getGalleryList()]");

		List<GalleryVo> GalleryList = galleryDao.getGalleryList();

		return GalleryList;
	}

	//이미지 한개 가져오기
	public GalleryVo getImage(int no) {
		System.out.println("[GalleryService.getImage()]");

		return galleryDao.selectImg(no);
	}

	//이미지 삭제
	public int delImg(GalleryVo galleryVo) {
		System.out.println("[GalleryService.delImg()]");

		int count = galleryDao.delImg(galleryVo);
		System.out.println(count);
		
		return count;
	}
}
