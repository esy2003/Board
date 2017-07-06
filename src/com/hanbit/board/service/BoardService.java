package com.hanbit.board.service;

import com.hanbit.board.domain.BoardBean;

public interface BoardService {
	public void writeBoard(BoardBean board);
	public int countBoard();
	public BoardBean[] listBoard();
	public BoardBean findBySeq(int seq);
	public BoardBean[] findByWriter(String writer);
	public void updateContent(BoardBean board);
	public void deleteBoard(int seq);
}
