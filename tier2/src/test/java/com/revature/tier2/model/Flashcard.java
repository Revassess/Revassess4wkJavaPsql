package com.revature.tier2.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Flashcard {

    @Id
    @Column(name = "flashcard_id")
   private int flashcardId;

    private String question;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    public int getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Flashcard(int flashcardId, String question, String answer, Category categoryId) {
        this.flashcardId = flashcardId;
        this.question = question;
        this.answer = answer;
        this.categoryId = categoryId;
    }

    public Flashcard() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard that = (Flashcard) o;
        return flashcardId == that.flashcardId && question.equals(that.question) && answer.equals(that.answer) && categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flashcardId, question, answer, categoryId);
    }

    @Override
    public String toString() {
        return "UserProblem4{" +
                "flashcardId=" + flashcardId +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}