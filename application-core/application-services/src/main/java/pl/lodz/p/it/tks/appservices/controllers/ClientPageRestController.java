package pl.lodz.p.it.tks.appservices.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.RentService;
import pl.lodz.p.it.tks.domainmodel.Rent;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Catalog;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

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
public @Data class ClientPageRestController implements Serializable {

    @Inject
    private RentService rentService;
    @Inject
    private ViewAccessController viewAccessController;
    @Inject
    private LoginController loginController;
    private List<Book> books;
    private List<Movie> movies;
    private List<Rent> unfinishedRents;
    private List<Rent> finishedRents;
    private String catalogFilter;
    private String rentFilter;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/tkslab/resources/api");

    public String prepareRentInfo(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            viewAccessController.setSelectedCatalog(catalog);
            return "addRent";
        } else {
            return "clientPageRest.xhtml";
        }
    }

    public void filterCatalogs() {
        books = base.path("books/{filter}")
                .resolveTemplate("filter", catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Book>>() {})
                .stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.toList());
        movies = base.path("catalogs/{filter}")
                .resolveTemplate("filter", catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Movie>>() {})
                .stream()
                .filter(movie -> movie.getDirector() != null && movie.getFormat() != null)
                .collect(Collectors.toList());
    }

    public void filterRentsForClient() {
        unfinishedRents = rentService.filterUnfinishedRentsForClient(loginController.getUsername(), rentFilter);
        finishedRents = rentService.filterFinishedRentsForClient(loginController.getUsername(), rentFilter);
    }

    public void finishRent(String rentId) {
        rentService.finishRent(rentId);
        loadData();
    }

    public String getCatalogStatus(Catalog catalog) {
        if (rentService.getUnfinishedRentsForCatalog(catalog.getId()).isEmpty()) {
            return "Free";
        } else {
            return "Rented";
        }
    }

    @PostConstruct
    public void loadData() {
        books = base.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        movies = base.path("movies").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        unfinishedRents = rentService.getUnfinishedRentsForClient(loginController.getUsername());
        finishedRents = rentService.getFinishedRentsForClient(loginController.getUsername());
    }
}