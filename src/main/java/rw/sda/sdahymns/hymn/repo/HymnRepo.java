package rw.sda.sdahymns.hymn.repo;

import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import rw.sda.sdahymns.hymn.model.Hymn;

import java.util.List;
import java.util.Optional;

/**
 * The interface Hymn repo.
 */
public interface HymnRepo extends MongoRepository<Hymn, String> {

    /**
     * Find hymn by number optional.
     *
     * @param number the number
     * @return the optional
     */
    Optional<Hymn> findHymnByNumber(long number);

    /**
     * Find all by list.
     *
     * @param textCriteria the text criteria
     * @return the list
     */
    List<Hymn> findAllBy(TextCriteria textCriteria);

    /**
     * Delete hymn by number.
     *
     * @param number the number
     */
    void deleteHymnByNumber(long number);
}
