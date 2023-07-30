package rw.sda.sdahymns.hymn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.model.HymnVerse;

@Repository
public interface HymnVerseRepo extends JpaRepository<HymnVerse, Long> {

    HymnVerse findHymnVerseByHymnAndNumber(Hymn hymn, int number);
}
