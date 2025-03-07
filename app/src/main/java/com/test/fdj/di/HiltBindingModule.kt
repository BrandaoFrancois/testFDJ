package com.test.fdj.di

import com.test.fdj.provider.SportsDataProviderSportsDBImpl
import com.test.fdj.provider.SportsDataProvider
import com.test.fdj.usecase.GetLeaguesFilteredByUseCase
import com.test.fdj.usecase.GetLeaguesFilteredByUseCaseImpl
import com.test.fdj.usecase.GetOddTeamListForLeagueSortedAnalphabeticalyUseCase
import com.test.fdj.usecase.GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltBindingModule {
    @Binds
    abstract fun bindSportsDataProvider(sportsDataProvider: SportsDataProviderSportsDBImpl) : SportsDataProvider

    @Binds
    abstract fun bindGetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl(getOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl: GetOddTeamListForLeagueSortedAnalphabeticalyUseCaseImpl) : GetOddTeamListForLeagueSortedAnalphabeticalyUseCase

    @Binds
    abstract fun bindGetLeaguesFilteredByUseCase(getLeaguesFilteredByUseCase: GetLeaguesFilteredByUseCaseImpl) : GetLeaguesFilteredByUseCase
}
