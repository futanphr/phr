package com.phr.common.thread;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理
 */
public class ThreadPoolManager {

	private static ThreadPoolManager tpm = null;

	// 线程池维护线程的最少数量
	private final static int CORE_POOL_SIZE = 5;

	// 线程池维护线程的最大数量
	private final static int MAX_POOL_SIZE = 5000;

	// 线程池维护线程所允许的空闲时间
	private final static int KEEP_ALIVE_TIME = 10;

	// 线程池所使用的缓冲队列大小
	private final static int WORK_QUEUE_SIZE = 1000;

	// 任务调度周期
	private final static int TASK_QOS_PERIOD = 10;

	// 任务缓冲队列
	private Queue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

	/*
	 * 线程池超出界线时将任务加入缓冲队列
	 */
	final RejectedExecutionHandler handler = new RejectedExecutionHandler() {
		public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
			taskQueue.offer(task);
		}
	};

	/**
	 * 将缓冲队列中的任务重新加载到线程池
	 */
	final Runnable accessBufferThread = new Runnable() {
		public void run() {
			if (hasMoreAcquire()) {
				threadPool.execute(taskQueue.poll());
			}
		}
	};

	/**
	 * 创建一个调度线程池
	 */
	final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	/**
	 * 通过调度线程周期性的执行缓冲队列中任务
	 */
	final ScheduledFuture<?> taskHandler = scheduler.scheduleAtFixedRate(accessBufferThread, 0, TASK_QOS_PERIOD, TimeUnit.MILLISECONDS);

	/**
	 * 线程池
	 */
	final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE), this.handler);

	/**
	 * 将构造方法访问修饰符设为私有，禁止任意实例化。
	 */
	private ThreadPoolManager() {

	}

	/**
	 * 线程池单例创建方法
	 */
	public static ThreadPoolManager newInstance() {
		if (tpm == null) {
			tpm = new ThreadPoolManager();
		}
		return tpm;
	}

	/**
	 * 消息队列检查方法
	 */
	private boolean hasMoreAcquire() {
		return !taskQueue.isEmpty();
	}

	/**
	 * 向线程池中添加任务方法
	 */
	public void addExecuteTask(Runnable task) {
		if (task != null) {
			threadPool.execute(task);
		}
	}

	/**
	 * 获取存线程池活数
	 * 
	 * @return
	 */
	public int getActiveCount() {
//		threadPool.shutdown();
//		System.out.println(threadPool.getCompletedTaskCount());
//		System.out.println(threadPool.getTaskCount());
//		System.out.println(threadPool.getKeepAliveTime(TimeUnit.SECONDS));
//		System.out.println(	threadPool.isTerminated());
//		System.out.println(	threadPool.isTerminating());
//		System.out.println(	threadPool.isShutdown());
		return threadPool.getActiveCount();
	}
	public void isShutdown() {

		 threadPool.isShutdown();
	}

	/**
	 * 向线程池中添加任务方法
	 */
	public Future<Object> submitExecuteTask(Callable<Object> task) {
		if (task != null) {
			return threadPool.submit(task);
		}
		return null;
	}
}
