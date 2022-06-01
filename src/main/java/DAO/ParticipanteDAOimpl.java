
package DAO;

import conexion.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utiles.participante;

public class ParticipanteDAOimpl  extends ConexionDB implements ParticipanteDAO{

    @Override
    public void insert(participante par) throws Exception {
    try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("INSERT INTO participantes (apellidos,nombres,id_seminario,confirmado) values (?,?,?,?)");
        ps.setString(1, par.getApellidos());
        ps.setString(2, par.getNombres());
        ps.setInt(3, par.getSeminario_id());
         ps.setInt(4, par.getConfirmado());
        
        
        ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
   }

    @Override
    public void update(participante par) throws Exception {
       try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("UPDATE participantes  SET apellidos=?,nombres=?,id_seminario=?,confirmado=? where id=?");
         
        ps.setString(1, par.getApellidos());
        ps.setString(2, par.getNombres());
        ps.setInt(3, par.getSeminario_id());
        ps.setInt(4, par.getConfirmado());
        ps.setInt(5, par.getId());
        ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
    }

    @Override
    public void delete(int id) throws Exception {
   try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("DELETE FROM participantes WHERE id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
    }

    @Override
    public participante getById(int id) throws Exception {
      participante v=new participante();
        try {
            this.conectar();
              PreparedStatement ps= this.conn.prepareStatement("SELECT *FROM participantes WHERE id=?");
              ps.setInt(1,id);
              ResultSet rs=ps.executeQuery();
               if (rs.next()) {
               v.setId(rs.getInt("id"));
               v.setApellidos(rs.getString("apellidos"));
               v.setNombres(rs.getString("nombres"));
               v.setSeminario_id(rs.getInt("id_seminario"));
               v.setConfirmado(rs.getInt("confirmado"));
               
           }
         }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
       return v;
           
     
    }

    @Override
    public List<participante> getAll() throws Exception {
      List<participante> lista=null;
        try{
        this.conectar();
        String sql="select p.*, p.id as id, p.nombres as nombres,p.apellidos as apellidos,s.titulo as titulo,p.id_seminario as id_seminario,p.confirmado as confirmado from participantes p join seminarios s WHERE p.id=s.id;";
      
        PreparedStatement ps= this.conn.prepareStatement(sql);
              ResultSet rs=ps.executeQuery();
              lista=new ArrayList<participante>();
           while(rs.next()) {
               participante v=new participante();
               
               v.setId(rs.getInt("id"));
               v.setNombres(rs.getString("nombres"));
               v.setApellidos(rs.getString("apellidos"));
               v.setTitulo(rs.getString("titulo"));
               v.setSeminario_id(rs.getInt("id_seminario"));
               v.setConfirmado(rs.getInt("confirmado"));
           lista.add(v);
           }
           rs.close();
           ps.close();
         }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
        return lista; 
   }
}
