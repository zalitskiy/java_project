package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class NewRestTests {
    @Test(enabled = false)
    public void makeSureThatGoogleIsUp() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }

    @Test(enabled = false)
    public void createNewPlayer() {
        //Создание нового игрока
        /*given().parameters("udid", "4694a9d07a685705", "xpromoId", "com.g5e.playgrounddemo.android")
                .when().get("https://api.test.pgpl.g5e.com/v2/createPlayer").then()
                .body(containsString("accessToken"));
        //Вытаскивание accessToken
        String token = given().parameters("udid", "4694a9d07a685705", "xpromoId", "com.g5e.playgrounddemo.android")
                .when().get("https://api.test.pgpl.g5e.com/v2/createPlayer").then()
                .extract().path("accessToken");
        System.out.println(token);*/

        String info = given().parameters( "fields", "id,name,creationTime,nick,picture,email,birthday,gender,locale,country,subdivision,city,cityName,isTester,nameIsCustom,passwordIsCustom,consumedProfileRewards,unlockedPictureIds,gameIds,underageConfirmStatus", "accessToken", "0.188864347.xk10JUn9q1FL99rd2bmM")
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().asString();
        System.out.println(info);

        int id = Integer.parseInt(given().parameters( "fields", "id", "accessToken", "0.188864347.xk10JUn9q1FL99rd2bmM")
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("id"));
        System.out.println(id);

        String name = given().parameters( "fields", "name", "accessToken", "0.188864347.xk10JUn9q1FL99rd2bmM")
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("name");
        System.out.println(name);

        int creationTime = given().parameters( "fields", "creationTime", "accessToken", "0.188864347.xk10JUn9q1FL99rd2bmM")
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("creationTime");
        System.out.println(creationTime);

        String nick = given().parameters( "fields", "nick", "accessToken", "0.188864347.xk10JUn9q1FL99rd2bmM")
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("nick");
        System.out.println(nick);
    }

}


