package com.test.fdj.compose

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
import com.test.fdj.provider.SportsDataProviderSportsDBImpl
import com.test.fdj.compose.items.SearchToolBar
import com.test.fdj.data.TeamElement
import com.test.fdj.ui.theme.TestFDJTheme
import com.test.fdj.usecase.GetLeaguesFilteredByUseCaseImpl
import com.test.fdj.usecase.GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl
import kotlinx.coroutines.Dispatchers

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    Column {
        SearchToolBar(
            categories = viewModel.leagues,
            onCategoriesUpdateNeeded = { input -> viewModel.updateLeagues(input) },
            onSelectCategory = { categorySelected -> viewModel.selectLeague(categorySelected) },
            text = viewModel.searchLabelText
        )
        if (viewModel.isTeamsVisible.value) {
            LeagueList(viewModel.teams)
        }
    }
}

@Composable
fun LeagueList(leaguePictures: State<List<TeamElement>>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(leaguePictures.value) { leaguePicture ->
            AsyncImage(model = leaguePicture.pictureURL, contentDescription = leaguePicture.name, modifier = Modifier.padding(Dp(6f)))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenPreview() {
    val sportsDataProviderSportsDB = SportsDataProviderSportsDBImpl(Dispatchers.Default)
    val viewModel = MainViewModel(
        GetLeaguesFilteredByUseCaseImpl(sportsDataProviderSportsDB),
        GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl(sportsDataProviderSportsDB)
    )

    TestFDJTheme {
        MainScreen(viewModel)
    }
}