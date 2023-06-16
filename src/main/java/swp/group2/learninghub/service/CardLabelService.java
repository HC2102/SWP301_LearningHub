package swp.group2.learninghub.service;

import swp.group2.learninghub.model.BoardLabel;
import swp.group2.learninghub.model.Card;
import swp.group2.learninghub.model.CardLabel;

import java.util.ArrayList;

public interface CardLabelService {
    public void addLabelToCard(CardLabel cardLabel);
    public ArrayList<BoardLabel> findLabelsInCard(int cardId) throws Exception;
    public ArrayList<Card> findCardsByLabel(int labelId) throws Exception;
}
