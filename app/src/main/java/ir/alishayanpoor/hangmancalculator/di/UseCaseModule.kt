package ir.alishayanpoor.hangmancalculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.alishayanpoor.hangmancalculator.domain.repo.HangmanRepo
import ir.alishayanpoor.hangmancalculator.domain.use_case.ArithmeticUseCase
import ir.alishayanpoor.hangmancalculator.domain.use_case.HangmanUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun arithmetic() = ArithmeticUseCase()

    @Provides
    fun hangmanUseCase(repo: HangmanRepo) = HangmanUseCase(repo)
}