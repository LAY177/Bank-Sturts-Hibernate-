package com.cx.bank.util;
/*
 * 存款金额为负数异常
 */
public class InvalidDepositException extends Exception{
   
	public InvalidDepositException(){
		super();
	}
	
	public InvalidDepositException(String msg){
		super(msg);
	}
	
	public String toString(){
		return "存款金额不能为负！";
	}
}
