import io.restassured.RestAssured;
import io.restassured.http.ContentType;


import io.restassured.response.Response;
import org.testng.annotations.Test;



import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class trello {
    String key = "28cb5a571f71e9eeadb531e0b9969ad7";
    String token = "04312436e1726025671852722f742ebe32e996da17fe2ad949188b0f7310d2dd";
    String URL = "https://api.trello.com/";

    String boardname;
    String cardname;
    String updatename;
    String idBoards;
    String idList;
    String idcard;


    ArrayList<String> cardID_list = new ArrayList<>();

    @Test
     public void CreateBoard(){

         RestAssured.baseURI = URL;
         System.out.println(URL);
         Response response = (Response) given()
                 .contentType(ContentType.JSON)
                 .log().all()
                 .queryParam("key", key)
                 .queryParam("token",token)
                 .queryParam("name",boardname)
                 .when().post("/1/boards/");
        System.out.println(response);
        idBoards = response.body().jsonPath().getString("id");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test
    public void CreateCart(){
        Response response = null;

        for (int i = 0; i < 2; i++) {
            RestAssured.baseURI = URL;

            response = (Response) given().log().all()
                    .contentType(ContentType.JSON)
                    .queryParam("name", cardname)
                    .queryParam("key", key)
                    .queryParam("token", token)
                    .queryParam("idBoard",idBoards)
                    .post("/cards/");

            idList = response.body().jsonPath().getString("id");

        }
    }
    @Test
    public void updateBoard(){
        RestAssured.baseURI = URL;
        System.out.println(URL);
        Response response = (Response) given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token",token)
                .queryParam("name",updatename)
                .when().put("/cards/id{id}");

    }

    @Test
    public void DeleteBoard(){

        RestAssured.baseURI = URL;
        System.out.println(URL);
        Response response = (Response) given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("name",boardname)
                .queryParam("key", key)
                .queryParam("token",token)
                .delete("https://api.trello.com/1/boards/" + idBoards);

    }

}
