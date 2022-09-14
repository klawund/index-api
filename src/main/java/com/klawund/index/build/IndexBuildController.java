package com.klawund.index.build;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/index/build")
public class IndexBuildController
{
	private final Logger logger;
	private final IndexBuildService indexBuildService;

	@Autowired
	public IndexBuildController(Logger logger, IndexBuildService indexBuildService)
	{
		this.logger = logger;
		this.indexBuildService = indexBuildService;
	}

	@PostMapping
	public void build(@RequestBody Map<String, Object> body)
	{
		logger.log(Level.INFO, body.toString());
	}
}
