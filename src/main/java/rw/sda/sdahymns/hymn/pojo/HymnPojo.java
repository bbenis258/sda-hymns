package rw.sda.sdahymns.hymn.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class HymnPojo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long number;

    private String title;

    private Map<Long, Map<String, String>> hymnContent;
}
