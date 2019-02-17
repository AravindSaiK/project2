package com.Quizzer.code.exceptions;

public class Prof_AddQuiz_Exception extends Exception {

	private String errorMessage;

	public Prof_AddQuiz_Exception(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
