package com.xuyu.service.impl;

import org.springframework.stereotype.Service;

import com.xuyu.service.UserService;


//将该类注入到spring容器里面
@Service
public class UserServiceImpl implements UserService {
	public UserServiceImpl() {
		System.out.println("反射调用无参数构造函数");
	}

	public void add() {
		
	}


}

