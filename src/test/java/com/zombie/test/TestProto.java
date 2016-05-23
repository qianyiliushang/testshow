package com.zombie.test;


import com.zombie.proto.test.RegisterProto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by chenpengpeng on 16/5/23.
 */


public class TestProto {

    @Test
    public void test() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8804/reg/proto");
        RegisterProto.RegisterRequest request = RegisterProto.RegisterRequest.newBuilder().setNick("zombie")
                .setPhoneNumber("hhh").build();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(request.toByteArray());
        InputStreamEntity inputStreamEntity = new InputStreamEntity(inputStream);
        httpPost.addHeader("Content-Type", "application/x-protobuf");
        httpPost.addHeader("Accept", "application/x-protobuf");
        httpPost.setEntity(inputStreamEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity2 = response.getEntity();

            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            entity2.writeTo(buf);
            System.out.println(new String(buf.toByteArray()) + "#################");
            RegisterProto.RegisterResponse protoResponse = RegisterProto.RegisterResponse.parseFrom(buf.toByteArray());
            System.out.println(protoResponse);
        } finally {
            response.close();
        }
    }
}
