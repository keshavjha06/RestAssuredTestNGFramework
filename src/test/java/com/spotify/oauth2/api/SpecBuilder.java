package com.spotify.oauth2.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import com.spotify.oauth2.utils.Secrets;

import static com.spotify.oauth2.api.Route.BASE_PATH;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(Secrets.baseUri()).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri(Secrets.accountBaseUri()).
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }



    public static ResponseSpecification getResponseSpec() {
            return new ResponseSpecBuilder().
                    log(LogDetail.ALL).
                    build();
        }
    }
