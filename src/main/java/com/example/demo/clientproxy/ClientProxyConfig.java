package com.example.demo.clientproxy;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ClientProxyConfig {

    @Value("${users.api.url.v1}")
    private String apiUrl;

    //@Bean
    public UserControllerV1 getUserControllerV1() {
        ResteasyClient client = new ResteasyClientBuilderImpl().build();

        ResteasyWebTarget target = client.target(apiUrl);

        //Returns the proxy
        return target.proxy(UserControllerV1.class);
    }
}