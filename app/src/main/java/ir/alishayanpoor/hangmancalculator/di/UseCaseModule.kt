package ir.alishayanpoor.hangmancalculator.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.alishayanpoor.hangmancalculator.domain.use_case.ArithmeticUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {
    @Provides
    fun arithmeticUseCase() = ArithmeticUseCase()
}