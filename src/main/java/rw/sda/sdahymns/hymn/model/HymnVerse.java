package rw.sda.sdahymns.hymn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Entity
//@Table(name = "HYMN_VERSE")
@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class HymnVerse {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String subTitle;

    @NotNull
//    @Column(columnDefinition = "TEXT")
    private String content;

    //    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hymn_id")
    @JsonIgnore
    private Hymn hymn;
}
