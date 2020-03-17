package pl.lodz.p.it.tks.domainmodel.catalogs;

import lombok.ToString;

@ToString(callSuper = true, includeFieldNames = false)
public class NoCatalog extends Catalog {

    public NoCatalog() {
        super(0, "", 0);
    }
}
