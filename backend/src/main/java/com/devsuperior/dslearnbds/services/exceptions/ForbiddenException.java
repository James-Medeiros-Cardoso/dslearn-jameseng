package com.devsuperior.dslearnbds.services.exceptions;

//retornar erro 403: o perdil deste usuario nao acessa este recurso
public class ForbiddenException extends RuntimeException {
	static final long serialVersionUID = 1L;

	public ForbiddenException(String msg) {
		super(msg);
	}
}
