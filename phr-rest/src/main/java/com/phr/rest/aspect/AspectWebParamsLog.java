package com.phr.rest.aspect;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

@Aspect
@Component
public class AspectWebParamsLog {
	private static Logger logger = LoggerFactory.getLogger(AspectWebParamsLog.class);
	
	
	@Autowired
	private HttpServletRequest request;

	@Before("execution(* com.phr..controller..*.*(..))")
	public void before(JoinPoint jp) throws Exception {
		Object[] objects = jp.getArgs();
		// if (objects != null && objects.length > 0 && objects[0] instanceof
		// RequestBaseEntity) {
		// RequestBaseEntity baseEntity = null;
		// if (objects[0] == null) {
		// baseEntity = new RequestBaseEntity();
		// } else {
		// baseEntity = (RequestBaseEntity) objects[0];
		// }
		// baseEntity.setReqTime(new Date());
		// logger.info(" class[" + jp.getTarget().getClass().getName() + "] - "
		// + "method["
		// + jp.getSignature().getName() + "]传入参数：" +
		// JSON.toJSONString(objects[0]));
		//
		// }
		
		
	}

	// private static String repeat_url = "repeat:url:";

	@Around("execution(* com.phr..controller..*.*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable {

		try {
			Object[] objects = jp.getArgs();
			if (objects != null && objects.length > 0) {
				logger.info("【请求开始,打印入参】请求者IP["+getIpAddr(request)+"] 访问类名[" + jp.getTarget().getClass().getName() + "] - " + "方法名[" + jp.getSignature().getName() + "]传入参数："
						+ JSON.toJSONString(objects[0]));
			}
			if (objects != null && objects.length > 0 && objects[0] instanceof RequestBaseEntity) {
				Object obj = objects[0];

				ValidateResult result = ValidateEntity.validateFiled(obj);
				if (result.getCode() == 0)
					return ResultDataUtil.result(ResultData.STATUS_ERROR, result.getDesc().toString());
				;
			}
			Object rvt;
			long startTime = System.currentTimeMillis();// 获取开始时间
			rvt = jp.proceed(objects);
			long endTime = System.currentTimeMillis();// 获取结束时间
			long val = endTime - startTime;

			logger.info(" ===>class[" + jp.getTarget().getClass().getName() + "] - " + "method[" + jp.getSignature().getName() + "]方法 执行了：" + val + "ms");
			return rvt;
		} catch (Exception e) {
			return ResultDataUtil.errorResult(CoreConstant.SYSTEM_ABNORMALITY);
		}
	}

	@AfterReturning(pointcut = "execution(* com.phr..controller..*.*(..))", returning = "retVal")
	public void afterReturning(JoinPoint jp, Object retVal) throws Exception {

		// if (retVal != null) {
		// if (retVal instanceof ResponseEntity) {
		// Object[] objects = jp.getArgs();
		// if (objects != null && objects.length > 0 && objects[0] instanceof
		// RequestBaseEntity) {
		// RequestBaseEntity baseEntity = (RequestBaseEntity) objects[0];
		// ResponseEntity responseEntity = (ResponseEntity) retVal;
		// responseEntity.setRespTime(baseEntity.getReqTime());
		// }
		// logger.info(" class[" + jp.getTarget().getClass().getName() + "] - "
		// + "method["
		// + jp.getSignature().getName() + "]输出参数：" + retVal);
		// }
		// } else {
		logger.info("【请求结束,打印出参】请求者IP["+getIpAddr(request)+"] 访问类名[" + jp.getTarget().getClass().getName() + "] - " + "方法名[" + jp.getSignature().getName() + "]输出参数：" + JSON.toJSONString(retVal));
		// }
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