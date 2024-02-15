package rw.sda.sdahymns.hymn.pojo;

import rw.sda.sdahymns.hymn.model.Hymn;

import java.util.Comparator;

public class HymnComparator implements Comparator<Hymn> {
    @Override
    public int compare(Hymn a, Hymn b) {
        return (int) (a.getNumber() - b.getNumber());
    }
}
