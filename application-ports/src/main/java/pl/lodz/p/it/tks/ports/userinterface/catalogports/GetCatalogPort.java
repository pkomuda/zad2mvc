package pl.lodz.p.it.tks.ports.userinterface.catalogports;

import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import java.util.List;

public interface GetCatalogPort {

    Catalog getCatalog(int id);
    List<Catalog> getCatalogs();
    List<Book> getBooks();
    List<Movie> getMovies();
}
