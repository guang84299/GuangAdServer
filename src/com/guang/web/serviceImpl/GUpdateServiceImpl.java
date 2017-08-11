package com.guang.web.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.base.dao.DaoTools;
import com.guang.base.dao.QueryResult;
import com.guang.web.model.GUpdate;
import com.guang.web.service.GUpdateService;


@Service
public class GUpdateServiceImpl implements GUpdateService{
	@Resource private DaoTools daoTools;

	public void add(GUpdate sdk) {
		daoTools.add(sdk);
	}

	public void delete(Long id) {
		daoTools.delete(GUpdate.class, id);
	}

	public void update(GUpdate sdk) {
		daoTools.update(sdk);
	}

	public GUpdate find(Long id) {
		return daoTools.find(GUpdate.class, id);
	}
	
	public GUpdate findFirst(String channel) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		List<GUpdate> list = daoTools.find(GUpdate.class,"channel",channel, 0, 1, lhm).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public GUpdate findNew(String channel) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		List<GUpdate> list = daoTools.find(GUpdate.class, "online",1+"","channel",channel, 0, 1, lhm).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	public GUpdate findNew(String packageName,String channel) {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
//		colvals.put("online =", 1+"");
		colvals.put("packageName =", "'"+packageName+"'");
		colvals.put("channel =", "'"+channel+"'");
		List<GUpdate> list = find(colvals).getList();
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	public QueryResult<GUpdate> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUpdate.class, null,null, firstindex, 100, lhm);
	}
	
	public QueryResult<GUpdate> findAlls() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUpdate.class, null,null, 0, 10000000, lhm);
	}

	public QueryResult<GUpdate> find(LinkedHashMap<String, String> colvals) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GUpdate.class, colvals, 0, 1, lhm);
	}
	
}
