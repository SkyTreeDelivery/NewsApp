package com.example.hp.materialdesign.util;

import android.app.Application;
import android.util.Log;

import com.example.hp.materialdesign.application.MyApplication;
import com.example.hp.materialdesign.domain.Channel;
import com.example.hp.materialdesign.domain.ImageUrl;
import com.example.hp.materialdesign.domain.NewsDetail;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Encoder;



public class NewsApi {
    private static final String TAG = "NewsApi";

    //云市场分配的密钥Id
    final static String secretId = "AKIDnezG9igvcHb9d8nrW1gojci123xtinwhjQ";
    //云市场分配的密钥Key
    final static String secretKey = "7RS63FNP5PBmC86CH6fsA7M4Tu42M3cDiRatV2rA";
    final static String newsUrl = "https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/release/news";
    //云市场分配的密钥Key
    final static String channelUrl = "https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/release/channel";
    MyApplication app = null;

    public NewsApi(Application application) {
        app = (MyApplication) application;
    }

    public String calcAuthorization(String source, String secretId, String secretKey, String datetime)
            throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String signStr = "x-date: " + datetime + "\n" + "x-source: " + source;
        Mac mac = Mac.getInstance("HmacSHA1");
        Key sKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), mac.getAlgorithm());
        mac.init(sKey);
        byte[] hash = mac.doFinal(signStr.getBytes("UTF-8"));
        String sig = new BASE64Encoder().encode(hash);

        String auth = "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"" + sig + "\"";
        return auth;
    }

    public String urlencode(Map<?, ?> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    URLEncoder.encode(entry.getKey().toString(), "UTF-8"),
                    URLEncoder.encode(entry.getValue().toString(), "UTF-8")
            ));
        }
        return sb.toString();
    }

    private String getJsonBase(String secretId,String secretKey,String url, Map<String, String> queryParams,Map<String, String> bodyParams ) throws UnsupportedEncodingException {

        String source = "market";
        Calendar cd = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String datetime = sdf.format(cd.getTime());
        // 签名
        String auth = null;
        try {
            auth = calcAuthorization(source, secretId, secretKey, datetime);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        // 请求方法
        String method = "GET";
        // 请求头
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-Source", source);
        headers.put("X-Date", datetime);
        headers.put("Authorization", auth);
        //url拼接
        if (!queryParams.isEmpty()) {
            url += "?" + urlencode(queryParams);
        }
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod(method);

            // request headers
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // request body
            Map<String, Boolean> methods = new HashMap<>();
            methods.put("POST", true);
            methods.put("PUT", true);
            methods.put("PATCH", true);
            Boolean hasBody = methods.get(method);
            if (hasBody != null) {
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                conn.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(urlencode(bodyParams));
                out.flush();
                out.close();
            }

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String result = "";
            while ((line = in.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
    private String requestJsonNews(Map<String, String> queryParams,Map<String, String> bodyParams) throws UnsupportedEncodingException {
        return getJsonBase(secretId,secretKey,newsUrl,queryParams,bodyParams);
    }

    private String requestJsonChannel(Map<String, String> queryParams,Map<String, String> bodyParams) throws UnsupportedEncodingException {
        return getJsonBase(secretId,secretKey,channelUrl,queryParams,bodyParams);
    }

    /**
     * *
     * 根据channelid和page数请求
     * @param channelId
     * @param page
     * @return
     */
    public List<NewsDetail> requestNewsBychannelAndPage(String channelId, String page){
        List<NewsDetail> list = new ArrayList<NewsDetail>();
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("channelId", channelId);
        //查询channelId的中文名称
        List<Channel> channelsList = app.getChannelList();
        String channelName = new String();
        for(Channel channel: channelsList){
            if(channel.getChannelId() == channelId){
                channelName = channel.getChannelName();
            }
        }
        queryParams.put("channelName", channelName);
        queryParams.put("needAllList", "1");
        queryParams.put("needContent", "1");
        queryParams.put("page", page);
        queryParams.put("title", "");
        Map<String, String> bodyParams = new HashMap<String, String>();
        try {
            String jsonStr = requestJsonNews(queryParams,bodyParams);
            list = parseNewsJson(jsonStr);
            return list;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    private List<NewsDetail> parseNewsJson(String json){
        List<NewsDetail> list = new ArrayList<NewsDetail>();
        try {
            JSONObject job = new JSONObject(json);
            int showapi_res_code = job.getInt("showapi_res_code");
            if(showapi_res_code == 0){
                JSONObject showapi_res_body = job.getJSONObject("showapi_res_body");
                JSONObject pagebean = showapi_res_body.getJSONObject("pagebean");
                int pagenum = pagebean.getInt("allPages");
                JSONArray contentlist = pagebean.getJSONArray("contentlist");
                for(int i = 0; i < contentlist.length();i++){
                    JSONObject item = contentlist.getJSONObject(i);
                    if(item.getBoolean("havePic") == true){
                        JSONArray allList = item.getJSONArray("allList");
                        NewsDetail newsDetail = new NewsDetail();
                        //读取每个新闻的信息
                        List<String> text = new ArrayList<String>();
                        List<ImageUrl> imageUrls = new ArrayList<ImageUrl>();
                        for(int j = 0; j < allList.length();j++){
                            Object t = allList.get(j);
                            if(t instanceof JSONObject){
                                JSONObject iu = (JSONObject)t;
                                ImageUrl imageUrl = new ImageUrl(iu.getString("url"),j ,iu.getInt("height"),iu.getInt("width"));
                                imageUrls.add(imageUrl);
                            }else if(t instanceof String){
                                text.add(allList.getString(j));
                            }
                        }
                        //储存imageUrls以后使用
                        Map<String,Object> map = newsDetail.getBundle();
                        map.put("imageUrls",imageUrls);
                        newsDetail.setAllText(text);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        newsDetail.setDate(sdf.parse(item.getString("pubDate")));
                        newsDetail.setTitle(item.getString("title"));
                        newsDetail.setChannelId(item.getString("channelId"));
                        newsDetail.setNid(item.getString("id"));
                        newsDetail.setLink(item.getString("link"));
                        newsDetail.setSource(item.getString("source"));
                        list.add(newsDetail);
                    }
                }
                return list;
            }else{
                Log.e(TAG,"获取失败");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Channel> parseChannelJson(String json){
        List<Channel> list = new ArrayList<Channel>();
        try {
            //请求channel数据
            JSONObject jsonObject = new JSONObject(json);
            int showapi_res_code = jsonObject.getInt("showapi_res_code");
            if(showapi_res_code == 0){
                JSONObject showapi_res_body = jsonObject.getJSONObject("showapi_res_body");
                JSONArray channelList = showapi_res_body.getJSONArray("channelList");
                for(int i = 0; i < channelList.length(); i++){
                    JSONObject obj = channelList.getJSONObject(i);
                    list.add(new Channel(obj.getString("channelId"),obj.getString("name")));
                }
                return list;
            }else{
                Log.e(TAG,"获取失败");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Channel> requestChannel(){
        List<Channel> list = new ArrayList<Channel>();
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> bodyParams = new HashMap<String, String>();
        try {
            String json = requestJsonChannel(queryParams,bodyParams);
            list = parseChannelJson(json);
            return list;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
