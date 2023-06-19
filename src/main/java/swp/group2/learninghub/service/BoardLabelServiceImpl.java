package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.BoardLabelDAO;
import swp.group2.learninghub.dao.CoreLabelDAO;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.Board;
import swp.group2.learninghub.model.CoreLabel;

import java.util.List;
import java.util.Optional;

@Service
public class BoardLabelServiceImpl implements BoardLabelService {

    private final CoreLabelDAO coreLabelDAO;
    private final BoardLabelDAO boardLabelDAO;

    @Autowired
    public BoardLabelServiceImpl(BoardLabelDAO boardLabelDAO, CoreLabelDAO coreLabelDAO) {
        this.boardLabelDAO = boardLabelDAO;
        this.coreLabelDAO = coreLabelDAO;
    }

    @Override
    public List<BoardLabel> getAllLabelsByBoardId(int boardId) {
        // Implement logic to get all labels by board ID
        return boardLabelDAO.findAllByBoardId(boardId);
    }

    @Override
    public BoardLabel getLabelById(int id) {
        Optional<BoardLabel> label = boardLabelDAO.findById(id);
        if (label.isPresent()) {
            return label.get();
        }
        return null;
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
    public void deleteLabel(int id) {
        boardLabelDAO.deleteById(id);
    }

    @Override
    public void addCoreLabelsToBoardLabels() {
        List<CoreLabel> coreLabels = coreLabelDAO.findAll();

        for (CoreLabel coreLabel : coreLabels) {
            BoardLabel boardLabel = new BoardLabel();
            boardLabel.setBoardId(coreLabel.getId()); // Set the board ID to the core label ID
            boardLabel.setName(coreLabel.getName());
            boardLabel.setColor(coreLabel.getColor());

            boardLabelDAO.save(boardLabel);
        }
    }
}

