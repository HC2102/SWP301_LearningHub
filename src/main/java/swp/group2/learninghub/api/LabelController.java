package swp.group2.learninghub.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp.group2.learninghub.model.CoreLabel;
import swp.group2.learninghub.service.CoreLabelsService;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
public class LabelController {

    private final CoreLabelsService coreLabelService;

    @Autowired
    public LabelController(CoreLabelsService coreLabelsService) {
        this.coreLabelService = coreLabelsService;
    }

    @GetMapping
    public ResponseEntity<List<CoreLabel>> getAllLabels() {
        List<CoreLabel> labels = coreLabelService.getAllLabels();
        return new ResponseEntity<>(labels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoreLabel> getLabelById(@PathVariable Long id) {
        CoreLabel label = coreLabelService.getLabelById(id);
        if (label != null) {
            return new ResponseEntity<>(label, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CoreLabel> createLabel(@RequestBody CoreLabel label) {
        CoreLabel createdLabel = coreLabelService.createLabel(label);
        return new ResponseEntity<>(createdLabel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoreLabel> updateLabel(@PathVariable Long id, @RequestBody CoreLabel label) {
        CoreLabel existingLabel = coreLabelService.getLabelById(id);
        if (existingLabel != null) {
            label.setId(id);
            CoreLabel updatedLabel = coreLabelService.updateLabel(label);
            return new ResponseEntity<>(updatedLabel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id) {
        CoreLabel existingLabel = coreLabelService.getLabelById(id);
        if (existingLabel != null) {
            coreLabelService.deleteLabel(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}