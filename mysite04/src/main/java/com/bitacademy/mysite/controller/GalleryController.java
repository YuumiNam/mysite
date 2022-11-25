package com.bitacademy.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.mysite.security.Auth;
import com.bitacademy.mysite.service.FileUploadService;
import com.bitacademy.mysite.service.GalleryService;
import com.bitacademy.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	private GalleryService galleryService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping
	public String index(Model model) {
		List<GalleryVo> list = galleryService.getImageList();
		model.addAttribute("list", list);
		
		// System.out.println(list);
		return "gallery/index";
	}
	
	@Auth(role="admin")
	@RequestMapping("/upload")
	public String upload(GalleryVo vo, @RequestParam("file") MultipartFile multipartFile, Model model) {
		String url = fileUploadService.restore(multipartFile);
		
		model.addAttribute("url", url);
		vo.setUrl(url);
		galleryService.saveImages(vo);
		
		// System.out.println(vo);
		// System.out.println(url);
		return "redirect:/gallery";
	}
	
	@Auth(role="admin")
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		galleryService.removeImages(no);
		
		return "redirect:/gallery";
	}
}
