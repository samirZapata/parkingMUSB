<?php
     if($_SERVER['REQUEST_METHOD'] == 'POST'){   

        require_once("conexionCrud.php");
            
        $id = $_POST['id'];   
        $color = $_POST['color'];
        $modelo =$_POST['modelo'];
       
        $query = "UPDATE vehiculo SET  color = '$color' ,modelo = '$modelo'  WHERE id = '$id'";
        $result = $mysql->query($query);
        
        if($mysql->affected_rows > 0){
            if($result === TRUE){                                       
             echo "el vehiculo a sido actualizado";
            }else{
                echo "error";
            }
        }else{
                echo "no se encontro ningun usuario";
        }        
        $mysql->close();
        }
?>