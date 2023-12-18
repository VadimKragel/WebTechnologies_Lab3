package by.bsuir.lab3.web.filters;

import jakarta.servlet.*;

import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		String responseEncoding = servletResponse.getCharacterEncoding();
		if (encoding != null && !encoding.equalsIgnoreCase(responseEncoding))
			servletResponse.setCharacterEncoding(encoding);

		String requestEncoding = servletRequest.getCharacterEncoding();
		if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding))
			servletRequest.setCharacterEncoding(encoding);

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		encoding = null;
	}
}
