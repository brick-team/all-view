package org.tview.visualization.app.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

  @Bean
  public FilterRegistrationBean<Filter> httpServletRequestReplacedRegistration() {
    FilterRegistrationBean<Filter> result = new FilterRegistrationBean<>();
    result.setFilter(new HttpServletRequestFilter());
    result.addUrlPatterns("/*");
    result.setName("httpServletRequestFilter");
    result.setOrder(Ordered.LOWEST_PRECEDENCE);
    return result;
  }
}
