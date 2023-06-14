package swp.group2.learninghub.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.Card;
import swp.group2.learninghub.model.CoreLabel;
import swp.group2.learninghub.service.BoardLabelService;
import swp.group2.learninghub.service.CardService;
import swp.group2.learninghub.service.CoreLabelsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/labels")
public class LabelController {
    private final BoardLabelService boardLabelService;
    private final CardService cardService;
    private final CoreLabelsService coreLabelService;

    @Autowired
    public LabelController(BoardLabelService boardLabelService, CardService cardService, CoreLabelsService coreLabelsService) {
        this.boardLabelService = boardLabelService;
        this.cardService = cardService;
        this.coreLabelService = coreLabelsService;
    }

    @GetMapping
    public ResponseEntity<List<CoreLabel>> getAllLabels() {
        List<CoreLabel> labels = coreLabelService.getAllLabels();
        return new ResponseEntity<>(labels, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoreLabel> getLabelById(@PathVariable int id) {
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
    public ResponseEntity<CoreLabel> updateLabel(@PathVariable int id, @RequestBody CoreLabel label) {
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
    public ResponseEntity<Void> deleteLabel(@PathVariable int id) {
        CoreLabel existingLabel = coreLabelService.getLabelById(id);
        if (existingLabel != null) {
            coreLabelService.deleteLabel( id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}/cards")
    public ResponseEntity<List<Card>> getCardsByLabelId(@PathVariable int id) {
        BoardLabel label = boardLabelService.getLabelById(id);
        if (label != null) {
            List<Card> cards = cardService.getCardsByLabelId(id);
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{labelId}/cards/{cardId}")
    public ResponseEntity<Void> addLabelToCard(@PathVariable int labelId, @PathVariable int cardId) {
        boardLabelService.addLabelToCard(cardId, labelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{labelId}/cards/{cardId}")
    public ResponseEntity<Void> removeLabelFromCard(@PathVariable int labelId, @PathVariable int cardId) {
        boardLabelService.removeLabelFromCard(cardId, labelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}