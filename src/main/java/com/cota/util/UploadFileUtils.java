package com.cota.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * 파일 업로드 처리 클래스
 * @author gimseonghun
 *
 */
public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * 파일 업로드 하기
	 * @param uploadPath
	 * @param originalName
	 * @param fileData
	 * @return
	 * @throws Exception
	 */
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		
		//겹쳐지지 않는 파일명을 위한 유니크한 값 생성
		UUID uid = UUID.randomUUID();
		
		//원본파일 이름과 UUID 결합
		String savedName = uid.toString() + "_" + originalName;
		
		//파일을 저장할 폴더 생성(년 월 일 기준)
		String savedPath = calcPath(uploadPath);
		
		//저장할 파일준비
		File target = new File(uploadPath + savedPath, savedName);
		
		//파일을 저장
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		
		logger.info("formatName is "+formatName);
		
		String uploadedFileName = uploadPath + savedPath + File.separator + savedName;
		
		/*//파일의 확장자에 따라 썸네일(이미지일경우) 또는 아이콘을 생성함.
		String uploadedFileName = null;
		
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}*/
		
		return uploadedFileName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}//
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * 날짜에 맞춰 경로 설정
	 * @param uploadPath
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		logger.info(datePath);
		
		return datePath;
	}//calcPath
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * 폴더 생성 메소드
	 * @param uploadPath
	 * @param paths
	 */
	private static void makeDir(String uploadPath, String... paths) {
		
		if(new File(uploadPath + paths[paths.length -1]).exists()) {
			return;
		}//if
		
		for(String path : paths) {
			
			File dirPath = new File(uploadPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}//if
			
		}//for
		
	}//makeDir
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * 아이콘 생성
	 * @param uploadPath
	 * @param path
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception{
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ------------------------------------------------------------------------------ //
	
	/**
	 * 썸네일 생성하기
	 * @param uploadPath
	 * @param path
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	// ------------------------------------------------------------------------------ //
}
