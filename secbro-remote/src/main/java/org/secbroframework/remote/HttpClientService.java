package org.secbroframework.remote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;

//@Service
//@Scope("prototype")
public class HttpClientService {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    public Map<String, Object> getHttpResp(final String charset, String httpUrl, HttpMethodCallback methodCallback) {

        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        Map<String, Object> mp = new HashMap<String, Object>();
        String response = "";
        try {
            URL url = new URL(httpUrl);

            PostMethod method = new PostMethod(httpUrl);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, new Integer(2000));

            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);
            client.getParams().setContentCharset(charset);

            if (httpUrl.indexOf("https") == 0) { // https
                logger.info("https......port:{}",url.getPort());
                Protocol myhttps = new Protocol("https", new MySecureProtocolSocketFactory(), url.getPort() == -1 ? 443: url.getPort());
                Protocol.registerProtocol("https", myhttps);
                client.getHostConfiguration().setHost(url.getHost(), url.getPort() == -1 ? 443 : url.getPort(), myhttps);
            } else { // http 请求
                client.getHostConfiguration().setHost(url.getHost(), url.getPort(), url.getProtocol());
            }
            methodCallback.initMethod(method);
            logger.info("charset: {} try to connect:{} body: queryString:{}"
                    ,new Object[]{charset,httpUrl,method.getQueryString()});
            int rescode = client.executeMethod(method);
            mp.put("statusCode", rescode);
            if (rescode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charset));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
                logger.info("{} response:{}",httpUrl,response);
                mp.put("response", response);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                        charset));
                String curline = "";
                while ((curline = reader.readLine()) != null) {
                    response += curline;
                }
                mp.put("response", response);
                logger.error("{} error:{}",httpUrl,rescode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("response:{}", response);
        return mp;
    }
}
