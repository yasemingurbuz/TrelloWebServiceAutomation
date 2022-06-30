import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class trello {
    String key = "28cb5a571f71e9eeadb531e0b9969ad7";
    String token = "04312436e1726025671852722f742ebe32e996da17fe2ad949188b0f7310d2dd";
    String URL = "https://api.trello.com/";
    String boardName = "ProjectBoardName";
    String cardName = "CardName";
    String updateName = "ProjectUpdatedName";
    String idList = "62b9e09c749ac538c59e987e";
    String idBoards = "62b9dfae60286a0ea3863ff1";
    String deleteBoard = "62b9e5f96fdf278bed8e88b5";



    @Test
     public void CreateBoard() throws InterruptedException {

         RestAssured.baseURI = URL;
         System.out.println(URL);
         Response response = (Response) given()
                 .contentType(ContentType.JSON)
                 .log()
                 .all()
                 .queryParam("key", key)
                 .queryParam("token",token)
                 .queryParam("name",boardName)
                 .when()
                 .post("/1/boards/")
                 .then()
                 .assertThat()
                 .statusCode(200)
                 .extract()
                 .response();
        assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

    @Test
    public void CreateCart(){
        Response response = null;

        for (int i = 0; i < 2; i++) {
            RestAssured.baseURI = URL;
            response = (Response) given().log().all()
                    .contentType(ContentType.JSON)
                    .queryParam("name", cardName)
                    .queryParam("key", key)
                    .queryParam("token", token)
                    .queryParam("idList",idList)
                    .post("/1/cards/")
                    .then()
                    .assertThat()
                    .statusCode(200)
                    .extract()
                    .response();
        }
    }

    @Test
    public void updateBoard(){
        RestAssured.baseURI = URL;
        System.out.println(URL);
        String request = "/1/boards/" + idBoards;
        Response response = (Response) given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("key", key)
                .queryParam("token",token)
                .queryParam("name",updateName)
                .put(request)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
    }

    @Test
    public void DeleteBoard(){

        RestAssured.baseURI = URL;
        System.out.println(URL);
        Response response = (Response) given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("name",boardName)
                .queryParam("key", key)
                .queryParam("token",token)
                .delete("https://api.trello.com/1/boards/" + deleteBoard);

    }

}
