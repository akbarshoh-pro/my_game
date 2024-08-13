package uz.gita.my_game.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.my_game.data.repository.AppRepositoryImpl
import uz.gita.my_game.domain.repository.AppRepository
import uz.gita.my_game.domain.usecase.CreateNewCellUseCase
import uz.gita.my_game.domain.usecase.GetAllCellsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppRepository() : AppRepository = AppRepositoryImpl()

    @Provides
    @Singleton
    fun provideCreateNewCellUseCase(repository: AppRepository): CreateNewCellUseCase =
        CreateNewCellUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAllCellsUseCase(repository: AppRepository): GetAllCellsUseCase =
        GetAllCellsUseCase(repository)
}