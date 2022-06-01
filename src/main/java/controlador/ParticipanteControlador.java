
package controlador;

import DAO.ParticipanteDAO;
import DAO.ParticipanteDAOimpl;
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
import utiles.participante;
import utiles.seminario;
@WebServlet(name = "ParticipanteControlador", urlPatterns = {"/ParticipanteControlador"})
public class ParticipanteControlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           try {
            ParticipanteDAO dao = new ParticipanteDAOimpl();
            SeminarioDAO daoProducto = new SeminarioDAOimpl();
            int id;
            List<seminario> lista_productos = null;
            participante venta = new participante();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    lista_productos = daoProducto.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("participante", venta);
                    request.getRequestDispatcher("frmparticipante.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    venta = dao.getById(id);
                    lista_productos = daoProducto.getAll();
                    request.setAttribute("lista_productos", lista_productos);
                    request.setAttribute("participante", venta);
                    request.getRequestDispatcher("frmparticipante.jsp").forward(request, response);
                    break;
                case "delete":
                     id=Integer.parseInt(request.getParameter("id"));
                      dao.delete(id);
                      response.sendRedirect("ParticipanteControlador");
                    break;
                case "view":
                    List<participante> lista = dao.getAll();
                    request.setAttribute("participantes", lista);
                    request.getRequestDispatcher("participantes.jsp").forward(request, response);
                    break;
            }

        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());

        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
          int id = Integer.parseInt(request.getParameter("id"));
        int seminario_id = Integer.parseInt(request.getParameter("seminario_id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String titulo = request.getParameter("titulo");
        int confirmado =Integer.parseInt(request.getParameter("confirmado"));
        participante venta = new participante();
 
        venta.setApellidos(apellidos);
        venta.setNombres(nombres);
        venta.setSeminario_id(seminario_id);
        venta.setConfirmado(confirmado);
        venta.setId(id);
        
       
              if (id == 0) {
            ParticipanteDAO dao = new ParticipanteDAOimpl();
            try {
                dao.insert(venta);
                response.sendRedirect("ParticipanteControlador");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al insertar dato" + ex.getMessage());
            }
        } else {
            ParticipanteDAO dao = new ParticipanteDAOimpl();
            try {
                dao.update(venta);
                response.sendRedirect("ParticipanteControlador");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al insertar dato" + ex.getMessage());
            }
        }

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

    