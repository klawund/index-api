package com.klawund.index.search;

import com.klawund.index.constants.IndexConstants;
import com.klawund.index.search.result.IndexSearchResult;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexSearchService
{
	private final Directory indexDirectory;
	private final Logger logger;

	@Autowired
	public IndexSearchService(Directory indexDirectory, Logger logger)
	{
		this.indexDirectory = indexDirectory;
		this.logger = logger;
	}

	public IndexSearchResult findByQuery(final String query)
	{
		final Query luceneQuery = buildQuery(query);
		return search(luceneQuery);
	}

	public IndexSearchResult findByFilter(final Map<String, String> filter)
	{
		final Set<String> queryTermSet = new HashSet<>();
		for (Map.Entry<String, String> filterPiece : filter.entrySet())
		{
			queryTermSet.add(filterPiece.getKey() + "=" + filterPiece.getValue());
		}

		Query query = buildBooleanQuery(queryTermSet);
		return search(query);
	}

	private IndexSearchResult search(Query luceneQuery)
	{
		final IndexSearchResult result = new IndexSearchResult();
		try (IndexReader reader = DirectoryReader.open(indexDirectory))
		{
			IndexSearcher searcher = new IndexSearcher(reader);
			TopDocs docs = searcher.search(luceneQuery, 10);
			ScoreDoc[] hits = docs.scoreDocs;

			result.setCount(hits.length);
			result.setTotalCount(docs.totalHits.value);

			for (ScoreDoc hit : hits)
			{
				result.add(searcher.doc(hit.doc));
			}
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	private Query buildQuery(final String query)
	{
		String escapedQuery = escapeQuery(query);
		return new WildcardQuery(new Term(IndexConstants.GLOBAL_FIELD_KEY, escapedQuery));
	}

	private String escapeQuery(String query)
	{
		String escapedQuery = query.replace("[", "\\[");
		escapedQuery = escapedQuery.replace("]", "\\]");
		return escapedQuery;
	}

	private Query buildBooleanQuery(final Set<String> queryTermSet)
	{
		Set<WildcardQuery> wildcardQuerySet = new HashSet<>();
		for (String queryTerm : queryTermSet)
		{
			wildcardQuerySet.add(
				new WildcardQuery(new Term(IndexConstants.GLOBAL_FIELD_KEY, "*" + queryTerm + "*")));
		}

		final BooleanQuery.Builder builder = new BooleanQuery.Builder();
		wildcardQuerySet.forEach(wildcardQuery -> builder.add(wildcardQuery, BooleanClause.Occur.MUST));

		return builder.build();
	}
}
