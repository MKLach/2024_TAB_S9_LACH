package com.mklachl.sopkom.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.topdf.Pdf;

@RestController
@RequestMapping("/api/raporty")
public class RaportyController {

	
	@Autowired
	Pdf pdfService;
	
	
	@RequestMapping(value = "/rozklad", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> generateRozklad(@RequestParam(name="id") Long id) throws FileNotFoundException, IOException {
	  
    	File fileIn;
		try {
			fileIn = pdfService.genRozklad(id);
			System.out.println(fileIn.getAbsolutePath());
			   
			 return ResponseEntity.ok()
			            .contentLength(fileIn.length())
			            .contentType(MediaType.parseMediaType(MediaType.APPLICATION_PDF_VALUE))
			            .body(new FileSystemResource(fileIn.getAbsolutePath()));
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return  ResponseEntity.notFound().build();
	
    		
	    
	}
	
}
