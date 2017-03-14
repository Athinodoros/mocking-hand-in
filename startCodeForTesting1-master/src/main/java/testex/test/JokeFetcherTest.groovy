package testex.test

import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import testex.IDateFormatter
import testex.InvalidTimeZomeException
import testex.Joke
import testex.JokeFetcher
import testex.factory.Chuck
import testex.factory.Educational
import testex.factory.IJokeFetcher
import testex.factory.IJokeFetcherFactory
import testex.factory.JokeFetcherFactory
import testex.factory.Moma
import testex.factory.Tambal

import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.arrayWithSize;
import org.junit.Assert;
import static org.mockito.BDDMockito.given;


/**
 * Created by Athinodoros on 3/14/2017.
 */
@RunWith(MockitoJUnitRunner.class)
class JokeFetcherTest {

    @Mock
    IDateFormatter iDateface;
    @Mock
    Moma moma;
    @Mock
    Chuck chuck;
    @Mock
    Educational edu;
    @Mock
    Tambal tambal;
    @Mock
    JokeFetcher jfm;
    @Mock
    IJokeFetcherFactory fetcherFactory

    @Before
    public void setup() {
        List<IJokeFetcher> fetchers = Arrays.asList(edu, chuck, moma, tambal);
        when(fetcherFactory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        //List<String> types = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        when(fetcherFactory.getAvailableTypes()).thenReturn(types);
        jfm = new JokeFetcher(iDateface, fetcherFactory);
    }

    @Test
    public void getAvailableTypesTest() throws Exception {
        List<String> types = fetcherFactory.getAvailableTypes();

        //assertThat(fetcherFactory.getAvailableTypes(), containsInAnyOrder(Arrays.asList(types)));
        assertThat(fetcherFactory.availableTypes,hasItems(Arrays.asList(types)));
    }

    @Test
    public void getAvailableTypesSize() throws Exception {
        List<String> types = fetcherFactory.getAvailableTypes();
        Assert.assertEquals(4, types.size());
    }

    @Test
    public void testGetAvailableTypes() {
        JokeFetcherFactory ff = new JokeFetcherFactory();
        List<String> expResult = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");
        List<String> result = ff.getAvailableTypes();
        assertEquals(expResult, result);
    }

    @Test
    public void checkIfValidToken() {
        String jokeTokens = "chucknorris";
        boolean check = jfm.isStringValid(jokeTokens);
        assertTrue(check);
    }

    @Test
    public void getJokesMethod() throws InvalidTimeZomeException {

        given(iDateface.getFormattedDate(eq("Europe/Copenhagen"),
                anyObject())).willReturn("13 Mar 2017 05:00 PM");
        Assert.assertThat(jfm.getJokes("eduprog,chucknorris,chucknorris,moma,tambal", "Europe/Copenhagen")
                .getTimeZoneString(), is("13 Mar 2017 05:00 PM"));

    }

    @Test
    public void ChuckNorrisTest() throws Exception {
        given(chuck.getJoke()).willReturn(new Joke("Chuck", ""));
        Assert.assertThat(chuck.getJoke().getJoke(), is("Chuck"));
        Assert.assertThat(chuck.getJoke().getReference(), is(""));
    }

    @Test
    public void MomaTest() throws Exception {
        given(moma.getJoke()).willReturn(new Joke("Moma", ""));
        Assert.assertThat(moma.getJoke().getJoke(), is("Moma"));
        Assert.assertThat(moma.getJoke().getReference(), is(""));
    }

    @Test
    public void TambalTest() throws Exception {
        given(tambal.getJoke()).willReturn(new Joke("Tambal", ""));
        Assert.assertThat(tambal.getJoke().getJoke(), is("Tambal"));
        Assert.assertThat(tambal.getJoke().getReference(), is(""));
    }

    @Test
    public void EduJokeTest() throws Exception {
        given(edu.getJoke()).willReturn(new Joke("Educational", ""));
        Assert.assertThat(edu.getJoke().getJoke(), is("Educational"));
        Assert.assertThat(edu.getJoke().getReference(), is(""));
    }

    @Test
    public void getJokesMethodes() throws InvalidTimeZomeException {

        given(iDateface.getFormattedDate(eq("Europe/Copenhagen"),
                anyObject())).willReturn("9 Mar 1989 05:25 PM");
        Assert.assertThat(jfm.getJokes("eduprog,chucknorris,chucknorris,moma,tambal", "Europe/Copenhagen")
                .getTimeZoneString(), is("9 Mar 1989 05:25 PM"));

        verify(iDateface, times(1)).getFormattedDate(anyString(), any());
        verify(fetcherFactory, times(1)).getJokeFetchers(anyString());
    }

}
