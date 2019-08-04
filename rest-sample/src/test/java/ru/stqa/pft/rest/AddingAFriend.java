package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class AddingAFriend {
    @Test
    public void addFriend() {
        String tokenOne = createPlayer("4694a9d07a685705", "com.g5e.playgrounddemo.android");
        System.out.println("tokenOne: " + tokenOne);

        String tokenTwo = createPlayer("4694a9d07a685705", "com.g5e.playgrounddemo.android");
        System.out.println("tokenTwo: " + tokenTwo);

        String PlayerOneId = extractId(tokenOne);
        System.out.println("PlayerOneId: " + PlayerOneId);

        String PlayerOneName = extractName(tokenOne);
        System.out.println("PlayerOneName: " + PlayerOneName);

        String PlayerOneNick = extractNick(tokenOne);
        System.out.println("PlayerOneNick: " + PlayerOneNick);

        System.out.println("------------");

        String PlayerTwoId = extractId(tokenTwo);
        System.out.println("PlayerTwoId: " + PlayerTwoId);

        String PlayerTwoName = extractName(tokenTwo);
        System.out.println("PlayerTwoName: " + PlayerTwoName);

        String PlayerTwoNick = extractNick(tokenTwo);
        System.out.println("PlayerTwoNick: " + PlayerTwoNick);

        sendAndApproveFriendRequest(tokenOne, tokenTwo, PlayerOneId, PlayerTwoId);
        System.out.println("------------");
        
        String info = given().parameters( "fields", "id,name,nick", "gameId", "14", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/getFriends").then().statusCode(200).extract().asString();
        System.out.println(info);
        given().parameters( "fields", "id,name,nick", "gameId", "14", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/getFriends").then().statusCode(200).body("friends.id", contains(PlayerOneId));
    }

    private void sendAndApproveFriendRequest(String tokenOne, String tokenTwo, String playerOneId, String playerTwoId) {
        given().parameters("playerId", playerOneId, "actionSource", "g5Friends", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/addFriend");

        given().parameters("playerId", playerTwoId, "actionSource", "g5Friends", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/addFriend");
    }

    private String extractNick(String token) {
        return given().parameters( "fields", "nick", "accessToken", token)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().statusCode(200).extract().path("nick");
    }

    private String extractName(String tokenOne) {
        return given().parameters( "fields", "name", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().statusCode(200).extract().path("name");
    }

    private String extractId(String tokenOne) {
        return given().parameters( "fields", "id", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().statusCode(200).extract().path("id");
    }

    private String createPlayer(String udid, String xpromoId) {
        return given().parameters("udid", udid, "xpromoId", xpromoId)
                .when().get("https://api.test.pgpl.g5e.com/v2/createPlayer").then().statusCode(200)
                .extract().path("accessToken");
    }
}
