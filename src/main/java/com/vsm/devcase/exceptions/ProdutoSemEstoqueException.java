package com.vsm.devcase.exceptions;

public class ProdutoSemEstoqueException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public ProdutoSemEstoqueException(String msg) {
		
		super(msg);
		
	}

}
