package com.sulove.projectmanagement02



import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Project(
    val projectName: String,
    val projectType: String,
    val projectId: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0


)
