package com.test.fdj.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.test.fdj.MainViewModel
import com.test.fdj.data.TeamElement
import com.test.fdj.repository.SportsDataRepositorySportsDBImpl
import com.test.fdj.ui.items.searchToolBar
import com.test.fdj.ui.theme.testFDJTheme
import com.test.fdj.usecase.GetLeaguesFilteredByUseCaseImpl
import com.test.fdj.usecase.GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl
import kotlinx.coroutines.Dispatchers

@SuppressLint("ComposableNaming")
@Composable
fun mainScreen(viewModel: MainViewModel = hiltViewModel()) {
    Column {
        searchToolBar(
            categories = viewModel.leagues,
            onCategoriesUpdateNeeded = { input -> viewModel.updateLeagues(input) },
            onSelectCategory = { categorySelected -> viewModel.selectLeague(categorySelected) },
            text = viewModel.searchLabelText,
        )
        if (viewModel.isTeamsVisible.value) {
            leagueList(viewModel.teams)
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun leagueList(leaguePictures: State<List<TeamElement>>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
    ) {
        items(leaguePictures.value) { leaguePicture ->
            AsyncImage(
                model = leaguePicture.pictureURL,
                contentDescription = leaguePicture.name,
                modifier = Modifier.padding(Dp(6f)),
            )
        }
    }
}

@Preview(showSystemUi = true)
@SuppressLint("ComposableNaming")
@Composable
fun mainScreenPreview() {
    val sportsDataRepositorySportsDB = SportsDataRepositorySportsDBImpl(Dispatchers.Default)
    val viewModel =
        MainViewModel(
            GetLeaguesFilteredByUseCaseImpl(sportsDataRepositorySportsDB),
            GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl(sportsDataRepositorySportsDB),
        )

    testFDJTheme {
        mainScreen(viewModel)
    }
}
