package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.CardDAO;
import swp.group2.learninghub.model.Card;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private final CardDAO cardDAO;

    @Autowired
    public CardServiceImpl(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }
    @Override
    public List<Card> getCardsByLabelId(int id) {
        return cardDAO.findAllByLabelsId(id);
    }
    @Override
    public List<Card> getByColId ( int colId){
        return cardDAO.findCardsByColumnId(colId);
    }
}
