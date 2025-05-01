package com.example.projet.Quiz;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class QuizGame {
    private final List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    public QuizGame() {
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//        fetchQuestionsFromApi();
//        Collections.shuffle(questions); // Tirage aléatoire


        new Thread(() -> {
            fetchQuestionsFromApi();
            Collections.shuffle(questions); // Tirage aléatoire
        }).start();
    }

    private void fetchQuestionsFromApi() {
        try {
            //URL url = new URL("http://192.168.X.X/mobile/getQuizRand.php"); // IP WAMP
            URL url = new URL("http://10.0.2.2/mobile/getQuizRand.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            in.close();

            JSONArray array = new JSONArray(content.toString());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                Question q = new Question(
                        obj.getString("question"),
                        obj.getString("reponse_a"),
                        obj.getString("reponse_b"),
                        obj.getString("reponse_c"),
                        obj.getString("reponse_d"),
                        obj.getString("bonne_reponse")
                );
                questions.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext() {
        return currentIndex < questions.size();
    }

    public Question nextQuestion() {
        return questions.get(currentIndex++);
    }

    public void submitAnswer(String userAnswer) {
        Question q = questions.get(currentIndex - 1);
        if (q.getBonneReponse().equalsIgnoreCase(userAnswer)) {
            score++;
        }
    }

    public int getScore() {
        return score;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}

