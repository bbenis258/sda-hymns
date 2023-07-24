package rw.sda.sdahymns.hymn.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.model.HymnVerse;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.repo.HymnRepo;
import rw.sda.sdahymns.hymn.repo.HymnVerseRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class HymnServiceImpl implements HymnService {

    private final HymnRepo hymnRepo;

    private final HymnVerseRepo hymnVerseRepo;

    @Autowired
    public HymnServiceImpl(HymnRepo hymnRepo, HymnVerseRepo hymnVerseRepo) {
        this.hymnRepo = hymnRepo;
        this.hymnVerseRepo = hymnVerseRepo;
    }

    @Override
    public Hymn createHymn(HymnPojo hymnPojo) {
        return this.saveSingleHymn(hymnPojo);
    }

    @Override
    public List<Hymn> createMultipleHymns(List<HymnPojo> hymnPojoList) {
        List<Hymn> hymns = new ArrayList<>();
        hymnPojoList.stream().forEach(hymnPojo -> {
            Hymn hymn = this.saveSingleHymn(hymnPojo);
            hymns.add(hymn);
        });
        return hymns;
    }

    private Hymn saveSingleHymn(@NotNull HymnPojo hymnPojo) {
        Hymn hymn = Hymn.builder()
                .id(hymnPojo.getNumber())
                .title(hymnPojo.getTitle())
                .firstVerse(hymnPojo.getFirstVerse())
                .firstChorus(hymnPojo.getFirstChorus())
                .secondChorus(hymnPojo.getSecondChorus())
                .build();

        Hymn savedHymn = hymnRepo.save(hymn);

        List<HymnVerse> hymnVerseList = new ArrayList<>();
        Hymn finalHymn = savedHymn;
        hymnPojo.getOtherVerses().forEach((number, verse) -> {
            HymnVerse hymnVerse = HymnVerse.builder()
                    .hymn(finalHymn)
                    .number(number)
                    .verse(verse)
                    .build();

            hymnVerseList.add(hymnVerse);
        });

        savedHymn.setOtherVerses(hymnVerseList);

        hymnRepo.save(savedHymn);

        return hymnRepo.findById(hymnPojo.getNumber()).orElseThrow();
    }

    @Override
    public Hymn getHymnById(long id) {
        return hymnRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Hymn> getAllHymns() {
        List<Hymn> hymns = hymnRepo.findAll();
        return hymns;
    }
}
