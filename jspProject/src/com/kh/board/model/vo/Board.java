package com.kh.board.model.vo;

public class Board {

	private int boardNo;
	private int boardType;
	private String categoryNo; // 작성 기능시 카테고리 번호 | 조회 기능시 카테고리명
	private String boardTitle;
	private String boardContent;
	private String boardWriter; // 작성 기능시 회원 번호 | 조회 기능시 회원아이디
	private int count;
	private String createDate; // to_date 써보기 위해 string으로 해보자~
	private String status;
	
	private String titleImg; // 대표이미지 보관할 필드
	
	public Board() {}

	public Board(int boardNo, int boardType, String categoryNo, String boardTitle, String boardContent,
			String boardWriter, int count, String createDate, String status) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
	}

	public Board(int boardNo, String categoryNo, String boardTitle, String boardWriter, int count, String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
	}
	
	public Board(int boardNo, String categoryNo, String boardTitle, String boardContent, String boardWriter,
			String createDate) {
		super();
		this.boardNo = boardNo;
		this.categoryNo = categoryNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}

	public Board(int boardNo, String boardTitle, int count, String titleImg) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.count = count;
		this.titleImg = titleImg;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardType() {
		return boardType;
	}

	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}

	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", categoryNo=" + categoryNo + ", boardTitle="
				+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", count=" + count
				+ ", createDate=" + createDate + ", status=" + status + "]";
	}
	
}
