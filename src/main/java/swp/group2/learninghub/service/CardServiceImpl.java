package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.CardDAO;
import swp.group2.learninghub.model.Card;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    public CardDAO cardDAO;

    @Override
    public List<Card> getCardsByLabelId(int id) {
        return null;
    }

    @Override
    public Card getById(int id) {
        Optional<Card> target = cardDAO.findById(id);
        if(target.isPresent()){
            return target.get();
        }else{
            return null;
        }
    }
    public void updateCard(Card newCard){
        try{
            cardDAO.save(newCard);
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<Card> getByColId ( int colId){
        return cardDAO.findCardsByColumnIdOrderByPosition(colId);
    }
}
