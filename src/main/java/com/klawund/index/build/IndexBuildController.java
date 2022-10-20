package com.klawund.index.build;

import java.util.Map;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/index/build")
public class IndexBuildController
{
	private final IndexBuildService indexBuildService;

	@Autowired
	public IndexBuildController(IndexBuildService indexBuildService)
	{
		this.indexBuildService = indexBuildService;
	}

	@PostMapping
	public Document build(@RequestBody Map<String, String> fields)
	{
		return indexBuildService.createAndWrite(fields);
	}
}
