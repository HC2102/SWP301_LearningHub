package swp.group2.learninghub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swp.group2.learninghub.dao.ColumnDAO;
import swp.group2.learninghub.model.KanbanColumn;

import java.util.List;

@Service
public class ColumnServiceImpl implements ColumnService{
    @Autowired
    public ColumnDAO columnDAO;

    @Override
    public void createNewColumn(KanbanColumn kanbanColumn){
        columnDAO.save(kanbanColumn);
    }

    @Override
    public List<KanbanColumn> getColumnsByBoardId(int boardId) {
        return columnDAO.getByBoardId(boardId);
    }
}
