package com.example.spring.config.persistence;

import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.spring.repository")
public class ElasticsearchConfig {

	@Value("${elasticsearch.host}")
	private String host;
	@Value("${elasticsearch.port}")
	private int port;
	@Value("${elasticsearch.clustername}")
	private String clusterName;

	@Bean
	public Client client() throws Exception {

		Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();

		TransportClient build = TransportClient.builder().settings(settings).build();
		build.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));

		return build;
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() throws Exception {
		return new ElasticsearchTemplate(client());
	}
}
