package by.bsuir.service_client.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TokenAuthenticationFilter implements WebFilter {

    private final TokenService tokenService;

    public TokenAuthenticationFilter(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /*@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String token = ((HttpServletRequest) request).getHeader(TokenData.TOKEN.getValue());
        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        final TokenAuthentication authentication = tokenService.parseAndCheckToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }*/

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        final String token = ((HttpServletRequest) request).getHeader(TokenData.TOKEN.getValue());
        if (token == null) {
            return chain.filter(exchange);
        }

        final TokenAuthentication authentication = tokenService.parseAndCheckToken(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return chain.filter(exchange);
    }
}
