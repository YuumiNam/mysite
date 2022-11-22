package com.bitacademy.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.mysite.repository.GalleryRepository;
import com.bitacademy.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	private GalleryRepository galleryRepository;
	
	public void saveImages(GalleryVo vo) {
		galleryRepository.insert(vo);
	}
	
	public void removeImages(Long no) {
		galleryRepository.deleteByNo(no);
	}
}
