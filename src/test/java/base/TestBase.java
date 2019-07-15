package base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
    public RequestSpecification REQUEST;

    public TestBase() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            //Rest Assured config
            RestAssured.baseURI = props.getProperty("api.uri");
            RestAssured.port = Integer.valueOf(props.getProperty("api.port"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //basic request setting
        REQUEST = RestAssured.given();
    }

    public String generateStringFromResource(String path) throws IOException {

        return new String(Files.readAllBytes(Paths.get(path)));

    }

}
