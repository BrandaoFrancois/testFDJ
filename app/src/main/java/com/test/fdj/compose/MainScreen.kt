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
import com.test.fdj.compose.items.SearchToolBar
import com.test.fdj.data.TeamElement
import com.test.fdj.ui.theme.TestFDJTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column {
        SearchToolBar(
            categories = viewModel.leagues,
            onCategoriesUpdateNeeded = { input -> viewModel.updateLeagues(input) },
            onSelectCategorie = { categorieSelected -> viewModel.selectLeague(categorieSelected) },
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
    TestFDJTheme {
        MainScreen()
    }
}