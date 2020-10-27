/*
 * 模型层为单例模式
 * 保证单例对象的类只有一个实例存在
 * 饱汉/懒汉模式（不会浪费内存）
 */
package com.cx.bank.model;

public class MoneyBean {
	//此模式属性私有，外部类要调用只能通过 类名.getXXX()方法。
    private static double money;
    //由于要求money是double类型，故只能在此定义一个instance用于做容器
	private static  MoneyBean instance;
    
//    static{
//    	instance=new MoneyBean(money);
//   }
    //构造方法私有
    private  MoneyBean(double money){
        this.money=money;
    }
    //外部类通过该属性拿到该类私有属性
    public static MoneyBean getInstance(){
    	if(instance==null)
    	instance=new MoneyBean(money);
    	return instance;
    	
    }
    
    public  double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
}
