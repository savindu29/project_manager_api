package com.inova.project_manager_api.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class CommonUtil {
    @SuppressWarnings("serial")
    public static HttpHeaders createMultiHttpHeader(String userName, String password, long contentLength){
        HttpHeaders header = new HttpHeaders() {{
            String auth = userName + ":" + password;
            byte[] encodedAuth;
            encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")), false );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );

        }};
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
        header.setContentLength(contentLength);

        return header;
    }
    @SuppressWarnings("serial")
    public static HttpHeaders createMultiHttpHeader(String userName,String password){
        HttpHeaders header = new HttpHeaders() {{
            String auth = userName + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")), false);
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );

        }};
        header.setContentType(MediaType.MULTIPART_FORM_DATA);

        return header;
    }

}
