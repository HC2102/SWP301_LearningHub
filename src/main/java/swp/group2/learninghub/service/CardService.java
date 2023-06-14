package swp.group2.learninghub.service;

import swp.group2.learninghub.model.Card;

import java.util.ArrayList;
import java.util.List;

public interface CardService {
    public List<Card> getByColId(int colId);
}
