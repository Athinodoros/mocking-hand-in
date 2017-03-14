package testex.factory;

import java.util.List;

/**
 * Created by Athinodoros on 3/14/2017.
 */
public interface IJokeFetcherFactory {

    List<String> getAvailableTypes();
    List<IJokeFetcher> getJokeFetchers(String jokesToFetch);
}
