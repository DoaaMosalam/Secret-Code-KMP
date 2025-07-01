package com.doaa.mosalam.shared.model

data class Student(
    val name: String,
    val seatNumber: Int,
    val secondLanguage: String,
    val religion: String,
    var groupNumber: Int = 0,
    var secretCode: Int = 0,
)


