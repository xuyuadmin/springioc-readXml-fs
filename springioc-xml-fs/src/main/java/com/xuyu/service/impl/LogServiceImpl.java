package com.xuyu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xuyu.dao.LogDao;
import com.xuyu.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Transactional(propagation = Propagation.NEVER)
	public void addLog() {
		logDao.add("addLog" + System.currentTimeMillis());
		// int i = 1 / 0;
	}

}
