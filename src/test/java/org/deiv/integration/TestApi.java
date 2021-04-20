package org.deiv.integration;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.deiv.application.dto.DepositDto;
import org.deiv.application.dto.TransferDto;
import org.deiv.application.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;

@MicronautTest
public class TestApi {

    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testApi() {
        UserDto addAccountDto = new UserDto();
        addAccountDto.setName("testUser");

        HttpResponse response;
        String walletResponse;

        /*
         * User creation
         */
        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/user", addAccountDto));

        addAccountDto.setName("testUser");


        /*
         * Wallet creation
         */
        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/wallet", addAccountDto));

        Assertions.assertEquals(200, response.code());

        /*
         * Wallet get
         */
        walletResponse = client
            .toBlocking()
            .retrieve(HttpRequest.GET("/wallet/testUser"));

        Assertions.assertEquals("{\"id\":0,\"number\":0,\"userId\":0,\"balance\":0}", walletResponse);

        /*
         * Deposit
         */
        DepositDto deposit = new DepositDto().amount(new BigDecimal(100)).userName("testUser");

        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/wallet/deposit", deposit));

        Assertions.assertEquals(200, response.code());

        walletResponse = client
            .toBlocking()
            .retrieve(HttpRequest.GET("/wallet/testUser"));

        Assertions.assertEquals("{\"id\":0,\"number\":0,\"userId\":0,\"balance\":100}", walletResponse);

        /*
         * Transfer
         */
        TransferDto transferDto = new TransferDto()
            .amount(new BigDecimal(10))
            .userName("testUser")
            .destUserName("testUser2");
        UserDto addAccountDto2 = new UserDto();
        addAccountDto2.setName("testUser2");

        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/user", addAccountDto2));

        Assertions.assertEquals(200, response.code());

        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/wallet", addAccountDto2));

        Assertions.assertEquals(200, response.code());

        response = client
            .toBlocking()
            .exchange(HttpRequest.POST("/wallet/transfer", transferDto));

        Assertions.assertEquals(200, response.code());

        walletResponse = client
            .toBlocking()
            .retrieve(HttpRequest.GET("/wallet/testUser"));

        Assertions.assertEquals("{\"id\":0,\"number\":0,\"userId\":0,\"balance\":90}", walletResponse);

        walletResponse = client
            .toBlocking()
            .retrieve(HttpRequest.GET("/wallet/testUser2"));

        Assertions.assertEquals("{\"id\":1,\"number\":1,\"userId\":1,\"balance\":10}", walletResponse);
    }

}
