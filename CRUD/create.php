<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
 
    require_once("conexionCrud.php");
    
    $idUser = $_POST['idUser'];
    $nombre = $_POST['nombre'];
    $apellido =$_POST['apellido'];
    $user =$_POST['user'];
    $pass =$_POST['pass'];
    $tipoUser =$_POST['tipoUser'];
    
    $query = "INSERT INTO users(idUser,nombre,apellido,user,pass,tipoUser) VALUES('$idUser','$nombre','$apellido','$user','$pass','$tipoUser')";
    $result = $mysql->query($query);

    if($result === TRUE){
        echo "el usuario se creo exitosamente";
    }else{
        echo "Error al crear el usuario ";
    }
    $mysql->close();
    }
?>