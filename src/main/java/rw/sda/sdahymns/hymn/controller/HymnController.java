package rw.sda.sdahymns.hymn.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rw.sda.sdahymns.config.ApiPaths;
import rw.sda.sdahymns.hymn.model.Hymn;
import rw.sda.sdahymns.hymn.pojo.HymnPojo;
import rw.sda.sdahymns.hymn.pojo.HymnUpdatePojo;
import rw.sda.sdahymns.hymn.service.HymnService;

import java.util.List;

@RestController
public class HymnController {

    private final HymnService hymnService;

    @Autowired
    public HymnController(HymnService hymnService) {
        this.hymnService = hymnService;
    }

    @PostMapping(value = ApiPaths.V1 + "/hymn/")
    public ResponseEntity<Hymn> createHymn(@NotNull @RequestBody HymnPojo hymnPojo) {
        return ResponseEntity.ok().body(hymnService.createHymn(hymnPojo));
    }

    @PostMapping(value = ApiPaths.V1 + "/hymn/multiple")
    public ResponseEntity<List<Hymn>> createMultipleHymn(@NotNull @RequestBody List<HymnPojo> hymnPojoList) {
        return ResponseEntity.ok().body(hymnService.createMultipleHymns(hymnPojoList));
    }

    @GetMapping(value = ApiPaths.V1 + "/hymn/{id}")
    public ResponseEntity<Hymn> getHymnById(@NotNull @PathVariable long id) {
        return ResponseEntity.ok().body(hymnService.getHymnById(id));
    }

    @GetMapping(value = ApiPaths.V1 + "/hymn/")
    public ResponseEntity<List<Hymn>> getAllHymns() {
        List<Hymn> hymns = hymnService.getAllHymns();
        return ResponseEntity.ok().body(hymns);
    }

    @PutMapping(value = ApiPaths.V1 + "/hymn/{number}")
    public ResponseEntity<Hymn> updateHymn(@NotNull @PathVariable(value = "number") long number, @NotNull @RequestBody HymnUpdatePojo hymnUpdatePojo) {
        return ResponseEntity.ok().body(hymnService.updateHymn(number, hymnUpdatePojo));
    }
}
