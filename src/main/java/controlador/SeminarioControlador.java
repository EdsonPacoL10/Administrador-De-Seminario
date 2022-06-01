
package controlador;

import DAO.SeminarioDAO;
import DAO.SeminarioDAOimpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import utiles.seminario;
@WebServlet(name = "SeminarioControlador", urlPatterns = {"/SeminarioControlador"})
public class SeminarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
            seminario cli = new seminario();
            int id;
            SeminarioDAO dao = new SeminarioDAOimpl();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    request.setAttribute("seminario", cli);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;
                case "edit":
                   id=Integer.parseInt(request.getParameter("id"));
                   cli=dao.getById(id);
                   request.setAttribute("seminario", cli);
                    request.getRequestDispatcher("frmseminario.jsp").forward(request, response);
                    break;
                case "delete":
                    id=Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect("SeminarioControlador");
                    break;
                case "view":
                    List<seminario> lista = dao.getAll();
                    request.setAttribute("seminarios", lista);
                    request.getRequestDispatcher("seminarios.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());

        }

    
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    int id=Integer.parseInt(request.getParameter("id"));
        String titulo =request.getParameter("titulo");
         String fecha =request.getParameter("fecha");
         int cupos =Integer.parseInt(request.getParameter("cupos"));
         SeminarioDAO dao = new SeminarioDAOimpl();
         seminario cli= new seminario();
         
         cli.setId(id);
         cli.setTitulo(titulo);
        try {
            cli.setFecha(convierteFecha(fecha));
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(SeminarioControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

         
         cli.setCupos(cupos);
         
         if (id==0) {
                        
            try {
                dao.insert(cli);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Error al insertar dato"+ex.getMessage());
            }
        }else{
            try {
                dao.update(cli);
            } catch (Exception ex) {
                          JOptionPane.showMessageDialog(null,"Error al editar"+ex.getMessage());
            }
         }
         response.sendRedirect("SeminarioControlador");
         
         
        
    }
    
    public Date convierteFecha(String fecha) throws ParseException {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {

        }
        return fechaBD;
    }

}
