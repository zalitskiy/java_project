package ru.stqa.pft.rest;

import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

public class AddingAFriend {
    @Test
    public void addFriend() {
        String tokenOne = getToken("4694a9d07a685705", "com.g5e.playgrounddemo.android");
        System.out.println(tokenOne);

        String tokenTwo = getToken("4694a9d07a685705", "com.g5e.playgrounddemo.android");
        System.out.println(tokenTwo);

        String PlayerOneId = extractId(tokenOne);
        System.out.println(PlayerOneId);

        String PlayerOneName = extractName(tokenOne);
        System.out.println(PlayerOneName);

        String PlayerOneNick = extractNick(tokenOne);
        System.out.println(PlayerOneNick);

        System.out.println("------------");

        String PlayerTwoId = extractId(tokenTwo);
        System.out.println(PlayerTwoId);

        String PlayerTwoName = extractName(tokenTwo);
        System.out.println(PlayerTwoName);

        String PlayerTwoNick = extractNick(tokenTwo);
        System.out.println(PlayerTwoNick);

        sendAndApproveFriendRequest(tokenOne, tokenTwo, PlayerOneId, PlayerTwoId);
        System.out.println("------------");
        
        String info = given().parameters( "fields", "id,name,nick", "gameId", "14", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/getFriends").then().extract().asString();
        System.out.println(info);
        given().parameters( "fields", "id,name,nick", "gameId", "14", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/getFriends").then().body("friends.id", contains(PlayerOneId));
    }

    private void sendAndApproveFriendRequest(String tokenOne, String tokenTwo, String playerOneId, String playerTwoId) {
        given().parameters("playerId", playerOneId, "actionSource", "g5Friends", "accessToken", tokenTwo)
                .when().get("https://api.test.pgpl.g5e.com/v2/addFriend");

        given().parameters("playerId", playerTwoId, "actionSource", "g5Friends", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/addFriend");
    }

    private String extractNick(String token) {
        return given().parameters( "fields", "nick", "accessToken", token)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("nick");
    }

    private String extractName(String tokenOne) {
        return given().parameters( "fields", "name", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("name");
    }

    private String extractId(String tokenOne) {
        return given().parameters( "fields", "id", "accessToken", tokenOne)
                .when().get("https://api.test.pgpl.g5e.com/v2/getPlayerInfo").then().extract().path("id");
    }

    private String getToken(String udid, String xpromoId) {
        return given().parameters("udid", udid, "xpromoId", xpromoId)
                .when().get("https://api.test.pgpl.g5e.com/v2/createPlayer").then()
                .extract().path("accessToken");
    }
}
