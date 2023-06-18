package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.BoardDAO;
import swp.group2.learninghub.model.Board;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    public BoardDAO boardDAO;
    @Override
    public Board createBoard(Board board) {
        board.setCreatedDate(Date.valueOf(LocalDate.now()));
        return boardDAO.save(board);
    }
}
