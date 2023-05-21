package com.example.calculus;

public class Question
{
    private String question;
    private String correctAnswer;
    private String[] answers;
    private String difficulty;

    public Question(String question, String correctAnswer, String[] answers, String difficulty)
    {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
        this.difficulty = difficulty;
    }

    public String getQuestion()
    {
        return question;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public String[] getAnswers()
    {
        return answers;
    }

    public String getDifficulty()
    {
        return difficulty;
    }
}
