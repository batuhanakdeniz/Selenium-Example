package Rest;

import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class restApiTests {

    RestAssured rest = new RestAssured();
    String baseURI = "https://www.hepsiburada.com/"; //This is not given. It's dummy baseURI

    @Test
    public void getAllGrocery(){
        given().
                get("/allGrocery").
        then().
                statusCode(200).
                body("data[0].name",equalTo("apple")).
                body("data[1].name",equalTo("grapes"));
    }

    @Test
    public void getGroceryWithName(){
        String groceryName = "apple";
        given().
                get("/allGrocery/"+groceryName).
                then().
                statusCode(200).
                body("data.price",equalTo(3)).
                body("data.stock",equalTo(100));
    }

    @Test
    public void postProduct(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",4);
        map.put("name","SomeString");
        map.put("price",12.3);
        map.put("stock",3);

        JSONObject request = new JSONObject(map);

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/add").
        then().
                statusCode(200);

    }


    @Test
    public void postFailProductWithRandomData(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",4);
        map.put("name","SomeString");
        map.put("price",12.3);
        map.put("stock",3);
        map.put("stockRandom",123123);

        JSONObject request = new JSONObject(map);

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/add").
        then().
                statusCode(400);

    }
    @Test
    public void postFailProduct(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",4);
        map.put("name","SomeString");
        map.put("price",12.3);
        map.put("stock",3);
        map.put("stockRandom",123123);

        JSONObject request = new JSONObject(map);

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/adds").
        then().statusCode(404);

    }
}
