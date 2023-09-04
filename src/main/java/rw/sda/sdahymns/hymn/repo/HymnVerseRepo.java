package rw.sda.sdahymns.hymn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.model.HymnVerse;

import java.util.List;

@Repository
public interface HymnVerseRepo extends JpaRepository<HymnVerse, Long> {

    HymnVerse findHymnVerseByHymnAndSubTitle(Hymn hymn, String subTitle);

    @Query("SELECT h.hymn FROM HymnVerse h WHERE lower(h.content) LIKE %?1%")
    List<Hymn> searchHymn(String searchTerm);
}
