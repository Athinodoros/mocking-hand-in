package testex.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Athinodoros on 3/14/2017.
 */
public class JokeFetcherFactory implements IJokeFetcherFactory {
    private final List<String> availableTypes = Arrays.asList("eduprog", "chucknorris", "moma", "tambal");
    @Override
    public List<String> getAvailableTypes() {
        return availableTypes;
    }

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        List<IJokeFetcher> JokeFetchersList = new ArrayList<>();
        String[] tokens = jokesToFetch.split(",");
        for (String token : tokens) {
            switch (token) {
                case "eduprog":
                    JokeFetchersList.add(new Educational());
                    break;
                case "chucknorris":
                    JokeFetchersList.add(new Chuck());
                    break;
                case "moma":
                    JokeFetchersList.add(new Moma());
                    break;
                case "tambal":
                    JokeFetchersList.add(new Tambal());
                    break;
                default:
                    break;
            }
        }
        return JokeFetchersList;
    }
}
