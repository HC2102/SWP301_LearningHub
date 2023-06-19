package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.BoardLabelDAO;
import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.Card;

import java.util.List;
import java.util.Optional;

@Service
public class BoardLabelServiceImpl implements BoardLabelService {

    @Autowired
    private BoardLabelDAO boardLabelDAO;


    @Override
    public List<BoardLabel> getAllLabelsByBoardId(int boardId) {
        // Implement logic to get all labels by board ID
        return boardLabelDAO.findAllByBoardId(boardId);
    }

    @Override
    public BoardLabel getLabelById(int id) {
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
    public void deleteLabel(int id) {
        boardLabelDAO.deleteById(id);
    }
    @Override
    public List<BoardLabel> getAllLabelsByCardId(int cardId) {
//        return boardLabelDAO.findAllByCardId(cardId);
        return null;
    }
    @Override
    public void addLabelToCard(int cardId, int labelId) {
//        BoardLabel card = boardLabelDAO.findById(cardId).orElse(null);
//        BoardLabel label = boardLabelDAO.findById(labelId).orElse(null);
//        if (card != null && label != null) {
//            card.boardLabelDAO(label);
//            boardLabelDAO.save(card);
//        }
    }

    @Override
    public void removeLabelFromCard(int cardId, int labelId) {
//        BoardLabel card = cardDAO.findById(cardId).orElse(null);
//        BoardLabel label = boardLabelDAO.findById(labelId).orElse(null);
//        if (card != null && label != null) {
//            card.removeLabel(label);
//            cardDAO.save(card);
//        }
    }

}

