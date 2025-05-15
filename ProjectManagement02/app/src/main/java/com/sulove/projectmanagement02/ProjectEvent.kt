package com.sulove.projectmanagement02



sealed interface ProjectEvent {

    object SaveProject: ProjectEvent
    data class SetProjectName(val projectName: String): ProjectEvent
    data class SetProjectType(val projectType: String): ProjectEvent
    data class SetProjectId(val projectId: String): ProjectEvent

    object ShowDialog : ProjectEvent
    object HideDialog: ProjectEvent

    data class SortProjects(val sortType: SortType ): ProjectEvent
    data class DeleteProject(val project: Project): ProjectEvent


}


