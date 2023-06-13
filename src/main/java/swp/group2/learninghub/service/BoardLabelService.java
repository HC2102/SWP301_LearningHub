package swp.group2.learninghub.service;
import swp.group2.learninghub.model.BoardLabel;

import java.util.List;
public interface BoardLabelService {
    List<BoardLabel> getAllLabelsByBoardId(Long boardId);
    BoardLabel getLabelById(Long id);
    BoardLabel createLabel(BoardLabel label);
    BoardLabel updateLabel(BoardLabel label);
    void deleteLabel(Long id);
}
