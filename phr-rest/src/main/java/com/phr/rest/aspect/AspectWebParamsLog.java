package com.phr.rest.aspect;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class AspectWebParamsLog {

	@Autowired
	private HttpServletRequest request;

	@Around("execution(* com.phr..controller..*.*(..))")
	public Object logHandler(ProceedingJoinPoint pjp) throws Throwable{
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String param = request.getQueryString();
        String method = request.getMethod();

        //开始时间
        long startTime=System.currentTimeMillis();
        String startTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);

        log.info("START===>request_url= {} {}{}，request_method={}，request_param={}，request_ip={}，begin_time={}",method,url,(param==null?"":"?"+param),("{"+pjp.getTarget().getClass().getSimpleName() + "#"  + pjp.getSignature().getName()+"}"),JSON.toJSONString(pjp.getArgs()),getIpAddr(request),startTimeStr);
        //执行代码
        Object result=null;

        result = pjp.proceed();


        //结束时间
        long endTime=System.currentTimeMillis();
        String  endTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        //总共用时
        long cousumeTime=endTime-startTime;

        log.info("END===>request_url= {} {}{}，request_method={}，consume_time={}ms，request_param={},response_result={}，request_ip={}，begin_time={}，end_time={}",method,url,(param==null?"":"?"+param),("{"+pjp.getTarget().getClass().getSimpleName() + "#"  + pjp.getSignature().getName()+"}"),cousumeTime,JSON.toJSONString(pjp.getArgs()),JSON.toJSONString(result),getIpAddr(request),startTimeStr,endTimeStr);
        return result;
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
    // 4.将获得的所有参数封装到一个Map<String,String[]>
    public static String getParamsForPostMethod(HttpServletRequest request){
	    StringBuilder params=new StringBuilder();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            params.append(entry.getKey());
            for (String string : entry.getValue()) {

                System.out.println(string);
            }
        }
        return params.toString();
    }

}