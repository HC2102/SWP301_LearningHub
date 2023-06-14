package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.BoardLabelDAO;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.service.BoardLabelService;

import java.util.List;
import java.util.Optional;

@Service
public class BoardLabelServiceImpl implements BoardLabelService {

    private final BoardLabelDAO boardLabelDAO;

    @Autowired
    public BoardLabelServiceImpl(BoardLabelDAO boardLabelDAO) {
        this.boardLabelDAO = boardLabelDAO;
    }

    @Override
    public List<BoardLabel> getAllLabelsByBoardId(Long boardId) {
        // Implement logic to get all labels by board ID
        return boardLabelDAO.findAllByBoardId(boardId);
    }

    @Override
    public BoardLabel getLabelById(Long id) {
        Optional<BoardLabel> label = boardLabelDAO.findById(id);
        return label.orElse(null);
    }

    @Override
    public BoardLabel createLabel(BoardLabel label) {
        return boardLabelDAO.save(label);
    }

    @Override
    public BoardLabel updateLabel(BoardLabel label) {
        return boardLabelDAO.save(label);
    }

    @Override
    public void deleteLabel(Long id) {
        boardLabelDAO.deleteById(id);
    }
}
