package rw.sda.sdahymns.hymn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.constraints.NotNull;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.pojo.HymnUpdatePojo;

import java.util.List;

/**
 * The interface Hymn service.
 */
public interface HymnService {

    /**
     * Create hymn hymn.
     *
     * @param hymnPojo the hymn pojo
     * @return the hymn
     */
    Hymn createHymn(@NotNull HymnPojo hymnPojo);

    /**
     * Create multiple hymns list.
     *
     * @param hymnPojoList the hymn pojo list
     * @return the list
     */
    List<Hymn> createMultipleHymns(@NotNull List<HymnPojo> hymnPojoList);

    /**
     * Update hymn hymn.
     *
     * @param number         the number
     * @param hymnUpdatePojo the hymn update pojo
     * @return the hymn
     * @throws JsonProcessingException the json processing exception
     */
    Hymn updateHymn(@NotNull long number, @NotNull HymnUpdatePojo hymnUpdatePojo) throws JsonProcessingException;

    /**
     * Gets hymn by id.
     *
     * @param id the id
     * @return the hymn by id
     */
    Hymn getHymnById(@NotNull long id);

    /**
     * Gets all hymns.
     *
     * @return the all hymns
     */
    List<Hymn> getAllHymns();

    /**
     * Delete hymn.
     *
     * @param id the id
     */
    void deleteHymn(@NotNull long id);

    /**
     * Search hymn list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    List<Hymn> searchHymn(@NotNull String searchTerm);
}
