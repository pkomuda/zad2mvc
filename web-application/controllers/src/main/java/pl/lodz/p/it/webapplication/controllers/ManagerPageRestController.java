package pl.lodz.p.it.webapplication.controllers;

import lombok.Data;
import pl.lodz.p.it.tks.appservices.services.rent.RentCrudService;
import pl.lodz.p.it.tks.appservices.services.rent.RentFilterService;
import pl.lodz.p.it.tks.appservices.services.rent.RentGetService;
import pl.lodz.p.it.tks.ports.userinterface.controller.converters.RentConverter;
import pl.lodz.p.it.model.RentWeb;
import pl.lodz.p.it.model.catalogs.BookWeb;
import pl.lodz.p.it.model.catalogs.CatalogWeb;
import pl.lodz.p.it.model.catalogs.MovieWeb;

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
public @Data class ManagerPageRestController implements Serializable {

    @Inject
    private RentCrudService rentCrudService;
    @Inject
    private RentGetService rentGetService;
    @Inject
    private RentFilterService rentFilterService;
    @Inject
    private ViewAccessController viewAccessController;
    private List<BookWeb> books;
    private List<MovieWeb> movies;
    private List<RentWeb> unfinishedRents;
    private List<RentWeb> finishedRents;
    private String catalogFilter;
    private String rentFilter;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/tkslab/resources/api");

    public String addBook() {
        return "addBook";
    }

    public String addMovie() {
        return "addMovie";
    }

    public String prepareCatalogInfo(CatalogWeb selectedCatalog) {
        viewAccessController.setSelectedCatalog(selectedCatalog);
        if (selectedCatalog instanceof BookWeb) {
            return "editBook";
        } else if (selectedCatalog instanceof MovieWeb) {
            return "editMovie";
        } else {
            return "";
        }
    }

    public String prepareRentsInfo(CatalogWeb selectedCatalog) {
        viewAccessController.setSelectedCatalog(selectedCatalog);
        return "rentsForCatalog";
    }

    public void filterCatalogs() {
        books = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<BookWeb>>() {})
                .stream()
                .filter(book -> book.getAuthor() != null)
                .collect(Collectors.toList());
        movies = base.path("catalogs/{filter}")
                .resolveTemplate("filter",catalogFilter)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<MovieWeb>>() {})
                .stream()
                .filter(movie -> movie.getDirector() != null && movie.getFormat() != null)
                .collect(Collectors.toList());
    }

    public void filterRents() {
        unfinishedRents = RentConverter.domainToWebRents(rentFilterService.filterUnfinishedRents(rentFilter));
        finishedRents = RentConverter.domainToWebRents(rentFilterService.filterFinishedRents(rentFilter));
    }

    public void removeCatalog(int id) {
        base.path("/catalog/{id}")
                .resolveTemplate("id", id)
                .request().delete();
        loadData();
    }

    public void removeRent(String rentId) {
        rentCrudService.removeRent(rentId);
        loadData();
    }

    @PostConstruct
    public void loadData() {
        books = base.path("books").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        movies = base.path("movies").request(MediaType.APPLICATION_JSON).get(new GenericType<>() {});
        unfinishedRents = RentConverter.domainToWebRents(rentGetService.getUnfinishedRents());
        finishedRents =RentConverter.domainToWebRents(rentGetService.getFinishedRents());
    }
}
