package kr.co.sist.board;

import java.sql.SQLException;
import java.util.List;

public class BoardService {
	
	/**
	 * 게시물의 총 레코드 
	 * @return
	 */
	public int totalCnt() {
		int cnt = 0;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			cnt=bDAO.selectTotalCnt();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return cnt;
	}//searchLogin
	
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
	
	public BoardDTO searchBoardDetail(int num){
		BoardDTO bDTO = null;
		BoardDAO bDAO = BoardDAO.getInstance();
		try {
			bDTO=bDAO.selectBoardDetail(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return bDTO;
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
	
}//class
