package com.phr.rest.aspect;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.phr.common.dto.ResultData;
import com.phr.common.utils.ResultDataUtil;
import com.phr.common.validate.ValidateEntity;
import com.phr.common.validate.ValidateResult;
import com.phr.core.constants.CoreConstant;
import com.phr.core.entity.RequestBaseEntity;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class AspectWebParamsLog {
	private static Logger logger = LoggerFactory.getLogger(AspectWebParamsLog.class);
	
	
	@Autowired
	private HttpServletRequest request;

	/*@Before("execution(* com.phr..controller..*.*(..))")
	public void before(JoinPoint jp) throws Exception {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String url = request.getRequestURL().toString();
		logger.info("【请求开始】请求者IP:{} ,请求url:{},请求param:{}" ,getIpAddr(request),url,Arrays.toString(jp.getArgs()));

	}

	@Around("execution(* com.phr..controller..*.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String url = request.getRequestURL().toString();

		try {
			Object[] objects = jp.getArgs();



			*//*if (objects != null && objects.length > 0 && objects[0] instanceof RequestBaseEntity) {
				Object obj = objects[0];

				ValidateResult result = ValidateEntity.validateFiled(obj);
				if (result.getCode() == 0)
					return ResultDataUtil.result(ResultData.STATUS_ERROR, result.getDesc().toString());
				;
			}*//*
			Object rvt;
			long startTime = System.currentTimeMillis();// 获取开始时间
			rvt = jp.proceed(objects);
			long endTime = System.currentTimeMillis();// 获取结束时间
			long val = endTime - startTime;
			logger.info("【请求结束】请求者IP:{} ,请求url:{},请求result:{},执行时间:{}ms" ,getIpAddr(request),url,JSON.toJSONString(rvt),val);
			return rvt;
		} catch (Exception e) {
			return ResultDataUtil.errorResult(CoreConstant.SYSTEM_ABNORMALITY);
		}
	}

	@AfterReturning(pointcut = "execution(* com.phr..controller..*.*(..))", returning = "retVal")
	public void afterReturning(JoinPoint jp, Object retVal) throws Exception {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String url = request.getRequestURL().toString();

	}*/

	/*@Pointcut("@annotation(com.phr)")
	public void logPointcut(){}*/

	@Around("execution(* com.phr..controller..*.*(..))")
	public Object logHandler(ProceedingJoinPoint process) throws Throwable{
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		String url = request.getRequestURL().toString();

		long startTime=System.currentTimeMillis();
		MethodSignature methodSignature= (MethodSignature) process.getSignature();
		Method method=methodSignature.getMethod();
		String methodName=method.getName();
		String className= method.getDeclaringClass().getName();
		Object[] args=process.getArgs();
		StringBuilder params=new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			params.append(args[i].toString());
			params.append(";");
		}
		Object result= null;
		try {
			result = process.proceed();
		} catch (Throwable throwable) {
			String exception=throwable.getClass()+":"+throwable.getMessage();
			long costTime=System.currentTimeMillis()-startTime;
			log.error("请求url：{}，请求耗时：{}，请求类名：{}，请求方法：{}，请求参数:{}，请求结果：{}",url,costTime,className,methodName,params.toString(),exception);
			//return CustomerResponse.buildFail(throwable.getMessage());
		}
		long costTime=System.currentTimeMillis()-startTime;
		log.info("请求url：{}，请求耗时：{}，请求类名：{}，请求方法：{}，请求参数:{}，请求结果：{}",url,costTime,className,methodName,params.toString(), result);
		return result;
	}


	@AfterThrowing(pointcut = "execution(* com.phr..controller..*.*(..))", throwing = "ex")
	public void doExceptionActions(JoinPoint jp, Throwable ex) {
		System.out.println("*************************************");
		System.out.println("Error happened in class: " + jp.getTarget().getClass().getName());
		System.out.println("Error happened in method: " + jp.getSignature().getName());

		for (int i = 0; i < jp.getArgs().length; i++) {
			System.out.println("arg[" + i + "]: " + jp.getArgs()[i]);
		}

		System.out.println("Exception class: " + ex.getMessage());
		ex.printStackTrace();
		System.out.println("*************************************");
	}

	public String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length() = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}