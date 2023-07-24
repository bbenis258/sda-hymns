package rw.sda.sdahymns.hymn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.sda.sdahymns.hymn.model.Hymn;

@Repository
public interface HymnRepo extends JpaRepository<Hymn, Long> {
}
