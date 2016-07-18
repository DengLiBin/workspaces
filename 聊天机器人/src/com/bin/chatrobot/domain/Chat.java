package com.bin.chatrobot.domain;

public class Chat {
	private Integer id;
	private String answer;
	private String question;
	public Chat(Integer id,String question, String answer) {
		this.id=id;
		this.answer = answer;
		this.question = question;
	}
	public Integer getId(){
		return id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
