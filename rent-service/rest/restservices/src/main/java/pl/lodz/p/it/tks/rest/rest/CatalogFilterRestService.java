package pl.lodz.p.it.tks.rest.rest;

import pl.lodz.p.it.tks.rest.agregates.CatalogRestFilterAdapter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
@Path("api")
public class CatalogFilterRestService {

    @Inject
    private CatalogRestFilterAdapter catalogRestFilterAdapter;

    @GET
    @Path("/catalogs/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterCatalogs(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogRestFilterAdapter.filterCatalogs(catalogFilter)).build();
    }

    @GET
    @Path("/books/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterBooks(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogRestFilterAdapter.filterBooks(catalogFilter)).build();
    }

    @GET
    @Path("/movies/{filter}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterMovies(@PathParam("filter") String catalogFilter) {
        return Response.ok(catalogRestFilterAdapter.filterMovies(catalogFilter)).build();
    }
}
