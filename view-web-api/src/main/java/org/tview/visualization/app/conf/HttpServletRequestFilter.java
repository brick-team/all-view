package org.tview.visualization.app.conf;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** 过滤器，用于截取RequestBody */
// @Configuration
// @WebFilter(urlPatterns = "/*",filterName = "httpServletRequestFilter")
public class HttpServletRequestFilter implements Filter {

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    // 防止流读取一次后就没有了, 所以需要将流继续写出去
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
    filterChain.doFilter(requestWrapper, servletResponse);
  }
}
