package rw.sda.sdahymns.hymn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.model.HymnVerse;
import rw.sda.sdahymns.hymn.pojo.HymnComparator;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.pojo.HymnUpdatePojo;
import rw.sda.sdahymns.hymn.repo.HymnRepo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Hymn service.
 */
@Service
@Slf4j
public class HymnServiceImpl implements HymnService {

    /**
     * The Hymn repo.
     */
    private final HymnRepo hymnRepo;

    /**
     * The Mapper.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Instantiates a new Hymn service.
     *
     * @param hymnRepo the hymn repo
     */
    public HymnServiceImpl(HymnRepo hymnRepo) {
        this.hymnRepo = hymnRepo;
    }

    /**
     * Create hymn hymn.
     *
     * @param hymnPojo the hymn pojo
     * @return the hymn
     */
    public Hymn createHymn(HymnPojo hymnPojo) {
        return this.saveSingleHymn(hymnPojo);
    }

    /**
     * Create multiple hymns list.
     *
     * @param hymnPojoList the hymn pojo list
     * @return the list
     */
    public List<Hymn> createMultipleHymns(List<HymnPojo> hymnPojoList) {
        List<Hymn> hymns = new ArrayList<>();
        hymnPojoList.stream().forEach(hymnPojo -> {
            Hymn hymn = this.saveSingleHymn(hymnPojo);
            hymns.add(hymn);
        });
        return hymns;
    }

    /**
     * Update hymn hymn.
     *
     * @param number         the number
     * @param hymnUpdatePojo the hymn update pojo
     * @return the hymn
     * @throws JsonProcessingException the json processing exception
     */
    public Hymn updateHymn(long number, HymnUpdatePojo hymnUpdatePojo) throws JsonProcessingException {
        log.info("Hymn Update Object: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymnUpdatePojo));
        Hymn hymn = hymnRepo.findHymnByNumber(number).orElseThrow();
        hymn.setTitle(hymnUpdatePojo.getTitle());

        List<HymnVerse> hymnVerseList = new ArrayList<>();
        hymnUpdatePojo.getHymnContent().forEach((order, hymnContent) -> {
            hymnContent.forEach((subTitle, content) -> {
                HymnVerse hymnVerse = hymn.getHymnContent().stream().filter(verse -> verse.getOrder() == order).findFirst().orElseGet(HymnVerse::new);
                hymnVerse.setOrder(order);
                hymnVerse.setSubTitle(subTitle);
                hymnVerse.setContent(content);

                try {
                    log.info("Hymn Verse to be Updated: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymnVerse));
                } catch (JsonProcessingException ignored) {
                }

                hymnVerseList.add(hymnVerse);
            });
        });

        hymn.setHymnContent(hymnVerseList);

        hymnRepo.save(hymn);

        return hymnRepo.findHymnByNumber(number).orElseThrow();
    }

    /**
     * Save single hymn hymn.
     *
     * @param hymnPojo the hymn pojo
     * @return the hymn
     */
    private Hymn saveSingleHymn(@NotNull HymnPojo hymnPojo) {
        Hymn hymn = Hymn.builder()
                .number(hymnPojo.getNumber())
                .title(hymnPojo.getTitle())
                .build();

        List<HymnVerse> hymnVerseList = new ArrayList<>();
        hymnPojo.getHymnContent().forEach((order, hymnContent) -> {
            hymnContent.forEach((subTitle, content) -> {
                HymnVerse hymnVerse = new HymnVerse();
                hymnVerse.setOrder(order);
                hymnVerse.setSubTitle(subTitle);
                hymnVerse.setContent(content);

                hymnVerseList.add(hymnVerse);
            });
        });

        hymn.setHymnContent(hymnVerseList);

        hymnRepo.save(hymn);

        return hymnRepo.findHymnByNumber(hymnPojo.getNumber()).orElseThrow();
    }

    /**
     * Gets hymn by id.
     *
     * @param id the id
     * @return the hymn by id
     */
    @Override
    public Hymn getHymnById(long id) {
        return hymnRepo.findHymnByNumber(id).orElseThrow(NullPointerException::new);
    }

    /**
     * Gets all hymns.
     *
     * @return the all hymns
     */
    @Override
    public List<Hymn> getAllHymns() {
        List<Hymn> hymns = hymnRepo.findAll();
        hymns.sort(new HymnComparator());
        return hymns;
    }

    /**
     * Delete hymn.
     *
     * @param id the id
     */
    public void deleteHymn(long id) {
        Hymn hymn = hymnRepo.findHymnByNumber(id).orElseThrow();
        hymnRepo.delete(hymn);
    }

    /**
     * Search hymn list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    @Override
    public List<Hymn> searchHymn(String searchTerm) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matchingPhrase(searchTerm);
        List<Hymn> hymns = hymnRepo.findAllBy(textCriteria);
        try {
            log.info("Found hymns: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymns));
        } catch (JsonProcessingException ignored) {
        }
        hymns.sort(new HymnComparator());
        return hymns;
    }
}
