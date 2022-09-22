<?php
     if($_SERVER['REQUEST_METHOD'] == 'POST'){   

        require_once("conexionCrud.php");
            
        $idUser = $_POST['idUser'];   
        $nombre = $_POST['nombre'];
        $apellido =$_POST['apellido'];
        $user =$_POST['user'];
        $pass =$_POST['pass'];
      
          
        $query = "UPDATE users SET nombre = '$nombre' ,apellido = '$apellido' ,user = '$user' ,pass = '$pass'  WHERE idUser = '$idUser'";
        $result = $mysql->query($query);
        
        if($mysql->affected_rows > 0){
            if($result === TRUE){                                       
             echo "el usuario a sido actualizado";
            }else{
                echo "error";
            }
        }else{
                echo "no se encontro ningun usuario";
        }        
        $mysql->close();
        }
?>