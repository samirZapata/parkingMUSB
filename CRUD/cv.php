<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
 
    require_once("conexionCrud.php");
    
    $id = $_POST['id'];
    $color = $_POST['color'];
    $modelo =$_POST['modelo'];
    
    $query = "INSERT INTO vehiculo(id,color,modelo) VALUES('$id','$color','$modelo')";
    $result = $mysql->query($query);
        
    if($result === TRUE){
        echo "el vehiculo se creo exitosamente";
    }else{
        echo "Error al crear el vehiculo ";
    }
    $mysql->close();
    }
?>