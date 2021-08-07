package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;

	public int insertImg(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.insertImg()]");

		return sqlSession.insert("insertImg", galleryVo);
	}

	public List<GalleryVo> getGalleryList() {
		System.out.println("[GalleryDao.getGalleryList()]");
		return sqlSession.selectList("getGalleryList");
	}

	// 이미지 한개 가져오기
	public GalleryVo selectImg(int no) {
		System.out.println("[GalleryDao.selectImg()]");

		return sqlSession.selectOne("gallery.selectImg", no);
	}

	// 이미지 삭제
	public int delImg(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.delImg()]");

		int count = sqlSession.delete("delImg", galleryVo);
		System.out.println(count);
		
		return count;
	}

}
