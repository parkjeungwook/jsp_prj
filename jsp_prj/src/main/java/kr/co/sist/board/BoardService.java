package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
	/**
	 * 게시물의 총 레코드 
	 * @return
	 */
	public int totalCnt( RangeDTO rDTO ) {
		int cnt = 0;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			cnt=bDAO.selectTotalCnt(rDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return cnt;
	}//searchLogin
	
	/**
	 * 한 화면에 보여질 게시글의 수 
	 * @return
	 */
	public int pageScale() {
		return 10;
	}
	
	/**
	 *  총 페이지 수 
	 * @param totalCount 총 게시글 수 
	 * @param pageScale 한화면에 보여질 게시글의 수 
	 * @return
	 */
	public int totalPage(int totalCnt, int pageScale) {
		int totalPage = (int)Math.ceil((double)totalCnt/pageScale);
		return totalPage;
	}
	
	/**
	 * 현재 페이지를 문자열에서 입력받아서 정수로 변환 후 반환.
	 * @param tempPage
	 * @return
	 */
	public int currentPage(String tempPage) {
		int currentPage = 1;
		if(tempPage != null && !"".equals(tempPage)){ // pagenation을 클릭 했을 떄 1,2,3,4 해당 페이지 번호가 입력 
			try {
				currentPage=Integer.parseInt(tempPage);
			}catch(NumberFormatException nfe) {
				currentPage=1;
			}//end catch
		}//end if
		return currentPage;
	}//currentPage
	
	/**
	 * 조회된 글의 시작 번호
	 * @param currentPage
	 * @param pageScale
	 * @return
	 */
	public int startNum(int currentPage, int pageScale) {
		int startNum = 1;
		startNum=currentPage * pageScale-pageScale+1;
		return startNum;
	}
	
	/**
	 * 조회할 글의 끝 번호
	 * @param startNum
	 * @param pageScale
	 * @return
	 */
	public int endNum(int startNum, int pageScale) {
		int endNum = startNum + pageScale - 1;
		return endNum;
	}
	
	
	/**
	 * 시작 번호와 끝 번호, 검색키워드, 검색 필드를 받아서 해당 시작번호와 끝번호 사이의 글을 검색하는 일
	 * @param rDTO
	 * @return
	 */
	public List<BoardDTO> searchBoard(RangeDTO rDTO){
		List<BoardDTO> listBoard = null;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			listBoard=bDAO.selectBoard(rDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return listBoard;
	}//searchBoard
	
	
	public boolean addBoard(BoardDTO bDTO) {
		boolean flag = false;
		
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			bDAO.insertBoard(bDTO);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//addBoard
	
	public boolean modifyBoard(BoardDTO bDTO) {
		boolean flag = false;
		
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			flag = bDAO.updateBoard(bDTO) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//modifyBoard
	
	public boolean removeBoard(BoardDTO bDTO) {
		boolean flag = false;
		
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			flag = bDAO.deleteBoard(bDTO) == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//removeBoard
	
	public BoardDTO searchBoardDetail(int num){
		BoardDTO bDTO = null;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			// 글 번호에 해당하는 글을 읽고 
			bDTO=bDAO.selectBoardDetail(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return bDTO;
	}//searchBoardDetail
	
	public void modifyCount(int num){
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			// 글 번호에 해당하는 글을 읽고 
			bDAO.updateCnt(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//searchBoardDetail
	
}//class
