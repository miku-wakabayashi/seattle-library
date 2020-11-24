package jp.co.seattle.library.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jp.co.seattle.library.service.BooksService;

/**
 * Handles requests for the application home page.
 */
@Controller  //APIの入り口
public class EditBookController {
	final static Logger logger = LoggerFactory.getLogger(EditBookController.class);

	@Autowired
	private BooksService booksService;

	@Transactional
	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public String transitionEdit(Locale locale,
			@RequestParam("bookId") Integer bookId,
			Model model) {
		logger.info("Welcome edit.java! The client locale is {}.", locale);

		model.addAttribute("bookInfo", booksService.getBookInfo(bookId));

		return "edit";
	}

	@Transactional
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST,produces="text/plain;charset=utf-8")
	public String editBook(Locale locale,
			@RequestParam("bookId") Integer bookId,
			@RequestParam("title") String title,
			@RequestParam("author") String author,
			@RequestParam("publisher") String publisher,
			@RequestParam("description") String description,
			@RequestParam("thumbnail") MultipartFile file,
			Model model) {
		logger.info("Welcome updateBooks.java! The client locale is {}.", locale);

		String thumbnail=null;
		if(!file.isEmpty()) {
			thumbnail = file.getOriginalFilename();
			String path = new File(".").getAbsoluteFile().getParent();
			logger.info(path);
			try {
	            // アップロードファイルを置く
	            File uploadFile =
	                    new File("/Users/wakabayashimiku/Documents/project/SeattleLibrary_1/src/main/webapp/resources/thumbnails/"+thumbnail);
	            		byte[] bytes = file.getBytes();
	            		BufferedOutputStream uploadFileStream =
	                    new BufferedOutputStream(new FileOutputStream(uploadFile));
	            		uploadFileStream.write(bytes);
	            		uploadFileStream.close();

	        } catch (Exception e) {
	            // 異常終了時の処理
	     }
		}

		booksService.updateBook(bookId,title,author,publisher,description,thumbnail);

		model.addAttribute("resultMess,age", "登録完了");
		model.addAttribute("bookInfo", booksService.getBookInfo(bookId));

		return "details";
	}


}
