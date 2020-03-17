package pl.lodz.p.it.tks.appservices.controllers.catalogs;

import lombok.Data;
import pl.lodz.p.it.tks.domainmodel.catalogs.Book;
import pl.lodz.p.it.tks.domainmodel.catalogs.Movie;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;

@Named
@ConversationScoped
public @Data class AddCatalogRestController implements Serializable {

    @Inject
    private Conversation conversation;
    private int id;
    private String title;
    private String author;
    private int releaseYear;
    private String director;
    private String format;
    private Client client = ClientBuilder.newClient();
    private WebTarget base = client.target("https://localhost:8181/tkslab/resources/api");

    public String addBook() {
        beginConversation();
        return "addBook";
    }

    public String addMovie() {
        beginConversation();
        return "addMovie";
    }

    public String confirmBook() {
        base.path("book")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new Book(id, title, author, releaseYear)), Response.class);
        endConversation();
        return "manager";
    }

    public String confirmMovie() {
        base.path("movie")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.json(new Movie(id, title, director, releaseYear, format)), Response.class);
        endConversation();
        return "manager";
    }

    public String cancel() {
        return "cancel";
    }

    public String cancelConfirm() {
        return "cancelConfirm";
    }

    private void beginConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
        conversation.begin();
    }

    private void endConversation() {
        conversation.end();
    }
}
