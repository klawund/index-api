package com.klawund.index.search;

import com.klawund.index.search.result.IndexSearchResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexSearchService
{
	private final Logger logger;

	@Autowired
	public IndexSearchService(Logger logger)
	{
		this.logger = logger;
	}

	public IndexSearchResult search(String query)
	{
		return null;
	}
}
