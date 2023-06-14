package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.BoardDAO;
import swp.group2.learninghub.model.Board;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    public BoardDAO boardDAO;
    @Override
    public void createBoard(Board board) {
        boardDAO.save(board);
    }
}
