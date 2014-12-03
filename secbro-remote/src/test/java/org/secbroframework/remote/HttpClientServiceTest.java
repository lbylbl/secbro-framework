package org.secbroframework.remote;

import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for HttpClientService.
 */
public class HttpClientServiceTest extends TestCase{
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientServiceTest.class);
	private HttpClientService client = null;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HttpClientServiceTest(String testName){
        super(testName);
    }

    /**
     * 此方法在执行每一个测试方法之前（测试用例）之前调用
     */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		client = new HttpClientService(); 
	}

	/**
	 * 此方法在执行每一个测试方法之后调用 
	 */
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}

    
    public void testHttpClientService(){
		// https://bgp.reapal.com
		// https://www.alipay.com/
		String url = "https://www.alipay.com/";
		logger.info("url地址：{}" + url);
		Map<String,Object> map = client.getHttpResp("UTF-8", url, new HttpMethodCallback(){
			@Override
			public PostMethod initMethod(PostMethod method) {
				return method;
			}
		});
		logger.info("statusCode:{},response:{}" ,map.get("statusCode"),map.get("response"));
    }
    
}
