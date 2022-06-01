package DAO;

import java.util.List;
import utiles.participante;

public interface ParticipanteDAO {
 public void insert(participante par)throws Exception;
    public void update(participante par)throws Exception;
    public void delete(int id)throws Exception;
    public participante getById(int id)throws Exception;
    public List<participante> getAll() throws Exception;    
}
