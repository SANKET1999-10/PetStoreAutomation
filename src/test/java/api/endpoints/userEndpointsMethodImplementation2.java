package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
// import api.payload.User;
import io.restassured.RestAssured;

public class userEndpointsMethodImplementation2 {

	// method created for getting url's from property file
	static ResourceBundle getURL () {
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");  // Load properties file
		return routes;
	}
	
    public static Response Createuser(User payload) {
    	
    	String post_url= getURL().getString("post_url");
    	
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
        .when()
        .post(post_url);
        
        return response;
    }
    
    public static Response Readeuser(String username) {
    	
    	String get_url= getURL().getString("get_url");
    	
        Response response = given()
            .pathParam("username", username)
        .when()
        .get(get_url);
        
        return response;
    }
    
    public static Response Updateeuser(String username, User payload) {
    	
    	String update_url= getURL().getString("update_url");
    	
        Response response = given()
        		.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .pathParam("username", username)
        .when()
        .put(update_url);
        
        return response;
    }
    
   public static Response Deleteeuser(String username) {
    	
	   String delete_url= getURL().getString("delete_url");
	   
        Response response = given()
            .pathParam("username", username)
        .when()
        .delete(delete_url);
        
        return response;
    }
    
    
}