package com.jayqqaa12.abase.exception;

public class AbaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public AbaseException() {
		super();
	}
	
	public AbaseException(String msg) {
		super(msg);
	}
	
	public AbaseException(Throwable ex) {
		super(ex);
	}
	
	public AbaseException(String msg,Throwable ex) {
		super(msg,ex);
	}

}
