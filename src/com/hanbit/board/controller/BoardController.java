package com.hanbit.board.controller;

import javax.swing.JOptionPane;

import com.hanbit.board.domain.BoardBean;
import com.hanbit.board.service.BoardService;
import com.hanbit.board.serviceImpl.BoardServiceImpl;

public class BoardController {
	public static void main(String[] args) {
		
		BoardService service = new BoardServiceImpl();
		BoardBean board = null;
		while(true) {
			switch(JOptionPane.showInputDialog("0. 종료 1. 시작 2. 글 갯수 3. 작성글 리스트 4. 글번호로 글찾기 \n 5. 작성자로 글찾기  6. 글내용변경 7. 삭제하기")) {
			case "0":
				JOptionPane.showMessageDialog(null, "종료");
				return;
				
			case "1":
				board = new BoardBean();
				String[] arr = JOptionPane.showInputDialog("작성자/작성일/제목/내용").split("/");
				board.setWriter(arr[0]);
				board.setRegdate(arr[1]);
				board.setTitle(arr[2]);
				board.setContent(arr[3]);
				service.writeBoard(board);
				JOptionPane.showMessageDialog(null, "작성 완료");
				break;
			case "2":
				JOptionPane.showMessageDialog(null, service.countBoard() + "개의 작성글이 있습니다");
				break;
			case "3":
				BoardBean[] bList = service.listBoard();
				
				String list = "";
				for (int i=0;i<service.countBoard();i++) {
					list += bList[i].toString() + "\n";
				}
				JOptionPane.showMessageDialog(null, "리스트 : " + list.toString());
				break;
			case "4":
				JOptionPane.showMessageDialog(null, service.findBySeq(Integer.parseInt(JOptionPane.showInputDialog("글번호를 입력하세요"))));
				break;
			case "5":
				BoardBean[] wList = service.findByWriter(JOptionPane.showInputDialog("작성자를 입력하세요"));
				String writer = "";
				if (wList.length == 0) {
					writer = "조회가능한 작성자가 없습니다";
				}
				else {
					for (int i=0;i<wList.length;i++) {
						writer += wList[i] + "\n";
					}
				}
				JOptionPane.showMessageDialog(null, writer);
				break;
			case "6":
				board = new BoardBean();
				board.setSeq(Integer.parseInt(JOptionPane.showInputDialog("변경할 글 번호")));
				board.setContent(JOptionPane.showInputDialog("새 글 입력"));
				service.updateContent(board);
				JOptionPane.showMessageDialog(null, "수정 완료");
				break;
			case "7":
				int deleteSeq = Integer.parseInt(JOptionPane.showInputDialog("삭제할 글번호 입력"));
				service.deleteBoard(deleteSeq);
				JOptionPane.showMessageDialog(null, "삭제됐수");
				break;
			}
		}
	}
}