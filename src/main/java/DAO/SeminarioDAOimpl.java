/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexion.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utiles.seminario;

/**
 *
 * @author Apple
 */
public class SeminarioDAOimpl extends ConexionDB implements SeminarioDAO{

    @Override
    public void insert(seminario sem) throws Exception {
      try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("INSERT INTO seminarios (titulo,fecha,cupo) values (?,?,?);");
        ps.setString(1, sem.getTitulo());
        ps.setDate(2, sem.getFecha());
        ps.setInt(3, sem.getCupos());
        ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }

    
    }

    @Override
    public void update(seminario sem) throws Exception {
            try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("UPDATE seminarios SET titulo=?,fecha=?,cupo=? where id=?");
        ps.setString(1, sem.getTitulo());
        ps.setDate(2, sem.getFecha());
        ps.setInt(3, sem.getCupos());
        ps.setInt(4, sem.getId());
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
        PreparedStatement ps= this.conn.prepareStatement("DELETE FROM seminarios WHERE id=? ");
      ps.setInt(1, id);
        ps.executeUpdate();
       }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }

    }

    @Override
    public seminario getById(int id) throws Exception {
       seminario cli=new seminario();
       try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("SELECT * FROM seminarios WHERE id=?");
         ps.setInt(1, id);
           ResultSet rs=ps.executeQuery();
           if (rs.next()) {
               cli.setId(rs.getInt("id"));
               cli.setTitulo(rs.getString("titulo"));
               cli.setFecha(rs.getDate("fecha"));
               cli.setCupos(rs.getInt("cupo"));
               
           }
         }catch(Exception e){
           throw e;
       }finally{
           this.desconectar();
           
       }
       return cli;
 }

    @Override
    public List<seminario> getAll() throws Exception {
      List<seminario> lista=null;
        try{
        this.conectar();
        PreparedStatement ps= this.conn.prepareStatement("SELECT * FROM seminarios");
              ResultSet rs=ps.executeQuery();
              lista=new ArrayList<seminario>();
           while(rs.next()) {
               seminario cli=new seminario();
               
               cli.setId(rs.getInt("id"));
               cli.setTitulo(rs.getString("titulo"));
               cli.setFecha(rs.getDate("fecha"));
               cli.setCupos(rs.getInt("cupo"));
           lista.add(cli);
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

   