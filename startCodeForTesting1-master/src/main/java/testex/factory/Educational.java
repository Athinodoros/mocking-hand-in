package testex.factory;

import com.jayway.restassured.response.ExtractableResponse;
import testex.Joke;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Athinodoros on 3/14/2017.
 */
public class Educational implements IJokeFetcher {
    @Override
    public Joke getJoke() {
        try{
            ExtractableResponse res =  given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
            String joke = res.path("joke");
            String reference = res.path("reference");
            return new Joke(joke,reference);
        }catch(Exception e){
            return null;
        }
    }
}
