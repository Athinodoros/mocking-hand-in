
package testex;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.response.ExtractableResponse;
import testex.factory.IJokeFetcher;
import testex.factory.IJokeFetcherFactory;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Class used to fetch jokes from a number of external joke API's
 */
public class JokeFetcher {
  
  /**
   * These are the valid types that can be used to indicate required jokes
   * eduprog: Contains joke related to programming and education. API only returns a new value each hour
   * chucknorris: Fetch a chucknorris joke. Not always political correct ;-)
   * moma: Fetch a "MOMA" joke. Defenitely never political correct ;-)
   * tambal: Just random jokes
   */

  private IJokeFetcherFactory jokeFetcherFactory;
  private IDateFormatter iDatef;

  JokeFetcher(IDateFormatter iface, IJokeFetcherFactory factory) {
    this.iDatef = iface;
    this.jokeFetcherFactory = factory;
  }
  private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");


  public Joke getEducationalProgrammingJoke(){
    try{
    ExtractableResponse res =  given().get("http://jokes-plaul.rhcloud.com/api/joke").then().extract();
    String joke = res.path("joke");
    String reference = res.path("reference");
    return new Joke(joke,reference);
    }catch(Exception e){
      return null;
    }
  }

  public Joke getChuckNorrisJoke(){
    try{
    String joke  = given().get("http://api.icndb.com/jokes/random").path("value.joke");
    return new Joke(joke,"http://api.icndb.com/");
    }catch(Exception e){
      return null;
    }
  }

  public Joke getYoMommaJoke(){
    try{
    //API does not set response type to JSON, so we have to force the response to read as so
    String joke = given().get("http://api.yomomma.info/").andReturn().jsonPath().getString("joke");
    return new Joke(joke,"http://api.yomomma.info/");
    }catch(Exception e){
      return null;
    }
  }

  public Joke getTambalJoke(){
    try{
    String joke  = given().get("http://tambal.azurewebsites.net/joke/random").path("joke");
    return new Joke(joke,"http://tambal.azurewebsites.net/joke/random");
    }catch(Exception e){
      return null;
    }
  }
  
  /**
   * The valid string values to use in a call to getJokes(..)
   * @return All the valid strings that can be used
   */

  boolean isStringValid(String jokeTokens){
    String[] tokens = jokeTokens.split(",");
      for(String token: tokens){
      if(!availableTypes.contains(token)){
        return false;
      }
    }
    return true;
  }

  public Jokes getJokes(String jokesToFetch, String timeZone) throws JokeException, InvalidTimeZomeException {
    if (!isStringValid(jokesToFetch)) {
      throw new JokeException("Inputs (jokesToFetch) contain types not recognized");
    }
    Jokes jokes = new Jokes();
    for (IJokeFetcher fetcher : jokeFetcherFactory.getJokeFetchers(jokesToFetch)) {
      jokes.addJoke(fetcher.getJoke());
    }

    String timeZoneString = iDatef.getFormattedDate(timeZone, new Date());
    jokes.setTimeZoneString(timeZoneString);
    return jokes;
  }
  

}
