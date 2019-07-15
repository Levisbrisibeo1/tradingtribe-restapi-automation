import base.TestBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

public class SkyBetTradingEngineTests extends TestBase {
    //TASK 1
    @Test
    public void TC01_RetrieveAllFixtures() {

        //TC01 - Retrieve All Fixtures

        Response response = REQUEST.given().when().get("/fixtures").then().statusCode(200).extract().response();
        int size = response.jsonPath().getList("$").size();
        //TC01 - i) Asssert that the number of fixtures is 3
        Assert.assertEquals(size, 3);

        //TC01 - ii) Assert that each of the 3 fixtures has a fixtureId value
        List<String> fixtureIds = response.path("fixtureId");
        for (String fixtureId : fixtureIds) {
            System.out.println(fixtureId);
            Assert.assertNotNull(fixtureId);
        }
    }

    //TASK 2
    @Test
    public void TC02_StoreNewFixture() throws IOException {
        //TC02 - Store a New Fixture

       // Use a Random Integer as Fixture ID and so that there is will not be any conflicts - Series 20-30
        Integer randomFixtureId = new Random().nextInt(21) + 10;
        String jsonBody = generateStringFromResource("src/test/resources/body.json");
        jsonBody = jsonBody.replace("<FIXTUREID>", randomFixtureId.toString());

        given().
                contentType("application/json").
                body(jsonBody).
                when().
                post("/fixture").
                then().
                statusCode(200).
                body(containsString("Fixture has been added"));

        /* TASK 3 - Retrieve the Response as soon as it is available and Asap */

        String fixtureRequest = "/fixture/" + randomFixtureId;
        Response response = REQUEST.given().when().get(fixtureRequest).then().extract().response();

        //TC02 - ii) Assert, within the teams array, that the first object has a teamId of 'HOME'.
        String teamId = response.jsonPath().getString("footballFullState.teams[0].teamId");
        Assert.assertEquals(teamId, "HOME");

    }

    //TASK 4
    @Test
    public void TC03_DeleteNewFixture() throws IOException {
        //TC03 - Create a New Fixture

        // Use a Random Integer as Fixture ID and so that there is will not be any conflicts - Series 30-40

        Integer randomFixtureId = new Random().nextInt(31) + 10;
        String jsonBody = generateStringFromResource("src/test/resources/body.json");
        jsonBody = jsonBody.replace("<FIXTUREID>", randomFixtureId.toString());

        given().
                contentType("application/json").
                body(jsonBody).
                when().
                post("/fixture").
                then().
                statusCode(200).
                body(containsString("Fixture has been added"));

        // TC03 - Delete the Created Feature
        String fixtureRequest = "/fixture/" + randomFixtureId;
        Response response = REQUEST.given().when().delete(fixtureRequest).then().extract().response();
        Assert.assertEquals(response.getBody().asString().toLowerCase(), "fixture has been deleted");

        // TC03 - Try to get the Created Feature and ensure that it is deleted
        Response getresponse = REQUEST.given().when().get(fixtureRequest).then().extract().response();

        // Perform Assertions
        Assert.assertEquals(getresponse.getBody().asString().toLowerCase(), "fixture not found");
        Assert.assertEquals(getresponse.statusCode(), 404);

    }


}