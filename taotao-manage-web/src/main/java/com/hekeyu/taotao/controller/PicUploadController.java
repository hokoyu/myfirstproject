package com.hekeyu.taotao.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.ServerInfo;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hekeyu.taotao.vo.PicUploadResult;
@RequestMapping("/pic")
@Controller
public class PicUploadController {
	
	//只支持以下格式的图片
	private static final String[] IMAGE_TYPES = {".jpg",".png",".bmp",".jpeg",".gif"};	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@RequestMapping(value="/upload", method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	@ResponseBody
	public String picUpload(@RequestParam("uploadFile") MultipartFile multipartFile) throws Exception {
		PicUploadResult picUploadResult = new PicUploadResult();
		picUploadResult.setError(1);//只要非零都是失败
		boolean isLegal =false;
		//判断文件格式即扩展名
		for (String str : IMAGE_TYPES) {
			if(multipartFile.getOriginalFilename().lastIndexOf(str)>0) {
				isLegal=true;
				break;
			}
		}
		
		if(isLegal) {
			try {
				//判断文件内容是否为图片,如果不是图片会报异常
				BufferedImage image = ImageIO.read(multipartFile.getInputStream());
				//上传图片到tracker 
				//获取tracker server的地址的配置文件路径
				String trackerConfig = this.getClass().getClassLoader().getResource("tracker.conf").getPath();
				//设值tracker server 的地址
				ClientGlobal.init(trackerConfig);
				//创建trackerClient
				TrackerClient trackerClient = new TrackerClient();
				//创建trackerServer
				TrackerServer trackerServer = trackerClient.getConnection();
				//创建storageServer
				StorageServer storageServer = null;
				//创建storageClient
				StorageClient storageClient = new StorageClient(trackerServer, storageServer);
				//上次文件
				//上传文件的后缀名
				String file_ext_name = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
				//文件的本地图片路径，后缀，文件信息
				String[] fileInfos = storageClient.upload_file(multipartFile.getBytes(), file_ext_name, null);
				String url = "";
				if(fileInfos!=null&&fileInfos.length>1) {
					String groupName = fileInfos[0];
					String filename = fileInfos[1];
					//获取存储服务器信息
					ServerInfo[] serverInfos = trackerClient.getFetchStorages(trackerServer, groupName, filename);
					url = "http://" + serverInfos[0].getIpAddr() + "/" + groupName + "/" + filename;
					
					//设置返回结果
					picUploadResult.setError(0);
					picUploadResult.setUrl(url);

				}
				picUploadResult.setHeight(image.getHeight()+"");
				picUploadResult.setWidth(image.getWidth()+"");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return MAPPER.writeValueAsString(picUploadResult);
	}
}
