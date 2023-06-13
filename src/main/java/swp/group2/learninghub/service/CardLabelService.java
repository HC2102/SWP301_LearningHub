package swp.group2.learninghub.service;
import swp.group2.learninghub.model.CardLabel;

import java.util.List;
public interface CardLabelService {
    List<CardLabel> getAllLabelsByCardId(Long cardId);
    CardLabel getLabelById(Long id);
    CardLabel createLabel(CardLabel label);
    CardLabel updateLabel(CardLabel label);
    void deleteLabel(Long id);
}
