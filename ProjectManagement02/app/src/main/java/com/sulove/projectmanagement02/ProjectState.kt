package com.sulove.projectmanagement02

data class ProjectState(
val projects: List<Project> = emptyList(),
val projectName: String= "",
val projectType: String="",
val projectId: String="",
val isAddingProject: Boolean= false,
val sortType: SortType= SortType.PROJECT_ID

)





