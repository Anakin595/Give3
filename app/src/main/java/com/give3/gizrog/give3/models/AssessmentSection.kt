package com.give3.gizrog.give3.models

class AssessmentSection(val section: Section, var grade: Float, val tasks: ArrayList<Task>) {

    fun countTasksCompleted(): Int {
        return tasks.count { it.isComplete }
    }

    fun calculateGrade(): Float {
        val comp = tasks.filter{ it.isComplete }.sumBy{ it.weight }.toFloat()
        val maxComp = tasks.sumBy { it.weight }.toFloat()
        this.grade = 2f + 3f * (comp/maxComp)
        return grade
    }

}