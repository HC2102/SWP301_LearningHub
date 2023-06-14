package swp.group2.learninghub.api;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swp.group2.learninghub.model.*;
import swp.group2.learninghub.model.clientModel.BoardData;
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

    @GetMapping("/formation")
    public Map<String,BoardData> testForm(){
        List<BoardData> list = new ArrayList<>();
        HashMap<String,BoardData> result = new HashMap<>();
        List<KanbanColumn> kbList = columnService.getColumnsByBoardId(1);
        for(KanbanColumn k : kbList){
            list.add(new BoardData(k.getName(),cardService.getByColId(6)));
            result.put(k.getName(),new BoardData(k.getName(),cardService.getByColId(6)));
        }
        return result;
    }
}
