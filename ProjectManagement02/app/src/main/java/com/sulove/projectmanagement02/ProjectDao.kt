package com.sulove.projectmanagement02


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Upsert
    suspend fun upsertProject(Project : Project)
    @Delete
    suspend fun deleteProject(Project: Project)

    @Query("SELECT * FROM project ORDER BY projectName ASC")
    fun getProjectsOrderByProjectName(): Flow<List<Project>>

    @Query("SELECT * FROM project ORDER BY projectType ASC")
    fun getProjectsOrderByProjectType(): Flow<List<Project>>

    @Query("SELECT * FROM project ORDER BY projectId ASC")
    fun getProjectsOrderByProjectId(): Flow<List<Project>>



}