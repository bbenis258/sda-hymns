package rw.sda.sdahymns.hymn.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * The type Hymn verse.
 */
@Data
public class HymnVerse {

    /**
     * The Order.
     */
    @NotNull
    private long order;

    /**
     * The Sub title.
     */
    @NotNull
    private String subTitle;

    /**
     * The Content.
     */
    @NotNull
    @TextIndexed
    private String content;
}
