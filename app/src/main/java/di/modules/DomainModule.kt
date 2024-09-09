package di.modules

import dagger.Binds
import dagger.Module
import data.repository.FakeShopApiRepositoryImpl
import domain.repository.FakeShopApiRepository
@Module
interface DomainModule {
    @Binds
    fun bindRepository(repositoryImpl: FakeShopApiRepositoryImpl): FakeShopApiRepository
}