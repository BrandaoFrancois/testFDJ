package com.test.fdj.hilt

import com.test.fdj.api.SportsDataProviderSportsDBImpl
import com.test.fdj.provider.SportsDataProvider
import com.test.fdj.service.TeamService
import com.test.fdj.service.TeamServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltBindingModule {
    @Binds
    abstract fun bindTeamService(teamService : TeamServiceImpl) : TeamService

    @Binds
    abstract fun bindSportsDataProvider(sportsDataProvider: SportsDataProviderSportsDBImpl) : SportsDataProvider
}
