package com.klawund.index.search;

import com.klawund.index.search.result.IndexSearchResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/index/search")
public class IndexSearchController
{
	private final Logger logger;
	private final IndexSearchService indexSearchService;

	@Autowired
	public IndexSearchController(Logger logger, IndexSearchService indexSearchService)
	{
		this.logger = logger;
		this.indexSearchService = indexSearchService;
	}

	@GetMapping
	public IndexSearchResult search(@RequestParam String query)
	{
		logger.log(Level.INFO, query);
		return indexSearchService.search(query);
	}
}
