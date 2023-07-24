package rw.sda.sdahymns.hymn.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "HYMN")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Hymn {

    @Id
    private long id;

    @NotNull
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String firstVerse;

    @Column(columnDefinition = "TEXT")
    private String firstChorus;

    @Column(columnDefinition = "TEXT")
    private String secondChorus;

    @OneToMany(mappedBy = "hymn", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HymnVerse> otherVerses = new ArrayList<>();
}
