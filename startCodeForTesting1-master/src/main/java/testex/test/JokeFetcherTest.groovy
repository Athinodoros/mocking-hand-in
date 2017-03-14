package testex.test

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import testex.IDateFormatter
import testex.JokeFetcher
import testex.factory.Chuck
import testex.factory.Educational
import testex.factory.IJokeFetcher
import testex.factory.IJokeFetcherFactory
import testex.factory.Moma
import testex.factory.Tambal
import org.mockito.Mock;
import static org.hamcrest.CoreMatchers.*
import static org.mockito.Mockito.*
import static org.mockito.Mockito.verify;

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
        when(fetcherFactory.getAvailableTypes()).thenReturn(types);
        jfm = new JokeFetcher(iDateface, fetcherFactory);
    }

    @Test
    public void getAvailableTypesTest() throws Exception {
        List<String> types = fetcherFactory.getAvailableTypes();
        assertEquals(4, types.size());
        assertThat(factory.getAvailableTypes, containsInAnyOrder(equalTo("EduJoke"), equalTo("ChuckNorris"), equalTo("Moma"), equalTo(Tambal)))
        verify(p, times(1)).print("aaa you Won the Game, with: 2");
    }

    @Test
    public void getAvialTest() {
        assertEquals("The result was ok", Arrays.asList("eduprog", "chucknorris", "moma", "tambal"), fetcher.getAvailableTypes());

    }
}
