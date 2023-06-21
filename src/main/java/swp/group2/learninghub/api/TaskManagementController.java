package swp.group2.learninghub.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swp.group2.learninghub.model.*;
import swp.group2.learninghub.model.clientModel.CardData;
import swp.group2.learninghub.model.clientModel.ColumnData;
import swp.group2.learninghub.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/note")
public class TaskManagementController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(TaskManagementController.class);
    @Autowired
    public NoteService noteService;
    @Autowired
    public BoardService boardService;
    @Autowired
    public ColumnService columnService;
    @Autowired
    public CardService cardService;
    @Autowired
    public CardLabelService cardLabelService;
    @Autowired
    public CoreLabelsService coreLabelsService;
    @Autowired
    HttpSession session;
    @Autowired
    private FeatureService featureService;
    private static final int FEATURE_ID = 2;
    private static final String SUCCESSMSG = "Success";
    private static final String FAILMSG = "Fail";

    @GetMapping("test")
    public String test() {
        return "Connected";
    }

    @GetMapping("/cardlabel")
    public void mapping(@RequestBody CardLabel cardLabel) {
        cardLabelService.addLabelToCard(cardLabel);
    }

    @PostMapping("/notes")
    public ResponseEntity<ResponseObject> createNote(@RequestBody Note newNote) {
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try {
            Note target = noteService.createNote(newNote);
            logger.info(target.toString());
            Board newBoard = new Board( newNote.getTitle(), newNote.getCreatedDate(),
                    noteService.getMaxBoardIdByEmail(target.getUserId()), true);
            logger.info(newBoard.toString());
            boardService.createBoard(newBoard);
            target.setId(noteService.getMaxBoardIdByEmail(target.getUserId()));
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(SUCCESSMSG, "Create note successfully!", newNote));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "Create note failed, reason: " + e.getMessage(), null));
        }
    }

    @GetMapping("/cardLabel/card")
    public ArrayList<Card> getCardsByLabel(@RequestParam(name = "id") int labelId) {
        try {
            return cardLabelService.findCardsByLabel(labelId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @GetMapping("/cardLabel/label")
    public ResponseEntity<ResponseObject> getLabelsByCard(@RequestParam(name = "id") int cardId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "ok", cardLabelService.findLabelsInCard(cardId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("fail", e.getMessage(), null));
        }
    }

    @PostMapping("/board")
    public ResponseEntity<ResponseObject> createBoard(@RequestBody Board newBoard) {
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try {
            Board board = boardService.createBoard(newBoard);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(SUCCESSMSG, "Create board successfully!", board));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "Create board failed, reason: " + e.getMessage(), null));
        }
    }

    @PostMapping("/column")
    public String createColumn(@RequestBody KanbanColumn newKanbanColumn) {
        try {
            columnService.createNewColumn(newKanbanColumn);
            return "ok";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @DeleteMapping("/column")
    public ResponseEntity<ResponseObject> archiveColumnById(@RequestParam("id") int id) {
        try {
            KanbanColumn target = columnService.getColumnById(id);
            if (target != null) {
                target.setActive(false);
                //update status
                target = columnService.updateColumn(target);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(SUCCESSMSG, "update successfully", target)
                );
            } else {
                throw new Exception("target is null");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject(FAILMSG, e.getMessage(), null)
            );
        }
    }

    @GetMapping("/column")
    public ResponseEntity<ResponseObject> getColumn(@RequestParam int boardId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "retrieved",
                            columnService.getColumnsByBoardId(boardId)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail", "fail",
                            null));
        }
    }

    @GetMapping("/kanban/data")
    @ResponseBody
    public Map<Integer, ColumnData> kanbanData(@RequestParam("boardId") int boardId) {
        HashMap<Integer, ColumnData> result = new HashMap<>();
        try {
            //if feature is active
            checkAccountAndActive();
            //get all column in the table
            List<KanbanColumn> kbList = columnService.getColumnsByBoardId(boardId);
            for (KanbanColumn k : kbList) {
                //for retrieve all cards inside each column
                ArrayList<CardData> cardData = new ArrayList<>();
                ArrayList<Card> cardList = (ArrayList<Card>) cardService.getByColId(k.getId());
                //each card retrieve tags inside
                for (Card c : cardList) {
                    ArrayList<BoardLabel> labelList = cardLabelService.findLabelsInCard(c.getId());
                    cardData.add(new CardData(c.getId(), c.getName(), labelList));
                }
                result.put(k.getId(), new ColumnData(k.getName(), cardData));
            }
            return result;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return Collections.emptyMap();
        }
    }

    @Transactional
    @PostMapping("/kanban/data")
    public Map<Integer, ColumnData> kanbanDataUpdate(@RequestParam("boardId") int boardId, @RequestBody Map<String, ColumnData> boardData) {
        try {
            int tempColId;
            List<CardData> tempColData;
            Card tempCard;
            List<BoardLabel> cardLabels;
            List<CardLabel> updated = new ArrayList<>();
            int tempPosition;
            //if feature active
            checkAccountAndActive();
            // search data for each column
            for (Map.Entry<String, ColumnData> col : boardData.entrySet()) {
                tempPosition = 1;
                tempColId = Integer.parseInt(col.getKey());
                tempColData = col.getValue().getItems();
                for (CardData cd : tempColData) {
                    tempCard = cardService.getById(cd.getId());
                    //label handle
                    cardLabels = cd.getLabels();
                    for (BoardLabel cl : cardLabels) {
                        updated.add(new CardLabel(cl.getId(), cd.getId()));
                    }
                    cardLabelService.updateCardLabelData(cd.getId(), updated);
                    // handle the position of the card
                    tempCard.setPosition(tempPosition);
                    tempCard.setColumnId(tempColId);
                    cardService.updateCard(tempCard);
                    tempPosition++;
                }
            }
            return kanbanData(boardId);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            logger.warn(boardData.toString());
            return kanbanData(boardId);
        }
    }

    @GetMapping("/notes")
    public ResponseEntity<ResponseObject> showAllNotes() {
        try {
            User u=checkAccountAndActive();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    SUCCESSMSG, "retrieve notes of " +
                    u.getEmail(),
                    noteService.showUserNotesByEmail(u.getEmail())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG,  e.getMessage(), null));
        }
    }


    @Transactional
    @DeleteMapping("/notes")
    public ResponseEntity<ResponseObject> deleteNoteById(@RequestParam int id) {
        try {
            noteService.removeNoteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(SUCCESSMSG, "Delete card: " + id, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseObject(FAILMSG, "Cannot delete card: " + id + ", reason: " + e.getMessage(), null));
        }
    }

    @GetMapping()
    public ResponseEntity<ResponseObject> findNoteById(@RequestParam("id") int id) {
        try {
            User u=checkAccountAndActive();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    SUCCESSMSG, "retrieve note of",
                    noteService.findNoteById(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, e.getMessage(), null));
        }
    }
    @PutMapping()
    public ResponseEntity<ResponseObject> updateNote(@RequestBody Note note) {
        try {
            User u=checkAccountAndActive();
            noteService.updateNote(note);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    SUCCESSMSG, "oke",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, e.getMessage(), null));
        }
    }

    private User checkAccountAndActive() {
        Feature feature = featureService.findFeatureById(FEATURE_ID);
        User userSession = (User) session.getAttribute("user");
        logger.info(userSession.toString());
        if (userSession == null) {
            throw new IllegalArgumentException("can not find user information for this feature");
        }
        if (!feature.isActive()) {
            throw new IllegalArgumentException("Feature is disable: " + feature.getDescription());
        }
        return userSession;

    }
}
