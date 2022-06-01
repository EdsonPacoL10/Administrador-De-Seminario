
package DAO;

import java.util.List;
import utiles.seminario;

public interface SeminarioDAO {
public void insert(seminario sem)throws Exception;
    public void update(seminario sem)throws Exception;
    public void delete(int id)throws Exception;
    public seminario getById(int id)throws Exception;
    public List<seminario> getAll() throws Exception;    
}
