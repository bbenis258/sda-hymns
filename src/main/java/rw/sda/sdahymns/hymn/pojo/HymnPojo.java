package rw.sda.sdahymns.hymn.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class HymnPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private long number;

    private String title;

    private String firstVerse;

    private String firstChorus;

    private String secondChorus;

    private Map<Integer, String> otherVerses;
}
