package com.example.Line_position.imageUpload;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Line_position.VideoModel;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ImageUploadService {

	@Autowired

	ImageUploadRepository imageuploadRepository;

	@PostMapping("/insertImage")

	public String insertImage(@RequestParam("file") MultipartFile file, @RequestParam("line_id") String line_id)
			throws IOException {
		String message = "{\"message\":\"Unsuccessful\"}";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String CurrentDate = sdf.format(date);

		int insertData = (imageuploadRepository).insertData(file.getBytes(), line_id, CurrentDate,
				file.getOriginalFilename(), file.getContentType());
		if (insertData > 0) {
			message = "{\"message\":\"Successful\"}";
		}

		return message;

	}

	@RequestMapping("/getImageId")
	public Map<String, ArrayList<Integer>> download(@RequestParam(value = "line_id") String line_id) {
		ArrayList<Integer> list = (ArrayList<Integer>) imageuploadRepository.getImageUpload1(line_id);

		HashMap<String, ArrayList<Integer>> hmap = new HashMap<String, ArrayList<Integer>>();
		hmap.put("imageId", list);

		return hmap;
	}

	@RequestMapping("/getImage")
	public Map<String, ArrayList<ImageUpload>> download1(@RequestParam(value = "id") int id) {
		ArrayList<ImageUpload> list = (ArrayList<ImageUpload>) imageuploadRepository.getImage(id);
		HashMap<String, ArrayList<ImageUpload>> hmap = new HashMap<String, ArrayList<ImageUpload>>();
		hmap.put("imageData", list);

		return hmap;
	}

	@RequestMapping("/getImageData")
	public ArrayList<VideoModel> getImageData(@RequestParam(value = "line_id") String line_id) {
		List<ImageUpload> list = imageuploadRepository.getImage(line_id);
		ArrayList<VideoModel> imageData = new ArrayList<VideoModel>();
		for (ImageUpload imageUpload : list) {
			String splits[] = imageUpload.getFile_type().split("/");
			if (splits[0].equalsIgnoreCase("image")) {
				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/downloadFile/")
						.path(String.valueOf(imageUpload.getId())).toUriString();
				VideoModel videoModel = new VideoModel(fileDownloadUri, imageUpload.getLine_id());
				imageData.add(videoModel);
			}
		}
		return imageData;
	}

	@GetMapping("/downloadFile/{id}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id) {
		// Load file from database
		ImageUpload image = imageuploadRepository.getData(id);
		byte[] data = image.getImage();
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFile_type()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFile_name() + "\"")
				.body(new ByteArrayResource(data));
	}
	
	 @RequestMapping("/getVideoData")
	 public Map<String,ArrayList<VideoModel>> getVideoData1(@RequestParam(value="line_id") String line_id) {
	  List<ImageUpload> list= imageuploadRepository.getImage(line_id);
	  ArrayList<VideoModel> imageData=new ArrayList<VideoModel>(); 
	  for(ImageUpload imageUpload:list) { 
		  String splits[]=imageUpload.getFile_type().split("/");
	  if(splits[0].equalsIgnoreCase("video")) { 
		  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	  .path("/api/downloadFile/") .path(String.valueOf(imageUpload.getId()))
	  .toUriString(); 
		  VideoModel videoModel=new VideoModel(fileDownloadUri,
	  imageUpload.getLine_id()); imageData.add(videoModel); } } 
	  HashMap<String,ArrayList<VideoModel>> hmap=new HashMap<String, ArrayList<VideoModel>>();
	 hmap.put("video", imageData); 
	 return hmap;
	
	 }
	 

	@RequestMapping("/DeleteImage")
	public String deleteData(@RequestParam(value = "id") int id) {
		String message = "{\"message\":\"Unsuccessful\"}";

		int deleteData = (imageuploadRepository).deleteData(id);

		if (deleteData > 0) {
			message = "{\"message\":\"Successful\"}";
		}

		return message;
	}

	@RequestMapping("/getVideo")
	public Map<String, ArrayList<ImageUpload>> getVideoData(@RequestParam(value = "line_id") String line_id) {
		List<ImageUpload> list = imageuploadRepository.getVideo(line_id);
		ArrayList<ImageUpload> imageData = new ArrayList<ImageUpload>();
		for (ImageUpload imageUpload : list) {
			String splits[] = imageUpload.getFile_type().split("/");
			if (splits[0].equalsIgnoreCase("video")) {
				imageData.add(imageUpload);

			}
		}
		HashMap<String, ArrayList<ImageUpload>> hmap = new HashMap<String, ArrayList<ImageUpload>>();
		hmap.put("video", imageData);
		return hmap;

	}

}