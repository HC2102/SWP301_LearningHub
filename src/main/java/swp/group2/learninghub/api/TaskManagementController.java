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
    public String test(){
        return"Connected";
    }

    @GetMapping("/cardlabel")
    public void mapping(@RequestBody CardLabel cardLabel){
        cardLabelService.addLabelToCard(cardLabel);
    }
    @PostMapping()
    public String createNote(@RequestBody Note newNote){
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try{
            noteService.createNote(newNote);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/cardLabel/card")
    public ArrayList<Card> getCardsByLabel(@RequestParam(name = "id") int labelId){
        try{
            return cardLabelService.findCardsByLabel(labelId);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
    @GetMapping("/cardLabel/label")
    public ResponseEntity<ResponseObject> getLabelsByCard(@RequestParam(name = "id") int cardId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","ok",cardLabelService.findLabelsInCard(cardId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("fail",e.getMessage(),null));
        }
    }
    @PostMapping("/board")
    public String createBoard(@RequestBody Board newBoard){
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try{
            boardService.createBoard(newBoard);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/column")
    public String createColumn(@RequestBody KanbanColumn newKanbanColumn){
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try{
            columnService.createNewColumn(newKanbanColumn);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/column")
    public ResponseEntity<ResponseObject> getColumn(@RequestParam int boardId){
        Logger logger = Logger.getLogger(TaskManagementController.class.getName());
        try{
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success","retrieved",
                            columnService.getColumnsByBoardId(boardId)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail","fail",
                            null));
        }
    }

    @GetMapping("/kanban/data")
    @ResponseBody
    public Map<Integer, ColumnData> kanbanData(@RequestParam("boardId") int boardId){
        HashMap<Integer, ColumnData> result = new HashMap<>();
        ArrayList<Card> cardList = new ArrayList<>();
        ArrayList<BoardLabel> labelList = new ArrayList<>();
        ArrayList<CardData> cardData = new ArrayList<>();
        try{
            //get all column in the table
            List<KanbanColumn> kbList = columnService.getColumnsByBoardId(boardId);
            for(KanbanColumn k : kbList){
                //for retrieve all cards inside each column
                cardList = (ArrayList<Card>) cardService.getByColId(k.getId());
                //each card retrieve tags inside
                for(Card c : cardList){
                    labelList = cardLabelService.findLabelsInCard(c.getId());
                    cardData.add(new CardData(c.getId(),c.getName(),labelList));
                }
                result.put(k.getId(),new ColumnData(k.getName(),cardData));
            }
            return result;
        } catch (Exception e){
            return null;
        }
    }
    @Transactional
    @PostMapping("/kanban/data")
    public Map<Integer, ColumnData> kanbanDataUpdate(@RequestParam("boardId") int boardId,@RequestBody Map<String,ColumnData>  boardData) {
        try{
            int tempColId;
            List<CardData> tempColData;
            Card tempCard;
            List<BoardLabel> cardLabels;
            List<CardLabel> updated = new ArrayList<>();
            int tempPosition;
            // search data for each column
            for(Map.Entry<String, ColumnData> col : boardData.entrySet()){
                tempPosition =1;
                tempColId = Integer.parseInt(col.getKey());
                tempColData = col.getValue().getItems();
                for(CardData cd : tempColData){
                    tempCard = cardService.getById(cd.getId());
                    //label handle
                    cardLabels = cd.getLabels();
                    for(BoardLabel cl : cardLabels){
                        updated.add(new CardLabel(cl.getId(),cd.getId()));
                    }
                    cardLabelService.updateCardLabelData(cd.getId(),updated);
                    // handle the position of the card
                    tempCard.setPosition(tempPosition);
                    tempCard.setColumnId(tempColId);
                    cardService.updateCard(tempCard);
                    tempPosition ++;
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
            User userSession = (User) session.getAttribute("user");
            if(userSession == null) {
                throw new IllegalArgumentException("can not find user information for this feature");
            }
            isFeatureActive();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    SUCCESSMSG, "retrieve notes of " +
                    userSession.getEmail(),
                    noteService.showUserNotesByEmail(userSession.getEmail())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                    new ResponseObject(FAILMSG, "account fail to connect: " + e.getMessage(), null));
        }
    }

    private void isFeatureActive() {
        Feature feature = featureService.findFeatureById(FEATURE_ID);
        if(!feature.isActive()) {
            throw new IllegalArgumentException("Feature is disable: " + feature.getDescription());
        }
    }
}
