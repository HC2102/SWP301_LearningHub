package swp.group2.learninghub.service;

import swp.group2.learninghub.model.Card;

import java.util.List;

public interface CardService {
    List<Card> getCardsByLabelId(int id);
  public List<Card> getByColId(int colId);
}
