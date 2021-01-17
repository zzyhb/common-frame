package com.yhb.common.httpclient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.yhb.common.json.Jackson;
import com.yhb.common.other.NullUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LiuGong
 * @version $
 * @since 2019年12月05日 11:40
 */
@Slf4j
public class HttpClientUtil {

    private static final String ENCODING = "UTF-8";

    private CloseableHttpClient httpClient;

    public HttpClientUtil(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * 不带参数的get请求，如果状态码为200，则返回body
     *
     * @param url
     *
     * @return
     *
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
        return EntityUtils.toString(closeableHttpResponse.getEntity(), ENCODING);
    }

    /**
     * 带参数的get请求
     *
     * @param url
     * @param map
     *
     * @return
     *
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                // 遍历map,拼接请求参数
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        return this.doGet(uriBuilder.toString());
    }

    /**
     * 通过url-encoded pairs形式的参数发送POST请求
     *
     * @param url
     * @param map
     *
     * @return
     *
     * @throws Exception
     */
    public String doPost(String url, Map<String, Object> map) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (map != null) {
            List<NameValuePair> list = new ArrayList<>(map.size());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, ENCODING);
            httpPost.setEntity(urlEncodedFormEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), ENCODING);
    }

    /**
     * 通过url-encoded pairs形式的参数发送POST请求
     *
     * @param url
     * @param map
     *
     * @return
     *
     * @throws Exception
     */
    public String doPost(String url, Map<String, Object> map, Map<String, Object> headerMap) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (headerMap != null) {
            headerMap.forEach((k, v) -> {
                String value = String.valueOf(v);
                httpPost.addHeader(k, value);
            });
        }

        if (map != null) {
            List<NameValuePair> list = new ArrayList<>(map.size());
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, ENCODING);
            httpPost.setEntity(urlEncodedFormEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), ENCODING);
    }

    public <T, R> R doPost(String url, T t, Class<R> rClass) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (t != null) {
            String requestParam = Jackson.build()
                    .writeValueAsString(t);
            log.info("HttpClientUtil.doPost url:{}, requestParam:{}", url, requestParam);
            StringEntity requestEntity = new StringEntity(requestParam,
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), ENCODING);
        log.info("doPost.result:{}", result);
        if (result != null) {
            log.info("HttpClientUtil.doPost result:{}", result);
            return Jackson.build().readValue(result, rClass);
        }
        return null;
    }

    public <T, R> R doPost(String url, T t, Map<String, Object> headerMap, Class<R> rClass) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (headerMap != null) {
            headerMap.forEach((k, v) -> {
                String value = String.valueOf(v);
                httpPost.addHeader(k, value);
            });
        }

        if (t != null) {
            StringEntity requestEntity = new StringEntity(Jackson.build().writeValueAsString(t),
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String result = EntityUtils.toString(response.getEntity(), ENCODING);
        if (result != null) {
            return Jackson.build().readValue(result, rClass);
        }
        return null;
    }

    public String doJsonPost(String url, Map<String, Object> map) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (map != null) {
            StringEntity requestEntity = new StringEntity(Jackson.build().writeValueAsString(map),
                    ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), ENCODING);
    }

    public <T> Optional<T> httpGetRes(String url, Map<String, Object> param, Class<T> clazz) {
        try {
            log.info("get url={}, param={}", url, param);
            String result = doGet(url, param);
            log.info("get url={}, param={}, result={}", url, param,result);
            if (NullUtil.isNull(result)) {
                return Optional.empty();
            }
            return Optional.of(Jackson.build().readValue(result, clazz));
        } catch (Exception e) {
            log.error("api调用异常",e);
            log.error("get isError url={}, param={} ", url, param);
            return Optional.empty();
        }
    }

    /**
     * 不带参数的post请求
     *
     * @param url
     *
     * @return
     *
     * @throws Exception
     */
    public String doPost(String url) throws Exception {
        return this.doPost(url, null);
    }

    /**
     * 通过ux-www-form-urlencoded pairs形式的参数发送POST请求
     *
     * 温州
     *
     * @param url
     * @param headerMap
     *
     * @return
     *
     * @throws Exception
     */
    public String doPostUX(String url, Map<String, Object> headerMap) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-type", "application/x-www-form-urlencoded;charset=" + ENCODING);
        if(null != headerMap){
            //代码需优化，java8
            List<NameValuePair> par = new ArrayList<NameValuePair>();
            for (String key: headerMap.keySet()) {
                par.add(new BasicNameValuePair(key, headerMap.get(key).toString()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(par,ENCODING));
        }
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), ENCODING);
    }
}
