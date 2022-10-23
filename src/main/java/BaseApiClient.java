import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.Map;

public class BaseApiClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public Response post(String endpoint, Object body){

        return request(body).post(endpoint);
    }
    public Response patch(String endpoint, Object body){

        return request(body).patch(endpoint);
    }
    public Response patch(String endpoint, Map<String, Object> headers, Object body){

        return request(headers, body).patch(endpoint);
    }
    public Response post(String endpoint, Map<String, Object> headers, Object body){

        return request(headers, body).post(endpoint);
    }
    public Response delete(String endpoint, Map<String, Object> headers){

        return request(headers).delete(endpoint);
    }
    public Response get(String endpoint, Map<String, Object> headers){

        return request(headers).get(endpoint);
    }
    public Response get (String endpoint){

        return request().get(endpoint);
    }
    private RequestSpecification request(Object body){

        return baseRequest().body(body);
    }
    private RequestSpecification request(){return baseRequest();
    }
    private RequestSpecification request(Map<String, Object> headers, Object body){

        return baseRequest().headers(headers).body(body);
    }
    private RequestSpecification request(Map<String, Object> headers){

        return baseRequest().headers(headers);
    }
    private RequestSpecification baseRequest(){

        return RestAssured.given(config());
    }
    private RequestSpecification config(){
        RestAssured.useRelaxedHTTPSValidation();
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(BASE_URL)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

}
