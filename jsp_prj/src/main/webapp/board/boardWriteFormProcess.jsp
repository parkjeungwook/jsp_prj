<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="oracle.net.aso.b"%>
<%@page import="kr.co.sist.util.BoardUtil"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="kr.co.sist.util.HangulUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.MultipartParser"%>
<%@page import="kr.co.sist.board.BoardService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/site_Property.jsp" %>
<%-- <%@ include file="../include/loginCheck.jsp" %> --%>
<% 
	String sessionId = "test"; 
	String sessionName = "테스트";
	pageContext.setAttribute("userId", sessionId);
	pageContext.setAttribute("userName", sessionName);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
if("GET".equals(request.getMethod())){
	return;
}
request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="bDTO" class="kr.co.sist.board.BoardDTO" scope="page"/>
<jsp:setProperty name="bDTO" property="id" value="${ userId }"/>
<jsp:setProperty name="bDTO" property="ip" value="<%= request.getRemoteAddr() %>"/>

<%
	// request로는 web parameter를 받을 수 없다. 
	File saveDir 
		= new File("C:/Users/user/git/jsp_prj/jsp_prj/src/main/webapp/upload");

	int maxSize = 1024*1024*10; // 업로드 파일의 크기 10Mbyte
	int uploadMaxSize = 1024*1024*50; // 업로드 파일의 크기 50Mbyte
	
	
	MultipartRequest mr = new MultipartRequest(request, saveDir.getAbsolutePath(),
			uploadMaxSize,"UTF-8",new DefaultFileRenamePolicy());
	
	String fileName = mr.getFilesystemName("upFile");
	String contentType = mr.getContentType("upFile");
	
	if(fileName != null){
		File uploadFile = new File( saveDir.getAbsolutePath() + File.separator 
				+ fileName);
		
		if(uploadFile.length() > maxSize){ // 파일 크기가 크다면 파일 삭제 
			uploadFile.delete();
			out.println("파일의 최대 10MByte까지만 업로드 가능합니다.");
			return;
		} else {
			// 서버에서 실해되는 파일이 업로드 되지 않도록 청리 
			if( contentType == null || !contentType.contains("image") ){
				// 이상한 파일 : 해킹 => 서비스가 되지 않는 디렉토리로 이동 
				File hackDir = new File("c:/dev/hack");
				if(!hackDir.exists()){
					hackDir.mkdirs();
				}//end if
				
				File hackFile = new File(hackDir.getAbsolutePath() + File.separator + uploadFile.getName());
				uploadFile.renameTo(hackFile);
				out.println("이미지 파일이 아니므로 hack 폴더로 이동되었습니다.");
				
				FileInputStream fis = null;
				FileOutputStream fos = null;
				
				try{
					// 악의적인 목적의 파일의 스트림 연결 
					fis = new FileInputStream(uploadFile);
					// 악의적인 목적의 파일을 web service가 되지 않는 디렉토리로 이동 
					fos = new FileOutputStream(hackDir.getAbsolutePath() + File.separator + uploadFile.getName());
					
					byte[] readData = new byte[512];//한번에 읽어들일 데이터의 크기 
					
					int readSize=0;
					while((readSize = fis.read(readData)) != -1 ){ // 데이터를 EOF까지 읽어들인다. 
						fos.write(readData,0,readSize);
					}//end while 
					
					fos.flush();
					
					uploadFile.delete();
					
				}finally{
					if(fis != null){ fis.close(); }
					if(fos != null){ fos.close(); }
				}
				return;
			}//end if
			
			if(mr.getContentType("upFile") != null){
				String[] imgExt = {"jpg","jpeg","gif","png","bmp"};
				boolean fileFlag = false; 
				String compareExt = uploadFile.getName().substring(uploadFile.getName().lastIndexOf(".")+1);
				
				for(int i = 0; i < imgExt.length; i++){
					if(fileFlag = compareExt.toLowerCase().equals(imgExt[i])){
					bDTO.setUpfile(BoardUtil.uuidFile(uploadFile)); // 파일 명을 변경 
					//정상적인 파일 
					uploadFile.renameTo(new File(uploadFile.getParent()+File.separator+bDTO.getUpfile()));
					// 정상적인 파일 
						break;
					}//end if
				}//end for
			}//end if
		}//end else
	}//end if
	/*
	bDTO.setTitle(URLDecoder.decode(URLEncoder.encode(mr.getParameter("title"),"8859_1"),"UTF-8"));
	bDTO.setContent(new String(mr.getParameter("content").getBytes("8859_1"),"UTF-8"));
	*/
	bDTO.setTitle(mr.getParameter("title"));	
	bDTO.setContent(mr.getParameter("content"));	
	BoardService bs = new BoardService();
	pageContext.setAttribute("result", bs.addBoard(bDTO));
%>
<script type="text/javascript">
<c:choose>
	<c:when test="${ result }">
		alert("글을 작성하였습니다.");
		location.href="boardList.jsp";
	</c:when>
	<c:otherwise>
		alert("글 저장 중 문제가 발생했습니다. 잠시 후 다시 시도");
		history.back();
	</c:otherwise>
</c:choose>
	
</script>
