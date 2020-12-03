package com.example.demo.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FileDownController {

	@RequestMapping(value = "fileDownload")
	public void fileDownload4(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// String path = request.getSession().getServletContext().getRealPath("저장경로");
		
		//uuid 사용한 파일이름	
		String filename = request.getParameter("fileName");
		String realFilename = "";
		
		try {
			//유저의 시스템 정보 
			String browser = request.getHeader("User-Agent");
			// 사용자가 익스플로어 인지 크롬인지 파악
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				//인코딩을 utf-8로 바꾼다 다운받기위해 공백을 %20 으로 바꿈 왜바꾸는지는 아직도 의문
				filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
			} else {
				//브라우저별로 달라서 위에 경우가 아니면 밑에 꺼로 utf-8 변경
				filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			//인코딩이 지원되지 않는 경우
			System.out.println("UnsupportedEncodingException");
		}
		realFilename = "C:\\upload\\" + filename;
		System.out.println(realFilename);
		File file1 = new File(realFilename);
		//파일이 존재하는지 확인
		if (!file1.exists()) {
			//파일이 없으면 리턴
			return;
		}

		// 파일형태 설정  application/octer-stream 기본값 디폴트 미디어타입
		response.setContentType("application/octer-stream");
		// 전자 메일 메시지 데이터를 US-ASCII 일반 텍스트 형식으로 변환하는 인코딩 방법
		//Binary 란 Data를 ASCII 영역의 문자로만 이루어진 문자열로 바꾸는 인코딩 방법
		response.setHeader("Content-Transfer-Encoding", "binary;");
		//Content-Disposition 를 attachment; filename과 함께주면 Body 오는 값을 다운로드 받으라는 뜻이 된다
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFilename);

			int ncount = 0;
			byte[] bytes = new byte[512];

			while ((ncount = fis.read(bytes)) != -1) {
				os.write(bytes, 0, ncount);
			}
			
			fis.close();
			os.close();
		} catch (Exception e) {
			System.out.println("FileNotFoundException : " + e);
		}
	}

}
