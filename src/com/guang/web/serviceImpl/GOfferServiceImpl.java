package com.guang.web.serviceImpl;

import java.util.LinkedHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.guang.base.dao.DaoTools;
import com.guang.base.dao.QueryResult;
import com.guang.web.model.GOffer;
import com.guang.web.service.GOfferService;
@Service
public class GOfferServiceImpl implements GOfferService{
	@Resource private DaoTools daoTools;
	
	public void add(GOffer offer) {
		daoTools.add(offer);
	}

	public void delete(long id) {
		daoTools.delete(GOffer.class, id);
	}

	public void update(GOffer offer) {
		daoTools.update(offer);
	}

	public GOffer find(long id) {
		return daoTools.find(GOffer.class, id);
	}

	public QueryResult<GOffer> findAlls(int firstindex) {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOffer.class, null,null, firstindex, 100, lhm);
	}

	public QueryResult<GOffer> findAlls() {
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("id", "desc");
		return daoTools.find(GOffer.class, null,null, 0, 1000, lhm);
	}

	public QueryResult<GOffer> findAllsByOnlie() {
		LinkedHashMap<String, String> colvals = new LinkedHashMap<String, String>();
		colvals.put("online =", 1+"");
		LinkedHashMap<String, String> lhm = new LinkedHashMap<String, String>();
		lhm.put("priority", "desc");
		
		return daoTools.find(GOffer.class, colvals, 0, 1000, lhm);
	}

}
