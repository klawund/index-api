package com.klawund.index;

import java.util.logging.Logger;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IndexApiApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(IndexApiApplication.class, args);
	}

	@Bean
	public Logger getLogger(InjectionPoint injectionPoint)
	{
		return Logger.getLogger(injectionPoint.getDeclaredType().getSimpleName());
	}
}
