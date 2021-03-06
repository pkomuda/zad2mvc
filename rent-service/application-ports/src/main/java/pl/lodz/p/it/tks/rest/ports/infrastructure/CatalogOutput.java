package pl.lodz.p.it.tks.rest.ports.infrastructure;

import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.BookEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.CatalogEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.MovieEntity;
import pl.lodz.p.it.tks.rest.adapters.datamodel.catalogs.NoCatalogEntity;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.Movie;
import pl.lodz.p.it.tks.rest.domainmodel.catalogs.NoCatalog;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

@Named
@Dependent
public class CatalogOutput {

    public CatalogEntity convert(Catalog catalog) {
        if (catalog instanceof Book) {
            return new BookEntity(
                    catalog.getId(),
                    catalog.getTitle(),
                    ((Book) catalog).getAuthor(),
                    catalog.getReleaseYear()
            );
        } else if (catalog instanceof Movie) {
            return new MovieEntity(
                    catalog.getId(),
                    catalog.getTitle(),
                    ((Movie) catalog).getDirector(),
                    catalog.getReleaseYear(),
                    ((Movie) catalog).getFormat()
            );
        } else if (catalog instanceof NoCatalog) {
            return new NoCatalogEntity();
        } else {
            return null;
        }
    }
}
