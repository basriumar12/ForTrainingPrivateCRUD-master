<?php

    include './config/koneksi.php';

    if(isset($_POST["username"])){
        $username=$_POST["username"];

    }
        $sql=mysqli_query($con,"SELECT * FROM tbl_user WHERE username='$username'");

    $response=array();
    $cek=mysqli_num_rows($sql);
    if($cek >0){
        $response["username"]=array();

        while ($row=mysqli_fetch_array($sql)){

            $data["id_user"]=$row["id_user"];
                $data["fullname"]=$row["fullname"];
                $data["username"]=$row["username"];
                $data["access"]=$row["access"];
                $data["insert_date"]=$row["insert_date"];
                $data["update_date"]=$row["update_date"];
                $data["last_active_date"]=$row["last_active_date"];
                
                $response["msg"]=trim("Login success.");
                $response["code"]=200;
                $response["status"]=true;    
            array_push($response["username"],$data);
        }

        echo json_encode($response);

    }else{
        $response["msg"]="News not found.";
        $response["code"]=404;
        $response["status"]=false; 
        echo json_encode($response);
    } 

?>