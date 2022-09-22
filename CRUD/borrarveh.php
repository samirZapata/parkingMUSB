<?php
    if($_SERVER['REQUEST_METHOD'] == 'POST'){   

        require_once("conexionCrud.php");
            
        $id = $_POST['id'];   
          
        $query = "DELETE FROM vehiculo WHERE id = '$id'";
        $result = $mysql->query($query);
        
        if($mysql->affected_rows > 0){
            if($result === TRUE){                                       
             echo "el vehiculo a sido eliminado";
            }
        }else{
                echo "no se encontro ningun usuario";
        }        
        $mysql->close();
        }
    
?>