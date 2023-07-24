package rw.sda.sdahymns.hymn.service;

import jakarta.validation.constraints.NotNull;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;

import java.util.List;

public interface HymnService {

    Hymn createHymn(@NotNull HymnPojo hymnPojo);

    List<Hymn> createMultipleHymns(@NotNull List<HymnPojo> hymnPojoList);

    Hymn getHymnById(@NotNull long id);

    List<Hymn> getAllHymns();
}
