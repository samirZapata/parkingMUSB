<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){   

        require_once("conexionCrud.php");
            
        $id = $_POST['idUser'];   
          
        $query = "DELETE FROM users WHERE idUser = '$id'";
        $result = $mysql->query($query);
        
        if($mysql->affected_rows > 0){
            if($result === TRUE){                                       
             echo "el usuario a sido eliminado";
            }
        }else{
                echo "no se encontro ningun usuario";
        }        
        $mysql->close();
        }
    
?>