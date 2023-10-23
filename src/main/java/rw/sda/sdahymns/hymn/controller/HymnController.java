package rw.sda.sdahymns.hymn.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rw.sda.sdahymns.config.ApiPaths;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.service.HymnService;

import java.util.List;

/**
 * The type Hymn controller.
 */
@RestController
public class HymnController {

    /**
     * The Hymn service.
     */
    @Autowired
    private HymnService hymnService;

    /*@PostMapping(value = ApiPaths.V1 + "/hymn/")
    public ResponseEntity<Hymn> createHymn(@NotNull @RequestBody HymnPojo hymnPojo) {
        return ResponseEntity.ok().body(hymnService.createHymn(hymnPojo));
    }*/

    /*@PostMapping(value = ApiPaths.V1 + "/hymn/multiple")
    public ResponseEntity<List<Hymn>> createMultipleHymn(@NotNull @RequestBody List<HymnPojo> hymnPojoList) {
        return ResponseEntity.ok().body(hymnService.createMultipleHymns(hymnPojoList));
    }*/

    /**
     * Gets hymn by id.
     *
     * @param id the id
     * @return the hymn by id
     */
    @GetMapping(value = ApiPaths.V1 + "/hymn/{id}")
    public ResponseEntity<Hymn> getHymnById(@NotNull @PathVariable long id) {
        return ResponseEntity.ok().body(hymnService.getHymnById(id));
    }

    /**
     * Gets all hymns.
     *
     * @return the all hymns
     */
    @GetMapping(value = ApiPaths.V1 + "/hymn/")
    public ResponseEntity<List<Hymn>> getAllHymns() {
        List<Hymn> hymns = hymnService.getAllHymns();
        return ResponseEntity.ok().body(hymns);
    }

    /*@PutMapping(value = ApiPaths.V1 + "/hymn/{number}")
    public ResponseEntity<Hymn> updateHymn(@NotNull @PathVariable(value = "number") long number, @NotNull @RequestBody HymnUpdatePojo hymnUpdatePojo) throws JsonProcessingException {
        return ResponseEntity.ok().body(hymnService.updateHymn(number, hymnUpdatePojo));
    }*/

    /*@DeleteMapping(value = ApiPaths.V1 + "/hymn/{number}")
    public ResponseEntity<Boolean> deleteHymn(@NotNull @PathVariable(value = "number") long number) {
        hymnService.deleteHymn(number);
        return ResponseEntity.ok().body(Boolean.TRUE);
    }*/

    /**
     * Search hymns response entity.
     *
     * @param searchTerm the search term
     * @return the response entity
     */
    @GetMapping(value = ApiPaths.V1 + "/hymn/search/{searchTerm}")
    public ResponseEntity<List<Hymn>> searchHymns(@NotNull @PathVariable String searchTerm) {
        return ResponseEntity.ok().body(hymnService.searchHymn(searchTerm));
    }
}
