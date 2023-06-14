package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.CardLabelDAO;
import swp.group2.learninghub.model.CardLabel;
@Service
public class CardLabelServiceImpl implements CardLabelService{
    @Autowired
    public CardLabelDAO cardLabelDAO;
    @Override
    public void addLabelToCard(CardLabel cardLabel) {
        cardLabelDAO.save(cardLabel);
    }
}
