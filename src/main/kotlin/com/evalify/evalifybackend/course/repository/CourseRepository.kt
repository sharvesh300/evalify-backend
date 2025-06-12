package com.evalify.evalifybackend.course.repository

import com.evalify.evalifybackend.course.domain.Course
import com.evalify.evalifybackend.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.UUID


@RepositoryRestResource(path = "course")
interface CourseRepository : JpaRepository<Course,UUID>{
    fun findAllByInstructors(instructorIds:List<User>):List<Course>
}