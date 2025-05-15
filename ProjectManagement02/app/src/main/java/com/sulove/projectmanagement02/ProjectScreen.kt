package com.sulove.projectmanagement02


import android.provider.CalendarContract.CalendarEntity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProjectScreen(

    state: ProjectState,
    onEvent: (ProjectEvent) -> Unit
) {
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick ={
                onEvent(ProjectEvent.ShowDialog)
            } ){
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "ADD PROJECT"
                )
            }

        },
    ) { _ ->
        if (state.isAddingProject) {
            AddProjectDialog(state = state, onEvent = onEvent)
        }
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        )
        {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(ProjectEvent.SortProjects(sortType))
                                },
                            verticalAlignment = CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(ProjectEvent.SortProjects(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }

                    }
                }
            }


            items(state.projects) { project ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${project.projectName} ${project.projectType}",
                        fontSize = 20.sp

                    )
                    Text(
                        text = project.projectId, fontSize = 12.sp
                    )
                }
                IconButton(onClick = {
                    onEvent(ProjectEvent.DeleteProject(project))

                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = " Delete Project "
                    )
                }
            }


        }

    }
    }

