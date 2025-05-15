<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Connexion à la base de données
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mobile"; // à modifier

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    http_response_code(500);
    echo json_encode(["error" => "Connexion échouée : " . $conn->connect_error]);
    exit();
}

// Traitement des paramètres GET
if (isset($_GET["random"])) {
    // Une question aléatoire
    $sql = "SELECT * FROM quiz ORDER BY RAND() LIMIT 1";
} elseif (isset($_GET["id"])) {
    // Une question par ID
    $id = intval($_GET["id"]);
    $sql = "SELECT * FROM quiz WHERE id = $id";
} else {
    // Toutes les questions
    $sql = "SELECT * FROM quiz";
}

$result = $conn->query($sql);
$quiz = [];

while ($row = $result->fetch_assoc()) {
    $quiz[] = $row;
}

echo json_encode($quiz, JSON_UNESCAPED_UNICODE);
$conn->close();
?>
