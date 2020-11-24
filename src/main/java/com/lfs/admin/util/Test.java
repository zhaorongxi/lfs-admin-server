package com.lfs.admin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lfs.base.util.CollectionUtils;
import com.lfs.base.util.HttpClientUtil;
import com.lfs.base.util.MD5Utils;
import com.lfs.http.httpclient.HttpClientGet;
import com.lfs.http.httpclient.HttpClientPost;
import com.lfs.http.httpclient.OkHttpUtil;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        arrayIndexOutOfBounds();
    }

    public static String getHttpEntityContent(HttpResponse response) throws UnsupportedOperationException, IOException{
        String result = "";
        HttpEntity entity = response.getEntity();
        if(entity != null){
            InputStream in = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
            StringBuilder strber= new StringBuilder();
            String line = null;
            while((line = br.readLine())!=null){
                strber.append(line+'\n');
            }
            br.close();
            in.close();
            result = strber.toString();
        }

        return result;

    }


    public static void arrayIndexOutOfBounds(){
        try {
            List<String> testList = new ArrayList<>();
            String[] charge = new String[]{"1","2","3","4"};
            if(CollectionUtils.isEmpty(charge)){
                System.out.println(1);
            }
            testList.add("one");
            testList.add("two");

            testList.get(1);

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("okTest","ok");
            map.put("failTest","fail");
            Integer a = (Integer) map.get("okTest");
            System.out.println(a);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("访问了不存在的数组下标!");
            e.printStackTrace();
        } catch (ClassCastException e){
            System.out.println("无法转换为强类型!");
            e.printStackTrace();
        }finally {
            System.out.println("程序结束!");
        }
    }
}
