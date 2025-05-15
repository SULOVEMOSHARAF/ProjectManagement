package com.sulove.projectmanagement02



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


@OptIn(ExperimentalCoroutinesApi::class)
class ProjectViewModel(
    private val dao : ProjectDao

): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.PROJECT_ID)
    private val _projects = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                SortType.PROJECT_NAME -> dao.getProjectsOrderByProjectName()
                SortType.PROJECT_TYPE -> dao.getProjectsOrderByProjectType()
                SortType.PROJECT_ID -> dao.getProjectsOrderByProjectId()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(ProjectState())
    val state = combine(_state, _sortType, _projects) { state, sortType, projects ->
        state.copy(
            projects = projects,
            sortType = sortType
        )


    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProjectState())

    fun onEvent(event: ProjectEvent) {
        when (event) {
            is ProjectEvent.DeleteProject -> run {
                viewModelScope.launch { dao.deleteProject(event.project) }
            }

            ProjectEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingProject = false
                    )
                }
            }

            ProjectEvent.SaveProject -> {
                val projectName = _state.value.projectName
                val projectType = _state.value.projectType
                val projectId = _state.value.projectId

                if (projectName.isBlank() || projectType.isBlank() || projectId.isBlank()) {
                    return
                }

                val project = Project(
                    projectName = projectName,
                    projectType = projectType,
                    projectId = projectId
                )
                viewModelScope.launch { dao.upsertProject(project) }
                _state.update {
                    it.copy(
                        isAddingProject = false,
                        projectName = "",
                        projectType = "",
                        projectId = ""
                    )
                }
            }

            is ProjectEvent.SetProjectName -> {
                _state.update {
                    it.copy(
                        projectName = event.projectName
                    )
                }
            }

            is ProjectEvent.SetProjectType -> {
                _state.update {
                    it.copy(
                        projectType = event.projectType
                    )
                }
            }

            is ProjectEvent.SetProjectId -> {
                _state.update {
                    it.copy(
                        projectId = event.projectId
                    )
                }
            }

            ProjectEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingProject = true
                    )
                }
            }

            is ProjectEvent.SortProjects -> {
                _sortType.value = event.sortType
            }


        }
    }


}














