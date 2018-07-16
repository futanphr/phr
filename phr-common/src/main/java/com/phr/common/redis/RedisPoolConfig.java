package com.phr.common.redis;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cache.redis")
public class RedisPoolConfig {

	private boolean enabled;
	private String cacheName;
	private String hostName;

	private Integer port;

	private String password;

	private Integer database;

	private Map<String, String> pool;
	private Integer timeout;
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDatabase() {
		return database;
	}
	public void setDatabase(Integer database) {
		this.database = database;
	}
	public Map<String, String> getPool() {
		return pool;
	}
	public void setPool(Map<String, String> pool) {
		this.pool = pool;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	
	

}
