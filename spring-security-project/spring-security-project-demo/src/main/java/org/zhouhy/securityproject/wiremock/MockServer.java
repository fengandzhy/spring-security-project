package org.zhouhy.securityproject.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;


public class MockServer {
    public static void main(String[] args) {
        WireMock.configureFor(8062); //定义端口
        WireMock.removeAllMappings(); //清空之前的配置
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/order/1"))
                .willReturn(WireMock.aResponse().withBody("{\"id\":1}").withStatus(200)));//伪造一个restful请求


    }
}
