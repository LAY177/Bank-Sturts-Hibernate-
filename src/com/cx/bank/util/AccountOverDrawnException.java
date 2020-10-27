package com.cx.bank.util;

/*
 * 取款余额不足抛出异常
 */
public class AccountOverDrawnException extends Exception {
   
	public AccountOverDrawnException(){
		super();
	}
	
	public AccountOverDrawnException(String msg){
		super(msg);
	}
	
	public String toString(){
		return "余额不足！";
		
	}
	
}
