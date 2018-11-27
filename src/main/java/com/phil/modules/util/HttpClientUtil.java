package com.phil.modules.util;

import org.apache.commons.collections4.MapUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URIBuilder builder = new URIBuilder(url);
            if (MapUtils.isNotEmpty(params)) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                if (response.getStatusLine().getStatusCode() == 200) {
                    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url) {
        return doGet(url,null);
    }

    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        if (MapUtils.isNotEmpty(params)) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);
                httpPost.setEntity(entity);
                try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                    return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}