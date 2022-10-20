package com.klawund.index.build;

import com.klawund.index.constants.IndexConstants;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexBuildService
{
	private final Directory indexDirectory;
	private final Logger logger;

	@Autowired
	public IndexBuildService(Directory indexDirectory, Logger logger)
	{
		this.indexDirectory = indexDirectory;
		this.logger = logger;
	}

	public Document createAndWrite(final Map<String, String> fields)
	{
		final Document document = create(fields);
		write(document);
		return document;
	}

	private Document create(final Map<String, String> fields)
	{
		final Document document = new Document();
		final Set<String> globalValueSet = new HashSet<>();

		fields.forEach((key, value) -> {
			document.add(new TextField(key, value, Field.Store.YES));
			globalValueSet.add(key + "=" + value);
		});

		document.add(new StringField(IndexConstants.GLOBAL_FIELD_KEY,
			String.join(IndexConstants.GLOBAL_FIELD_VALUE_DELIMITER, globalValueSet), Field.Store.YES));

		return document;
	}

	private void write(final Document document)
	{
		if (indexDirectory == null)
		{
			return;
		}

		try (StandardAnalyzer analyzer = new StandardAnalyzer())
		{
			IndexWriterConfig writerConfig = new IndexWriterConfig(analyzer);
			try (IndexWriter writer = new IndexWriter(indexDirectory, writerConfig))
			{
				writer.addDocument(document);
			}
			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
			}
		}
	}
}
