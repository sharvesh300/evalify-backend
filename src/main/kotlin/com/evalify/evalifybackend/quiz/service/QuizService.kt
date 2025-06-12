package com.evalify.evalifybackend.quiz.service

import com.evalify.evalifybackend.core.exception.NotFoundException
import com.evalify.evalifybackend.quiz.domain.Quiz
import com.evalify.evalifybackend.quiz.repository.QuizRepository
import com.evalify.evalifybackend.usewr.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class QuizService(private val quizRepository: QuizRepository,private val userRepository: UserRepository) {




    fun addStudentToQuiz(quizId: UUID, studentId:List<String> ){
        val quiz = quizRepository.findById(quizId).orElseThrow{
            NotFoundException("quiz with id $quizRepository not found")
        }
        val student = userRepository.findAllById(studentId)
        quiz.student.addAll(student)
        quizRepository.save(quiz)
    }

    fun removeStudentFromQuiz(quizId: UUID, studentId:List<String> ){
        val quiz = quizRepository.findById(quizId).orElseThrow{
            NotFoundException("quiz with id $quizId not found")
        }
        val student = userRepository.findAllById(studentId)
        quiz.student.removeAll(student)
        quizRepository.save(quiz)
    }

    /**
     * Deletes a quiz by its ID
     * @param quizId The UUID of the quiz to delete
     * @throws NotFoundException if the quiz is not found
     */
    fun deleteQuiz(quizId: UUID) {
        if (!quizRepository.existsById(quizId)) {
            throw NotFoundException("Quiz with id $quizId not found")
        }
        quizRepository.deleteById(quizId)
    }

}
