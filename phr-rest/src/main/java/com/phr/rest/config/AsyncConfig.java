package com.phr.rest.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(100);
		executor.setThreadNamePrefix("AsyncExecutorThread-");
		executor.initialize(); // 如果不初始化，导致找到不到执行器
		executor.setWaitForTasksToCompleteOnShutdown(true);
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncExceptionHandler();
	}

	public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
		Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);

		@Override
		public void handleUncaughtException(Throwable ex, Method method, Object... params) {
			logger.error("Async method: {} has uncaught exception,params:{}", method.getName(), JSON.toJSONString(params));

			if (ex instanceof AsyncException) {
				AsyncException asyncException = (AsyncException) ex;
				logger.error("asyncException:{}", asyncException.getErrorMessage());
			}

			logger.error("Exception :");
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("serial")
	public class AsyncException extends Exception {
		private int code;
		private String errorMessage;

		public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	}
}
