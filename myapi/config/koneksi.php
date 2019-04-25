<?php
//def 
// definisikan koneksi ke database
$server = "localhost";
$username = "root";
$password = "";
$database = "berita";

// $username = "root";
// $password = "";
// $database = "berita-firas";
//cek koneksi
// Koneksi dan memilih database di server
$con =mysqli_connect($server,$username,$password,$database) or die("Connection failed.");
//mysqli_select_db($database) or die("Database tidak bisa dibuka");
?>

