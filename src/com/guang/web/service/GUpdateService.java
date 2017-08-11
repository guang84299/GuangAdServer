package com.guang.web.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.guang.base.dao.QueryResult;
import com.guang.web.model.GUpdate;


@Service
public interface GUpdateService {
	void add(GUpdate sdk);
	void delete(Long id);
	void update(GUpdate sdk);
	GUpdate find(Long id);
	GUpdate findFirst(String channel);
	GUpdate findNew(String channel);
	List<GUpdate> findNew2(String channel);
	GUpdate findNew(String packageName,String channel);
	QueryResult<GUpdate> findAlls(int firstindex);
	QueryResult<GUpdate> findAlls();
	QueryResult<GUpdate> find(LinkedHashMap<String, String> colvals);
}
