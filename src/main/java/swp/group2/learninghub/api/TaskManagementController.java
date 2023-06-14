package swp.group2.learninghub.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp.group2.learninghub.model.*;
import swp.group2.learninghub.model.clientModel.CardData;
import swp.group2.learninghub.model.clientModel.ColumnData;
import swp.group2.learninghub.service.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/note")
public class TaskManagementController {
    @Autowired
    public NoteService noteService;
    @Autowired
    public BoardService boardService;
    @Autowired
    public BoardLabelService boardLabelService;
    @Autowired
    public ColumnService columnService;
    @Autowired
    public CardService cardService;
    @Autowired
    public CoreLabelsService coreLabelsService;

    @GetMapping("test")
    public String test(){
        return"Connected";
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
    public Map<String, ColumnData> kanbanData(@RequestParam("boardId") int boardId){
        HashMap<String, ColumnData> result = new HashMap<>();
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
                    labelList = (ArrayList<BoardLabel>) boardLabelService.getLabelsByCardId(c.getId());
                    cardData.add(new CardData(c,labelList));
                }
                result.put(k.getName(),new ColumnData(k.getName(),cardData));
            }
            return result;
        } catch (Exception e){
            return null;
        }
    }
    @PostMapping("/kanban/data")
    public Map<String, ColumnData> kanbanDataUpdate(@RequestParam("boardId") int boardId){
        HashMap<String, ColumnData> result = new HashMap<>();
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
                    labelList = (ArrayList<BoardLabel>) boardLabelService.getLabelsByCardId(c.getId());
                    cardData.add(new CardData(c,labelList));
                }
                result.put(k.getName(),new ColumnData(k.getName(),cardData));
            }
            return result;
        } catch (Exception e){
            return null;
        }
    }
}
