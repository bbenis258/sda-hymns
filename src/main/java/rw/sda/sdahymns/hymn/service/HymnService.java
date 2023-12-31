package rw.sda.sdahymns.hymn.service;

import jakarta.validation.constraints.NotNull;
import rw.sda.sdahymns.hymn.model.Hymn;

import java.util.List;

public interface HymnService {

    /*Hymn createHymn(@NotNull HymnPojo hymnPojo);

    List<Hymn> createMultipleHymns(@NotNull List<HymnPojo> hymnPojoList);

    Hymn updateHymn(@NotNull long number, @NotNull HymnUpdatePojo hymnUpdatePojo) throws JsonProcessingException;*/

    Hymn getHymnById(@NotNull long id);

    List<Hymn> getAllHymns();

    /*void deleteHymn(@NotNull long id);*/

    List<Hymn> searchHymn(@NotNull String searchTerm);
}
