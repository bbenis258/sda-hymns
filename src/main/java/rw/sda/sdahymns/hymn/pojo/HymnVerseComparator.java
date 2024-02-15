package rw.sda.sdahymns.hymn.pojo;

import rw.sda.sdahymns.hymn.model.HymnVerse;

import java.util.Comparator;

public class HymnVerseComparator implements Comparator<HymnVerse> {
    @Override
    public int compare(HymnVerse a, HymnVerse b) {
        return (int) (a.getOrder() - b.getOrder());
    }
}
