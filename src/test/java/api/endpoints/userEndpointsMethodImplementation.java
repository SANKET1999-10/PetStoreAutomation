package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
// import api.payload.User;
import io.restassured.RestAssured;

public class userEndpointsMethodImplementation {

    public static Response Createuser(User payload) {
    	
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
        .when()
        .post(Routes.post_url);
        
        return response;
    }
    
    public static Response Readeuser(String username) {
    	
        Response response = given()
            .pathParam("username", username)
        .when()
        .get(Routes.get_url);
        
        return response;
    }
    
    public static Response Updateeuser(String username, User payload) {
    	
        Response response = given()
        		.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
        .when()
        .put(Routes.update_url);
        
        return response;
    }
    
   public static Response Deleteeuser(String username) {
    	
        Response response = given()
            .pathParam("username", username)
        .when()
        .delete(Routes.delete_url);
        
        return response;
    }
    
    
}