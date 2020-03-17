package pl.lodz.p.it.tks.adapters.datamodel.catalogs;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity(name = "Movie")
@EqualsAndHashCode(callSuper = true)
public @Data class MovieEntity extends CatalogEntity {

    private String director;
    private String format;

    public MovieEntity() {
        super();
    }

    public MovieEntity(int id, String title, String director, int releaseYear, String format) {
        super(id, title, releaseYear);
        this.director = director;
        this.format = format;
    }
}
