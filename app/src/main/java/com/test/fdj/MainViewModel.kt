package com.test.fdj

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.fdj.data.TeamElement
import com.test.fdj.service.TeamService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val teamService: TeamService
): ViewModel() {

    private val _leagues : MutableState<List<String>> = mutableStateOf(listOf())
    val leagues: State<List<String>> get() = _leagues

    private val _teams : MutableState<List<TeamElement>> = mutableStateOf(listOf());
    val teams: State<List<TeamElement>> get() = _teams

    private val _isTeamsVisible : MutableState<Boolean> = mutableStateOf(false)
    val isTeamsVisible: State<Boolean> get() = _isTeamsVisible

    val searchLabelText : MutableState<String> = mutableStateOf("")

    private var updateLeaguesJob : Job? = null

    fun updateLeagues(input: String) {
        updateLeaguesJob?.cancel()
        updateLeaguesJob = viewModelScope.launch {
            try {
                val leagues: List<String> = teamService.getLeaguesFilteredBy(input)

                _leagues.value = leagues
                _isTeamsVisible.value = false
            } catch(exception: IOException) {
                Log.e("ERROR", "Big error ! ${exception.message}")
                // TODO Faire quelque chose
            }
        }
    }

    fun selectLeague(leagueName: String) {
        viewModelScope.launch {
            try {
                val teamsElement: List<TeamElement> =
                    teamService.getOddTeamListForLeagueSortedAnalphabeticaly(leagueName)

                _teams.value = teamsElement // A mettre sur la Main Thread
                _isTeamsVisible.value = true

            } catch(exception: IOException) {
                // TODO Faire quelque chose
            }
        }
    }
}