package rw.sda.sdahymns.hymn.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import rw.sda.sdahymns.hymn.pojo.HymnVerseComparator;

import java.util.List;

/**
 * The type Hymn.
 */
@Data
@Builder
@AllArgsConstructor
@Document(collection = "hymn_db")
public class Hymn {

    /**
     * The Number.
     */
    @Indexed(unique = true)
    private long number;

    /**
     * The Title.
     */
    @NotNull
    @TextIndexed
    private String title;

    /**
     * The Hymn content.
     */
    private List<HymnVerse> hymnContent;

    /**
     * Gets hymn content.
     *
     * @return the hymn content
     */
    public List<HymnVerse> getHymnContent() {
        hymnContent.sort(new HymnVerseComparator());
        return hymnContent;
    }
}
