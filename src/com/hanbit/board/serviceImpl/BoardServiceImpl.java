package com.hanbit.board.serviceImpl;

import com.hanbit.board.domain.BoardBean;
import com.hanbit.board.service.BoardService;

public class BoardServiceImpl implements BoardService {
	int count, seq;
	BoardBean board;
	BoardBean[] list;
	public BoardServiceImpl() {
		count=0;
		seq=0;
		board = new BoardBean();
		list = new BoardBean[count];
	}
	
	@Override
	public void writeBoard(BoardBean board) {
		seq++;
		board.setSeq(seq);
		if (list.length == count) {
			BoardBean[] temp = new BoardBean[count+1];
			System.arraycopy(list, 0, temp, 0, count);
			list = temp;
		}
		//list[count++] = board; 아래두줄은 이것과 같음
		list[count] = board;
		count++;
	}

	@Override
	public int countBoard() {
		return count;
	}

	@Override
	public BoardBean[] listBoard() {
		return list;
	}

	@Override
	public BoardBean findBySeq(int seq) {
		board = new BoardBean();
		for (int i=0;i<count;i++) {
			if (seq == list[i].getSeq()) {
				board = list[i];
			}
		}
		return board;
	}

	@Override
	public BoardBean[] findByWriter(String writer) {
		int sameName = 0;
		for (int i=0;i<count;i++) {
			if (writer.equals(list[i].getWriter())) {
				sameName++;
			}
		}
		BoardBean[] temp = new BoardBean[sameName];
		int j=0;
		for (int i=0;i<count;i++) {
			if(writer.equals(list[i].getWriter())) {
				temp[j] = list[i];
				j++;
			}
	//밑의 구문은 동명이인의 수가 j값과 같으면 다 찾아서 더 없다는뜻이므로 break;
			if (sameName == j) {
				break;
			}
		}
		return temp;
	}

	@Override
	public void updateContent(BoardBean bean) {
		board = findBySeq(bean.getSeq());
		board.setContent(bean.getContent());
		//this.board = findBySeq(bean.getSeq());
	}

	@Override
	public void deleteBoard(int seq) {
		for (int i=0;i<count;i++) {
			if (seq == board.getSeq()) {
				list[i] = list[count-1];
				list[count-1] = null;
				count--;
			}
		}
	}
}
