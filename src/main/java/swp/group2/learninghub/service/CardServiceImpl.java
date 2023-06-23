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
    public int getMaxCardId(int columnId) {
        return cardDAO.getMaxCardIdByColumn(columnId);
    }

    @Override
    public List<Card> getCardsByLabelId(int id) {
        return null;
    }

    @Override
    public void deleteCardById(int cardId) {
        cardDAO.deleteById(cardId);
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

    @Override
    public void addCard(Card newCard) {
        cardDAO.save(newCard);
    }

    public void updateCard(Card newCard) throws Exception {
        try{
            Optional<Card> card = cardDAO.findById(newCard.getId());
            if(card.isEmpty()){
                throw new Exception("card Id is not valid");
            }
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
