package rw.sda.sdahymns.hymn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HYMN_VERSE")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HymnVerse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hymn_verse_generator")
    @SequenceGenerator(name = "hymn_verse_generator", sequenceName = "hymn_verse_seq")
    private long id;

    @NotNull
    private int number;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String verse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hymn_id")
    @JsonIgnore
    private Hymn hymn;
}
