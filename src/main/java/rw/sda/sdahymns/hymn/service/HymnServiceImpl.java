package rw.sda.sdahymns.hymn.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.model.HymnVerse;
import rw.sda.sdahymns.hymn.pojo.HymnComparator;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.pojo.HymnUpdatePojo;
import rw.sda.sdahymns.hymn.repo.HymnRepo;
import rw.sda.sdahymns.hymn.repo.HymnVerseRepo;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public Hymn updateHymn(long number, HymnUpdatePojo hymnUpdatePojo) {
        Hymn hymn = hymnRepo.findById(number).orElseThrow();
        hymn.setTitle(hymnUpdatePojo.getTitle());

        Hymn savedHymn = hymnRepo.save(hymn);

        hymnUpdatePojo.getHymnContent().forEach((subTitle, content) -> {
            HymnVerse hymnVerse = hymnVerseRepo.findHymnVerseByHymnAndSubTitle(savedHymn, subTitle);
            hymnVerse.setContent(content);

            hymnVerseRepo.save(hymnVerse);
        });

        return hymnRepo.findById(number).orElseThrow();
    }

    private Hymn saveSingleHymn(@NotNull HymnPojo hymnPojo) {
        Hymn hymn = Hymn.builder()
                .id(hymnPojo.getNumber())
                .title(hymnPojo.getTitle())
                .build();

        Hymn savedHymn = hymnRepo.save(hymn);

        List<HymnVerse> hymnVerseList = new ArrayList<>();
        hymnPojo.getHymnContent().forEach((subTitle, content) -> {
            HymnVerse hymnVerse = HymnVerse.builder()
                    .hymn(savedHymn)
                    .subTitle(subTitle)
                    .content(content)
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
        hymns.sort(new HymnComparator());
        return hymns;
    }
}
