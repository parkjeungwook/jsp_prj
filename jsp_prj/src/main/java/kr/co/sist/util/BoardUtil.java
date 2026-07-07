package kr.co.sist.util;

import java.io.File;
import java.util.UUID;

public class BoardUtil {

	private BoardUtil() {
	}

	/**
	 * pagination을 제작하는 일
	 * @param currentPage 현재 페이지 
	 * @param totalPage 총 페이지 수 
	 * @param url 이동한 url 
	 * @param fieldNum 검색 필드 명 
	 * @param keyWord 검색 조건 명 
	 * @return
	 */
	public static String pagination(int currentPage, int totalPage, String url, String fieldNum, String keyWord) {
		// 1. 한 화면에 보여줄 page nation의 수
		int pageNumber = 3;
		// 2. 화면에 보여줄 시작 번호 ([1][2][3]=>1, [4][5][6] => 4,)
		int startPage = ((currentPage - 1) / pageNumber) * pageNumber + 1;
		// 3. 화면에 보여줄 마지막 번호
		int endPage = startPage + pageNumber - 1;
		// 4. 총 페이지 수가 연산된 마지막 페이지 수 보다 작다면
		if (totalPage <= endPage) {
			endPage = totalPage;
		} // end if
		StringBuilder pagenation = new StringBuilder();
		pagenation.append("<ul class='pagination' style='width:250px; margin: 0px auto;'>");
		// 5. 이전 페이지로 가기위한 기호
		int movePage = 0;
		StringBuilder prevMark = new StringBuilder();
		prevMark.append("<li class='page-item disabled'><span class='page-link'>Previous</span></li>");

		if (currentPage > pageNumber) { // 4 > 3
			movePage = startPage - 1;
			prevMark.delete(0, prevMark.length());
			prevMark.append("<li class='page-item'><a class='page-link' href='").append(url)
					.append("?currentPage=").append(movePage);
			if (keyWord != null && !keyWord.trim().isEmpty()) {
				prevMark.append("&fieldNum=").append(fieldNum).append("&keyword=").append(keyWord);
			} // end if
			prevMark.append("'>Previous</a></li>");
		} // end if
		pagenation.append(prevMark.toString());

		// 6. 시작페이지 번호부터 끝 페이지 번호까지 화면에 출력 [1][2][3]
		movePage = startPage;
		StringBuilder pageLink = new StringBuilder();

		while (movePage <= endPage) {
			if (movePage == currentPage) { // 현재페이지 였을 때에는 link를 설정하지 않는다.
				pageLink.append("<li class='page-item active'><span class='page-link'>").append(movePage)
						.append("</span></li>");
			} else {
				pageLink.append("<li class='page-item'><a class='page-link' href='").append(url)
						.append("?currentPage=").append(movePage);
				if (keyWord != null && !keyWord.trim().isEmpty()) {
					pageLink.append("&fieldNum=").append(fieldNum).append("&keyword=").append(keyWord);
				} // end if
				pageLink.append("'>").append(movePage).append("</a></li>");
			} // end else
			movePage++;
		} // end while

		if (pageLink.toString().isEmpty()) {
			pageLink.append("<li class='page-item'><span class='page-link'>1</span></li>");
		} // end if
		pagenation.append(pageLink.toString());

		// 7. 뒤에 페이지 더 있는 경우
		StringBuilder nextMark = new StringBuilder(
				"<li class='page-item disabled'><span class='page-link'>Next</span></li>");
		if (totalPage > endPage) {
			movePage = endPage + 1;
			nextMark.delete(0, nextMark.length());
			nextMark.append("<li class='page-item'><a class='page-link' href='").append(url)
					.append("?currentPage=").append(movePage);

			if (keyWord != null && !keyWord.trim().isEmpty()) {
				nextMark.append("&fieldNum=").append(fieldNum).append("&keyword=").append(keyWord);
			} // end if
			nextMark.append("'>next</a></li>");
		} // end if
		pagenation.append(nextMark.toString()).append("</ul>");

		return pagenation.toString();	
		}// pagination
	
	public static String uuidFile(File file) {
		// 파일명을 UUID를 사용하여 변경된 이름으로 반환 
		String fileName = file.getName();
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String uuidName = UUID.randomUUID().toString().replaceAll("-","")+ext;
		
		
		return uuidName;
	}//uuideFile
	
		

}// class
