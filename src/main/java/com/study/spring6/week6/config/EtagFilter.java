package com.study.spring6.week6.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import java.io.IOException;

public class EtagFilter extends ShallowEtagHeaderFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
        response.setHeader("Cache-Control", "max-age=600, must-revalidate");
    }

}
