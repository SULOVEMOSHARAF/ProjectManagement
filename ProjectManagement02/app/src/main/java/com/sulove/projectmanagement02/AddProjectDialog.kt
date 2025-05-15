package com.sulove.projectmanagement02

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun AddProjectDialog(
    state: ProjectState,
    onEvent: (ProjectEvent) -> Unit,
    modifier: Modifier = Modifier
){
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(ProjectEvent.HideDialog)
        },
        title = { Text(text= " ADD PROJECT ") },
        text = {
            Column (
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                TextField(
                    value = state.projectName,
                    onValueChange = {
                        onEvent(ProjectEvent.SetProjectName(it))
                    },
                    placeholder = {
                        Text(text= " PROJECT NAME ")
                    }

                )
                TextField(
                    value = state.projectType,
                    onValueChange = {
                        onEvent(ProjectEvent.SetProjectType(it))
                    },
                    placeholder = {
                        Text(text= " PROJECT TYPE")
                    }
                )
                TextField(
                    value= state.projectId,
                    onValueChange = {
                        onEvent(ProjectEvent.SetProjectId(it))
                    },
                    placeholder = {
                        Text(text= " PROJECT ID ")
                    }
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(ProjectEvent.SaveProject)
                }) {
                    Text(text= " SAVE ")
                }
            }
        }


    )
}
