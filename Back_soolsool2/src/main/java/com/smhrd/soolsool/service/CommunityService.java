package com.smhrd.soolsool.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.soolsool.converter.ImageConverter;
import com.smhrd.soolsool.converter.ImageToBase64;
import com.smhrd.soolsool.domain.Community;
import com.smhrd.soolsool.mapper.CommunityMapper;

@Service
public class CommunityService {

	@Autowired
	private CommunityMapper mapper;

	@Autowired
	private ResourceLoader resourceLoader;

	private static final String UPLOAD_DIRECTORY = "static/img"; // 리액트 웹에서 접근 가능한 경로

	public JSONArray CommunityList(int page) {
		  int itemsPerPage = 10; // 한 페이지당 보여줄 글의 수
		  List<Community> list = mapper.communityList((page - 1) * itemsPerPage, itemsPerPage);

		  JSONArray jsonArray = new JSONArray();
		  ImageConverter<File, String> converter = new ImageToBase64();
		  
		  //Community -> JsonObject
		  for(Community c: list) {
		    String fileStringValue = null;
		    if (c.getComm_file() != null) {
		      String filepath = "classpath:/static/img" + c.getComm_file();
		      Resource resource = resourceLoader.getResource(filepath);
		      try {
		        fileStringValue = converter.convert(resource.getFile());
		      } catch(IOException e) {
		        e.printStackTrace();
		      }
		      c.setComm_file(fileStringValue);
		    }
		    
		    JSONObject obj = new JSONObject(); //비어있는 json object 생성
		    obj.put("community", c); //비어있는 object에 값을 추가한 것 
		    jsonArray.add(obj); 
		  }
		  
		  return jsonArray;
		}

	public JSONObject CommunityOne(int idx) {
		Community community = mapper.communityOne(idx);
		JSONObject obj = new JSONObject();
		ImageConverter<File, String> converter = new ImageToBase64();
		String filepath = "classpath:/static/img" + community.getComm_file();
		Resource resource = resourceLoader.getResource(filepath);
		String fileStringValue = null;
		try {
			fileStringValue = converter.convert(resource.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}

		community.setComm_file(fileStringValue);
		obj.put("community", community);

		return obj;

	}

	public Community write(String comm_title, String comm_content, List<MultipartFile> file) {
		Community community = new Community();
		community.setComm_title(comm_title);
		community.setComm_content(comm_content);
		// 파일 처리 및 파일 경로 설정
		List<String> fileUrls = new ArrayList<>();
		for (MultipartFile f : file) {
			String fileUrl = saveFile(f); // 파일 저장 및 파일 경로 반환하는 메서드 호출
			if (fileUrl != null) {
				fileUrls.add(fileUrl);
			}
		}
		// 파일 경로를 문자열로 변환하여 Community 객체에 저장
		community.setComm_file(String.join(",", fileUrls));

		// 생성된 Community 객체를 DB에 삽입 (mapper를 활용하여 DB작업)
		mapper.write(community);

		return community;
	}

	// 이미지 파일 저장하기
	public String saveFile(MultipartFile file) {
		// 파일 저장
		// 예시: 원본 파일의 확장자를 유지하여 저장하는 방식
		String originalFileName = file.getOriginalFilename(); // 원본파일 이름
		String fileName = UUID.randomUUID().toString() + getExtension(originalFileName); // 임의의 파일 이름 + 확장자
		String directoryPath = "src/main/resources/" + UPLOAD_DIRECTORY; // 파일이 저장될 디렉토리 경로
		try {
			// 파일 저장 로직 구현
			byte[] bytes = file.getBytes();
			Path path = Paths.get(directoryPath, fileName);
			Files.write(path, bytes);

			System.out.println("파일 저장 성공");
			String filePath = UPLOAD_DIRECTORY + "/" + fileName;// 리액트 웹에서 접근 가능한 파일 경로

			System.out.println(filePath);
			return filePath; // 저장된 파일의 경로 반환

		} catch (IOException e) {
			// 파일 저장 실패시 예외 처리
			e.printStackTrace();
			System.out.println("파일 저장 실패");
			return null;
		}
	}

	// 확장자까지 저장하기
	private String getExtension(String filename) {
		int lastIndex = filename.lastIndexOf(".");
		if (lastIndex == -1) {
			return ""; // 확장자가 없는 경우

		}
		return filename.substring(lastIndex); // 확장자를 포함한 경우
	}

}
