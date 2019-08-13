package com.dv2.stockservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class StockControllerTest {

    @BeforeAll
    public static void startServer() {
        LambdaApp.main(new String[0]);
    }

    @Test
    public void getStockBySymbol() {
        when()
                .get("/stock/{symbol}", "TSLA")
                .then()
                .statusCode(200);
    }
}
