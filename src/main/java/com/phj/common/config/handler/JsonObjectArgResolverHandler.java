package com.phj.common.config.handler;

import com.alibaba.fastjson.JSON;
import com.jayway.jsonpath.JsonPath;
import com.phj.common.config.annotation.JsonObject;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: spring-boot-shiro
 * @description:
 * @author: Mr.Pan
 * @create: 2021-07-23 14:17
 **/

public class JsonObjectArgResolverHandler implements HandlerMethodArgumentResolver {
   private final Logger logger = LoggerFactory.getLogger(JsonObjectArgResolverHandler.class);


    @Override
    /**
     * 判断参数是否使用JsonObject注解 是返回true
     */
    public boolean supportsParameter(MethodParameter parameter) {
        logger.debug("判断了注解JsonObject");
        return parameter.hasParameterAnnotation(JsonObject.class);
    }

    /**
     * @Author Lux Sun
     * @Description: 重写 resolveArgument，核心函数：分解参数功能
     * @Param: [methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory]
     * @Return: java.lang.Object
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String body = getRequestBody(nativeWebRequest);
        String annotationKey = null;
        Object matchObj = null;
        logger.debug(body);
        try {
            // 获取注解 value
            annotationKey = methodParameter.getParameterAnnotation(JsonObject.class).value();
            if (annotationKey.equals("")) {
                annotationKey=methodParameter.getParameterName();
            }
            int midBracket = body.indexOf('[');
            int bigBracket = body.indexOf('{');
            // 匹配[...{}...]、[...]、{...}类型
            if(bigBracket > midBracket && midBracket != -1 || midBracket != -1 && bigBracket == -1)
            {
                // 此时的annotationKey取什么都可以，因为下面一定会解析出属于JSONArray类型，那时才是真正的key
                body = "{\"" + annotationKey + "\":" + body + "}";
            }

            // 默认只读第一层{...}中key（至此一定是{...}结构）
            matchObj = JsonPath.read(body, annotationKey);
            if (methodParameter.getParameterAnnotation(JsonObject.class).required() && matchObj == null) {
                throw new Exception(annotationKey + "不能为空");
            }
            else if(matchObj instanceof LinkedHashMap && ((Map)matchObj).size()==0) // { key: {} }
            {
                throw new Exception("JSON对象为空");
            }
            else if(matchObj instanceof JSONArray && ((List)matchObj).size()==0) // { key: [] }
            {
                throw new Exception("JSONArray为空");
            }
        }
        catch (Exception e) {
            // 情况1：required == false && 找不到 key 对应的 json（无论Body是否为空）
            if (!methodParameter.getParameterAnnotation(JsonObject.class).required()) {
                return null;
            }

            // 情况2：required == true && 找不到 key 对应的 json（无论Body是否为空）
            e.printStackTrace();
            return null;
        }

        // Map2JsonString
        String matchJsonStr = JSON.toJSONString(matchObj);

        // methodParameter.getGenericParameterType() 返回参数的完整类型（带泛型）（web层形参的类型）
        final Type type = methodParameter.getGenericParameterType();

        // 判断转换类型
        if(Map.class.isAssignableFrom(methodParameter.getParameterType())){ // Map类型（使用自己新封装的JSONObject充当Map）
            if(matchObj instanceof JSONArray) // 匹配JSONArray类型，前面需要加一个key，组装成JSONObject类型格式
            {
                matchJsonStr = "{\"" + annotationKey + "\":" + matchJsonStr + "}";
            }
            Map mapWrapper = new HashMap(JSON.parseObject(matchJsonStr));
            return mapWrapper;
        }
        else { // Number、BigDecimal/BigInteger、Boolean、String、Entity/List<Entity>
            Object rsObj = null;
            try {
                rsObj = JSON.parseObject(matchJsonStr, type);
            }
            catch (Exception e) {
                // 情况：形参类型与JSON解析后的类型不匹配
                e.printStackTrace();
            }
            return rsObj;
        }
    }

    /**
     * @Author Lux Sun
     * @Description: 获取请求包的Body内容
     * @Param: [nativeWebRequest]
     * @Return: java.lang.String
     */
    private String getRequestBody(NativeWebRequest nativeWebRequest) {


        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = null;
        String jsonBody = (String) request.getAttribute("JSON_REQUEST_BODY");
        if (jsonBody != null) {
            return jsonBody;
        } else {
            try {
                reader = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
                String line=null;
                StringBuilder sb=new StringBuilder();
                while ((line=reader.readLine())!=null){
                    sb.append(line);
                }
                String s = sb.toString();
                request.setAttribute("JSON_REQUEST_BODY", s);
                return s;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
