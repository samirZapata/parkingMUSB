<?php
    $mysql = new mysqli(
        "localhost",
        "root",
        "",
        "parkingmusb"
    );  

    if($mysql->connect_error){
        die("fallo en conectarse ".$mysql->connect_error);
    }
    ?>