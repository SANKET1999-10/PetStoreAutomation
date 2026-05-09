package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndpointsMethodImplementation;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority=1, dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser (String UserID, String username, String fname, String lname, String useremail, String pwd, String ph) {
		
		User userPayload = new User ();
		
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(username);
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response= userEndpointsMethodImplementation.Createuser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	
	public void testDeleteUserByName (String userName) {
		
		Response response = userEndpointsMethodImplementation.Deleteeuser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
