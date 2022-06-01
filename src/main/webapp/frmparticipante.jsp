<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" rel="stylesheet">
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <title>SEMINARIO</title>
    </head>
    <body>
        <div class="container">
            <h1>Formulario de participante</h1>
            <jsp:include page="META-INF/menu.jsp">   
                <jsp:param name="opcion" value="participantes"/>
            </jsp:include>

            <br>
            <form action ="ParticipanteControlador" method="post">
                <input type="hidden" name="id" value="${participante.id}">
                <div class="mb-3">
                    <div class="mb-3">
                        <label for="" class="form-label">Nombres</label>
                        <input type="text" class="form-control" name="nombres" value ="${participante.nombres}"placeholder="escriba el producto">
                   </div>
                    <div class="mb-3">
                        <label for="" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" name="apellidos" value ="${participante.apellidos}"placeholder="escriba el producto">
                   </div>
                    
                    <label for="" class="form-label">Seminario</label>
                    <select name ="seminario_id" class="form-control">
                        <option value="">-- Seleccione--</option>  
                        <c:forEach var="item" items="${lista_productos}">
                            <option value="${item.id}" <c:if test="${participante.seminario_id==item.id}">
                                        selected 
                                </c:if>
                            >${item.titulo}</option> 
                             </c:forEach>
                    </select>
                    <div class="mb-3">
                        <label for="" class="form-label">confirmacion </label>
                        <input type="number" class="form-control" name="confirmado" value ="${participante.confirmado}"placeholder="1:.confirmacion 0:.falta confirmar">
                   </div>
                        
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>


            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


    </body>
</html>
