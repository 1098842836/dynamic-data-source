package com.yl.dynamicdatasource.config;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @description: request的包装类
 * @author: yl
 * @date: 2022-08-24
 **/
public class ParamValidateServletRequestWrapper extends HttpServletRequestWrapper {
    private final String body;

    public ParamValidateServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        StringBuilder requestBody = new StringBuilder();
        try (ServletInputStream inputStream = request.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                requestBody.append(requestBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        body=requestBody.toString();
    }
}
