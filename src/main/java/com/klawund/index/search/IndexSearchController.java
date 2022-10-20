package com.klawund.index.search;

import com.klawund.index.search.result.IndexSearchResult;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/index/search")
public class IndexSearchController
{
	private final IndexSearchService indexSearchService;

	@Autowired
	public IndexSearchController(IndexSearchService indexSearchService)
	{
		this.indexSearchService = indexSearchService;
	}

	@PostMapping(path = "find-by-query")
	public IndexSearchResult findByQuery(@RequestParam String query)
	{
		return indexSearchService.findByQuery(query);
	}

	@PostMapping(path = "find-by-filter")
	public IndexSearchResult findByFilter(@RequestBody Map<String, String> filter)
	{
		return indexSearchService.findByFilter(filter);
	}
}
