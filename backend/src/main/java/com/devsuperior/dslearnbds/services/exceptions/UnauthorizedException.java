package com.devsuperior.dslearnbds.services.exceptions;

//erro 401
public class UnauthorizedException extends RuntimeException {
	static final long serialVersionUID = 1L;

	public UnauthorizedException(String msg) {
		super(msg);
	}
}
