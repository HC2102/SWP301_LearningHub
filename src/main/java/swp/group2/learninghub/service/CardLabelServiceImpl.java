package swp.group2.learninghub.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.CardLabelDAO;
import swp.group2.learninghub.model.CardLabel;
import swp.group2.learninghub.service.CardLabelService;

import java.util.List;
import java.util.Optional;
@Service
public class CardLabelServiceImpl implements CardLabelService {

    private final CardLabelDAO cardLabelDAO;

    @Autowired
    public CardLabelServiceImpl(CardLabelDAO cardLabelDAO) {
        this.cardLabelDAO = cardLabelDAO;
    }

    @Override
    public List<CardLabel> getAllLabelsByCardId(Long cardId) {
        // Implement logic to get all labels by card ID
        return cardLabelDAO.findAllByCardId(cardId);
    }

    @Override
    public CardLabel getLabelById(Long id) {
        Optional<CardLabel> label = cardLabelDAO.findById(id);
        return label.orElse(null);
    }

    @Override
    public CardLabel createLabel(CardLabel label) {
        return cardLabelDAO.save(label);
    }

    @Override
    public CardLabel updateLabel(CardLabel label) {
        return cardLabelDAO.save(label);
    }

    @Override
    public void deleteLabel(Long id) {
        cardLabelDAO.deleteById(id);
    }
}
