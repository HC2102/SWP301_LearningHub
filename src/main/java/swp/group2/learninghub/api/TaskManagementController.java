package swp.group2.learninghub.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swp.group2.learninghub.service.BoardService;
import swp.group2.learninghub.service.ColumnService;
import swp.group2.learninghub.service.CoreLabelsService;
import swp.group2.learninghub.service.NoteService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RequestMapping("/api/v1/note")
public class TaskManagementController {
    public NoteService noteService;
    public BoardService boardService;
    public ColumnService columnService;
    public CoreLabelsService coreLabelsService;
    @GetMapping("test")
    public String test(){
        return"Connected";
    }
}
