package com.academy.api.rest.mobile;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.PrintStream;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class SubscriberXmlTests {
    private static final Logger LOG = LogManager.getLogger(SubscriberXmlTests.class);

    @BeforeSuite
    public void setUp() {
        RestAssured.baseURI = "http://localhost/rest/xml";
        RestAssured.port = 8080;

        config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));
    }

    @Ignore
    @Test
    public void testGet() {
        LOG.info("API-> test subscribers get by id");
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/subscribers/1");
        ResponseBody body = response.body();
        XmlPath xmlPath = body.xmlPath();
        int  id = xmlPath.getInt("subscriber.id");
        String  name = xmlPath.getString("subscriber.firstName");
        int code = response.statusCode();

        Assert.assertEquals(code, 200);
        Assert.assertEquals(id, 1);
        Assert.assertEquals(name, name);
    }
    @Ignore
    @Test
    public void testGetDsl() {
        LOG.info("API-> test subscribers get by id");
        given().log().all()
                .when().get("/subscribers/{id}", 1)
                .then().assertThat()
                .body("subscriber.id", equalTo("1"))
                .body("subscriber.firstName", equalTo("Иван"))
                .body("subscriber.lastName", equalTo("Петров"))
                .body("subscriber.age", equalTo("33"))
                .body("subscriber.gender", equalTo("m"))
                .and().statusCode(200);
    }

    @Test
    public void testGetAll() {
        given().config(config)
                .log().all()
                .when().get("/subscribers")
                .then().assertThat()
                .body("subscribers.subscriber.size()", equalTo(4))
                .and()
                .body("subscribers.subscriber[0].id", equalTo("1"))
                .and()
                .statusCode(200);
    }

    @Ignore
    @Test
    public void testPost() {
        // TODO if exists
        JSONObject json = new JSONObject();
        json.put("firstName", "Vasya"); // Cast
        json.put("lastName", "Pupkin");
        json.put("age", "25");
        json.put("gender", "m");

        given().log().all()
                .header("Content-Type", "application/json")
//                .body("{\"firstName\":\"Olegjan\",\"lastName\":\"Afanasiev\",\"age\":\"36\",\"gender\":\"m\"}")
                .body(json.toJSONString())
                .post("/subscribers")
                .then().assertThat()
                .header("Location", containsString("http://localhost:8080/rest/xml/subscribers/"))
                .statusCode(201);
    }

    @Ignore
    @Test
    public void testUpdate() {
        JSONObject json = new JSONObject();
        json.put("id", "30"); // Cast
        json.put("firstName", "Helen"); // Cast
        json.put("lastName", "Ivanova");
        json.put("age", "18");
        json.put("gender", "f");
        given().log().all()
                .header("Content-Type", "application/json")
//                .body("{\"id\":\"28\",\"firstName\":\"Olegjan\",\"lastName\":\"Afanasiev\",\"gender\":\"m\",\"age\":\"36\"}")
                .body(json.toJSONString())
                .put("/subscribers/30")
                .then().assertThat()
                .body("id", equalTo(30))
                .and()
                .body("lastName", equalTo("Ivanova"))
                .statusCode(200);
    }

    @Ignore
    @Test
    public void testDelete() {
        given().log().all()
                .delete("/subscribers/30")
                .then().assertThat()
                .statusCode(200);
    }
}
