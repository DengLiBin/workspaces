package com.jayqqaa12.abase.exception;

public class DaoException extends AbaseException {
	private static final long serialVersionUID = 1L;
	
	public DaoException() {}
	
	
	public DaoException(String msg) {
		super(msg);
	}
	
	public DaoException(Throwable ex) {
		super(ex);
	}
	
	public DaoException(String msg,Throwable ex) {
		super(msg,ex);
	}
	
}
