package ru.stqa.pft.rest;

import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckBugStatusTests extends TestBase {
    @BeforeClass
    public void init() {
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    @Test
    public void testCreateStateIsOpen() throws IOException {
        Issue newIssue = new Issue().withSubject("Test issue123").withDescription("New test issue123").withStateName("Resolved");
        int issueId = createIssue(newIssue);
        //System.out.println(issueId);
        skipIfNotFixed(issueId);
        System.out.println("Hello, world!");
    }
}
