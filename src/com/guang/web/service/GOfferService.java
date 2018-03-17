package com.guang.web.service;

import org.springframework.stereotype.Service;

import com.guang.base.dao.QueryResult;
import com.guang.web.model.GOffer;

@Service
public interface GOfferService {
	void add(GOffer offer);
	void delete(long id);
	void update(GOffer offer);
	GOffer find(long id);
	QueryResult<GOffer> findAlls(int firstindex);
	QueryResult<GOffer> findAlls();
	QueryResult<GOffer> findAllsByOnlie();
}
