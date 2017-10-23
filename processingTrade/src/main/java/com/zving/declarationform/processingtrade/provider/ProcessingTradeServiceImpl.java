package com.zving.declarationform.processingtrade.provider;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zving.declarationform.processingtrade.model.ProcessingTrade;
import com.zving.declarationform.processingtrade.schema.ProcessingTradeService;
import com.zving.declarationform.storage.IStorage;
import com.zving.declarationform.storage.StorageUtil;

import io.servicecomb.provider.rest.common.RestSchema;

/**
 * @author zhaochangjin
 * @mail zhaochangjin@zving.com
 * @date 2017年10月20日
 */
@Controller
@RestSchema(schemaId = "processingTrade")
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON)
public class ProcessingTradeServiceImpl implements ProcessingTradeService {

	@Override
	@RequestMapping(path = "processingtrade", method = RequestMethod.POST)
	@ResponseBody
	public String add(@RequestBody ProcessingTrade processingTrade) {
		StorageUtil.getInstance().add(ProcessingTrade.class, processingTrade);
		return "1";
	}

	@Override
	@RequestMapping(path = "processingtrade", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestBody ProcessingTrade processingTrade) {
		ProcessingTrade old = get(processingTrade.getId());
		StorageUtil.getInstance().update(ProcessingTrade.class, old, processingTrade);
		return "1";
	}

	@Override
	@RequestMapping(path = "processingtrade/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable String ids) {
		for (String s : ids.split(",")) {
			final IStorage storage = StorageUtil.getInstance();
			List<ProcessingTrade> processingTrades = storage.find(ProcessingTrade.class, null);
			for (ProcessingTrade item : processingTrades) {
				if (item.getId() == Long.parseLong(s)) {
					storage.delete(ProcessingTrade.class, item);
				}
			}
		}
		return "1";
	}

	@Override
	@RequestMapping(path = "processingtrade/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ProcessingTrade get(@PathVariable long id) {
		ProcessingTrade processingTrade = new ProcessingTrade();
		processingTrade.setId(id);
		return StorageUtil.getInstance().get(ProcessingTrade.class, processingTrade);
	}

	@Override
	@RequestMapping(path = "processingtrade", method = RequestMethod.GET)
	@ResponseBody
	public List<ProcessingTrade> list() {
		return StorageUtil.getInstance().find(ProcessingTrade.class, null);
	}

}
