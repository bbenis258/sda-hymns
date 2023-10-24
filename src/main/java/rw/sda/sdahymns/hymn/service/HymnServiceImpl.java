package rw.sda.sdahymns.hymn.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.pojo.HymnComparator;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.pojo.HymnUpdatePojo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Hymn service.
 */
@Service
@Slf4j
public class HymnServiceImpl implements HymnService {

    /*private final HymnRepo hymnRepo;

    private final HymnVerseRepo hymnVerseRepo;*/

    /**
     * The Mapper.
     */
    private final ObjectMapper mapper = new ObjectMapper();

    private final List<Hymn> hymnsFromFile;

    /**
     * Instantiates a new Hymn service.
     */
    public HymnServiceImpl() {
        /*this.hymnRepo = hymnRepo;
        this.hymnVerseRepo = hymnVerseRepo;*/
        hymnsFromFile = this.readDataFromFile();
    }

    /**
     * Read data from file list.
     *
     * @return the list
     */
    private List<Hymn> readDataFromFile() {
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/data/indirimbo.json");
            byte[] binaryData = FileCopyUtils.copyToByteArray(inputStream);
            String jsonData = new String(binaryData, StandardCharsets.UTF_8);
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(jsonData);
            JsonArray array = jsonElement.getAsJsonArray();
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Hymn>>() {
            }.getType();
            List<Hymn> hymns = gson.fromJson(array, listType);
            log.info("Loaded hymns: {}", hymns.size());
            return hymns;
        } catch (IOException e) {
            log.error("Unable to load hymns: {}", e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Create hymn.
     *
     * @param hymnPojo the hymn pojo
     */
    public void createHymn(HymnPojo hymnPojo) {
        /*return this.saveSingleHymn(hymnPojo);*/
    }

    /**
     * Create multiple hymns.
     *
     * @param hymnPojoList the hymn pojo list
     */
    public void createMultipleHymns(List<HymnPojo> hymnPojoList) {
        /*List<Hymn> hymns = new ArrayList<>();
        hymnPojoList.stream().forEach(hymnPojo -> {
            Hymn hymn = this.saveSingleHymn(hymnPojo);
            hymns.add(hymn);
        });
        return hymns;*/
    }

    /**
     * Update hymn.
     *
     * @param number         the number
     * @param hymnUpdatePojo the hymn update pojo
     * @throws JsonProcessingException the json processing exception
     */
    public void updateHymn(long number, HymnUpdatePojo hymnUpdatePojo) throws JsonProcessingException {
        /*log.info("Hymn Update Object: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymnUpdatePojo));
        Hymn hymn = hymnRepo.findById(number).orElseThrow();
        hymn.setTitle(hymnUpdatePojo.getTitle());

        Hymn savedHymn = hymnRepo.save(hymn);

        hymnUpdatePojo.getHymnContent().forEach((subTitle, content) -> {
            log.info("Hymn Verse to Update: {} - {}", subTitle, content);
            HymnVerse hymnVerse = hymnVerseRepo.findHymnVerseByHymnAndSubTitle(savedHymn, subTitle);
            if (hymnVerse == null) {
                hymnVerse = new HymnVerse();
                hymnVerse.setHymn(savedHymn);
                hymnVerse.setSubTitle(subTitle);
            }
            try {
                log.info("Hymn Verse to be Updated: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymnVerse));
            } catch (JsonProcessingException ignored) {
            }
            hymnVerse.setContent(content);

            hymnVerseRepo.save(hymnVerse);
        });

        return hymnRepo.findById(number).orElseThrow();*/
    }

    /**
     * Save single hymn.
     *
     * @param hymnPojo the hymn pojo
     */
    private void saveSingleHymn(@NotNull HymnPojo hymnPojo) {
        /*Hymn hymn = Hymn.builder()
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

        savedHymn.setHymnContent(hymnVerseList);

        hymnRepo.save(savedHymn);

        return hymnRepo.findById(hymnPojo.getNumber()).orElseThrow();*/
    }

    /**
     * Gets hymn by id.
     *
     * @param id the id
     * @return the hymn by id
     */
    @Override
    public Hymn getHymnById(long id) {
        return hymnsFromFile.get((int) id);
    }

    /**
     * Gets all hymns.
     *
     * @return the all hymns
     */
    @Override
    public List<Hymn> getAllHymns() {
        /*List<Hymn> hymns = hymnRepo.findAll();
        hymns.sort(new HymnComparator());*/
        return this.hymnsFromFile;
    }

    /**
     * Delete hymn.
     *
     * @param id the id
     */
    public void deleteHymn(long id) {
        /*Hymn hymn = hymnRepo.findById(id).orElseThrow();
        hymnVerseRepo.deleteAll(hymn.getHymnContent());
        hymnRepo.delete(hymn);*/
    }

    /**
     * Search hymn list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    @Override
    public List<Hymn> searchHymn(String searchTerm) {
        List<Hymn> hymns = new ArrayList<>();
        hymnsFromFile.stream().forEach(hymn -> {
            if (hymn.getTitle().contains(searchTerm)) {
                log.info("{} - Contains: {}", hymn.getId(), listContainsId(hymns, hymn.getId()));
                if (!listContainsId(hymns, hymn.getId())) {
                    hymns.add(hymn);
                }
            }
            hymn.getHymnContent().stream().forEach(hymnVerse -> {
                if (hymnVerse.getContent().contains(searchTerm)) {
                    log.info("{} - Contains - from verses: {}", hymn.getId(), listContainsId(hymns, hymn.getId()));
                    if (!listContainsId(hymns, hymn.getId())) {
                        hymns.add(hymn);
                    }
                }
            });
        });
        /*try {
            log.info("Found hymns: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymns));
        } catch (JsonProcessingException ignored) {
        }
        hymnRepo.searchHymn(searchTerm.toLowerCase()).stream().forEach(hymn -> {
            log.info("{} - Contains: {}", hymn.getId(), listContainsId(hymns, hymn.getId()));
            if (!listContainsId(hymns, hymn.getId())) {
                hymns.add(hymn);
            }
        });
        try {
            log.info("Found hymns - from verses: {}", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hymns));
        } catch (JsonProcessingException ignored) {
        }
        hymnVerseRepo.searchHymn(searchTerm.toLowerCase()).stream().forEach(hymn -> {
            log.info("{} - Contains - from verses: {}", hymn.getId(), listContainsId(hymns, hymn.getId()));
            if (!listContainsId(hymns, hymn.getId())) {
                hymns.add(hymn);
            }
        });*/
        hymns.sort(new HymnComparator());
        return hymns;
    }

    /**
     * List contains id boolean.
     *
     * @param list the list
     * @param id   the id
     * @return the boolean
     */
    public boolean listContainsId(final List<Hymn> list, final long id) {
        return list.stream().anyMatch(o -> o.getId() == id);
    }
}
