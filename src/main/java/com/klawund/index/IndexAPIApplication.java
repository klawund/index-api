package com.klawund.index;

import com.klawund.index.config.IndexConfiguration;
import java.nio.file.Path;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IndexAPIApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(IndexAPIApplication.class, args);
	}

	@Bean
	public Logger getLogger(InjectionPoint injectionPoint)
	{
		return LoggerFactory.getLogger(injectionPoint.getDeclaredType().getSimpleName());
	}

	@Bean
	public IndexConfiguration getIndexConfiguration(@Value("${index.folder.path}") String folderPath)
	{
		return new IndexConfiguration(folderPath);
	}

	@Bean
	@Autowired
	public Directory getIndexDirectory(IndexConfiguration indexConfiguration)
	{
		try
		{
			return FSDirectory.open(Path.of(indexConfiguration.getPath()));
		}
		catch (Exception e)
		{
			return null;
		}
	}
}
