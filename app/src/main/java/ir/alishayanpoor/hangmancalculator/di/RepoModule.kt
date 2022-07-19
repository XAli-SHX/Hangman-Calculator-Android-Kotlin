package ir.alishayanpoor.hangmancalculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.alishayanpoor.hangmancalculator.data.repo.HangmanRepoLocal

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {
    @Provides
    fun hangman() = HangmanRepoLocal()
}