package com.klawund.index.search.result;

import java.util.HashMap;
import java.util.Map;

public class IndexSearchResult
{
	private Map<String, Object> data = new HashMap<>();
	private long count;
	private long totalCount;

	public IndexSearchResult()
	{
	}

	public IndexSearchResult(Map<String, Object> data, long totalCount)
	{
		this.data = data;
		this.count = data.size();
		this.totalCount = totalCount;
	}

	public Map<String, Object> getData()
	{
		return data;
	}

	public IndexSearchResult setData(Map<String, Object> data)
	{
		this.data = data;
		return this;
	}

	public long getCount()
	{
		return count;
	}

	public IndexSearchResult setCount(long count)
	{
		this.count = count;
		return this;
	}

	public long getTotalCount()
	{
		return totalCount;
	}

	public IndexSearchResult setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
		return this;
	}
}
