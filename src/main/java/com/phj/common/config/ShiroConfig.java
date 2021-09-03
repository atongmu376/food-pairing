package com.phj.common.config;

import com.phj.common.shiro.ShiroFormAuthenticationFilter;
import com.phj.common.shiro.ShiroSessionIdGenerator;
import com.phj.common.shiro.ShiroSessionManager;
import com.phj.common.shiro.UserRealm;
import com.phj.common.util.SHA256Util;
import com.phj.pojo.SysUrl;
import com.phj.service.SysUrlService;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionsSecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.iherus.shiro.cache.redis.RedisCacheManager;
import org.iherus.shiro.cache.redis.RedisSessionDAO;
import org.iherus.shiro.cache.redis.connection.RedisConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

/**
 * @program: spring-boot-shiro
 * @description:
 * @author: Mr.Pan
 * @create: 2021-07-14 19:29
 **/
@Configuration
public class ShiroConfig {

    private Logger log=Logger.getLogger(String.valueOf(ShiroConfig.class));
    @Autowired
    private SysUrlService service;

    private final String CACHE_KEY = "shiro:cache:";
    private final String SESSION_KEY = "shiro:session:";

    //Redis配置
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;

    @Bean
    //密码加密规则   前端传进来比较也可
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用SHA256算法;
        shaCredentialsMatcher.setHashAlgorithmName(SHA256Util.HASH_ALGORITHM_NAME);
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        shaCredentialsMatcher.setHashIterations(SHA256Util.HASH_ITERATIONS);
        return shaCredentialsMatcher;
    }
    /**
     * 安全管理器
     */
    @Bean("mySecurityManager")
    public SessionsSecurityManager securityManager(SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 自定义Realm验证
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     *
     * 身份认证
     * */
    @Bean("authorizer")
    public UserRealm shiroRealm() {

        UserRealm shiroRealm = new UserRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return shiroRealm;
    }
    /**
     * SessionID生成器
     * @Author Sans
     * @CreateTime 2019/6/12 13:12
     */
    @Bean
    public ShiroSessionIdGenerator sessionIdGenerator(){
        return new ShiroSessionIdGenerator();
    }
    /**
     * 配置Session管理器
     * @Author Sans
     * @CreateTime 2019/6/12 14:25
     */
    @Bean
    public SessionManager sessionManager(@Qualifier("myRedisSessionDAO") SessionDAO dao) {
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setSessionDAO(dao);
        return shiroSessionManager;
    }
    @Bean({"shiroCacheManager"})
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setConnectionFactory(connectionFactory);
        cacheManager.setKeyPrefix(CACHE_KEY);
        cacheManager.setExpiration(Duration.ofSeconds(timeout));
        cacheManager.setScanBatchSize(3000);
        cacheManager.setFetchBatchSize(50);
        cacheManager.setDeleteBatchSize(5000);
        Optional.ofNullable(0);
        return cacheManager;
    }
    @Bean("myRedisSessionDAO")
    public RedisSessionDAO redisSessionDAO(CacheManager cacheManager) {

        RedisSessionDAO redisSessionDAO = new RedisSessionDAO(cacheManager);
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return redisSessionDAO;
    }
    //创建 ShiroFilterFactoryBean
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("mySecurityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /*
        添加Shiro内置过滤器，常用的有如下过滤器
            anon： 无需认证就可以访问
            authc： 必须认证才可以访问
            user： 如果使用了记住我功能就可以直接访问
            perms:  拥有某个资源权限才可以访问
            role： 拥有某个角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String, String>();
        List<SysUrl> sysUrls = service.selectAll();
        filterMap.put("/sysUser/login", "anon");
        filterMap.put("/sysUser/logout", "anon");
        filterMap.put("/", "anon");
        filterMap.put("/index", "anon");
        filterMap.put("/base/**", "anon");
        filterMap.put("/app/kaptcha/number.jpg", "anon");
        for (SysUrl sysUrl : sysUrls) {
            StringBuffer perms = new StringBuffer("perms");
            perms.append("[");
            perms.append(sysUrl.getCode());
            perms.append("]");
            log.info(perms.toString());
            filterMap.put(sysUrl.getUrl(), perms.toString());
        }
        //拦截所有请求 静态资源需要自行配置
        filterMap.put("/static/**", "anon");
        shiroFilterFactoryBean.setUnauthorizedUrl("/base/unauthorized");
        shiroFilterFactoryBean.setSuccessUrl("/");
        filterMap.put("/**", "authc");

        LinkedHashMap<String, Filter> filtsMap = new LinkedHashMap<>();
        // 这里使用自定义的filter  解决前后端未登录重定向json数据看不到
        filtsMap.put("authc", new ShiroFormAuthenticationFilter());

        shiroFilterFactoryBean.setFilters(filtsMap);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 开启Shiro-aop注解支持
     * 使用代理方式所以需要开启代码支持
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public Authenticator authenticator(){
        return new ModularRealmAuthenticator();
    }


    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {

        SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        //未经授权错误
        mappings.setProperty("org.apache.shiro.authz.UnauthorizedException","/base/unauthorized");
        mappings.setProperty("org.apache.shiro.authz.UnauthenticatedException","/base/loginError");
        r.setExceptionMappings(mappings);
        return r;
    }

}
