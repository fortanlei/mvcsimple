package com.test.controller;

public class UserServiceImpl {
	
	public void sayHello(User user) {
		System.out.println(user.getName() + ";" + user.getAge());
	}
}
