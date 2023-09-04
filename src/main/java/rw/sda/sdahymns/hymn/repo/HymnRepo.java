package rw.sda.sdahymns.hymn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rw.sda.sdahymns.hymn.model.Hymn;

import java.util.List;

@Repository
public interface HymnRepo extends JpaRepository<Hymn, Long> {

    @Query("SELECT h FROM Hymn h WHERE lower(h.title) LIKE %?1%")
    List<Hymn> searchHymn(String searchTerm);
}
