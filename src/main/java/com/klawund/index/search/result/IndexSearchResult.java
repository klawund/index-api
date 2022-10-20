package com.klawund.index.search.result;

import java.util.HashSet;
import java.util.Set;
import org.apache.lucene.document.Document;

public class IndexSearchResult
{
	private long count;
	private long totalCount;
	private Set<Object> data = new HashSet<>();

	public IndexSearchResult()
	{
	}

	public long getCount()
	{
		return count;
	}

	public void setCount(long count)
	{
		this.count = count;
	}

	public long getTotalCount()
	{
		return totalCount;
	}

	public void setTotalCount(long totalCount)
	{
		this.totalCount = totalCount;
	}

	public Set<Object> getData()
	{
		return data;
	}

	public void setData(Set<Object> data)
	{
		this.data = data;
	}

	public void add(final Document document)
	{
		getData().add(document);
	}

	public void remove(final Document document)
	{
		getData().remove(document);
	}
}
