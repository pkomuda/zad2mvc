package com.pas.zad2mvc.controllers;

import com.pas.zad2mvc.model.Rent;
import com.pas.zad2mvc.model.catalogs.Book;
import com.pas.zad2mvc.model.catalogs.Catalog;
import com.pas.zad2mvc.model.catalogs.Movie;
import com.pas.zad2mvc.services.CatalogService;
import com.pas.zad2mvc.services.RentService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class ManagerPageRestController implements Serializable {
    @Inject
    private RentService rentService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<Book> books;
    private List<Movie> movies;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String catalogFilter;
    private String rentFilter;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/zad2mvc/resources/api");

    public String addBook() {
        return "addBook";
    }

    public String addMovie() {
        return "addMovie";
    }

    public String prepareCatalogInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalogId(selectedCatalog.getId());
        if (selectedCatalog instanceof Book) {
            return "editBook";
        } else if (selectedCatalog instanceof Movie) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public String prepareRentsInfo(Catalog selectedCatalog) {
        viewAccessController.setSelectedCatalogId(selectedCatalog.getId());
        return "rentsForCatalog";
    }

    public void filterCatalogs() {
        books = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Book>>() {})
                .stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.toList());
        movies = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Movie>>() {})
                .stream()
                .filter(movie -> movie.getDirector() != null)
                .collect(Collectors.toList());

    }

    public void filterRents() {
        unfinishedRents = rentService.filterUnfinishedRents(rentFilter);
        finishedRents = rentService.filterFinishedRents(rentFilter);
    }

    public void removeCatalog(int id) {

        base.path("/catalog/{id}").resolveTemplate("id",id)
                .request().delete();
        loadData();
//        catalogService.removeCatalog(id);
//        loadData();
    }

    public void removeRent(String rentId) {
        rentService.removeRent(rentId);
        loadData();
    }

    //region getters
    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Rent> getUnfinishedRents() {
        return unfinishedRents;
    }

    public List<Rent> getFinishedRents() {
        return finishedRents;
    }

    public String getCatalogFilter() {
        return catalogFilter;
    }

    public String getRentFilter() {
        return rentFilter;
    }
    //endregion

    //region setters
    public void setCatalogFilter(String catalogFilter) {
        this.catalogFilter = catalogFilter;
    }

    public void setRentFilter(String rentFilter) {
        this.rentFilter = rentFilter;
    }
    //endregion

    @PostConstruct
    public void loadData() {
        books = base.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Book>>() {});
        movies = base.path("movies").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Movie>>() {});
        unfinishedRents = rentService.getUnfinishedRents();
        finishedRents = rentService.getFinishedRents();
    }
}
