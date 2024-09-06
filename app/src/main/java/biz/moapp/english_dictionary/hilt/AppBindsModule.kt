package biz.moapp.english_dictionary.hilt

import biz.moapp.english_dictionary.network.OpenAiDataSource
import biz.moapp.english_dictionary.network.RetrofitOpenAiNetwork
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

}

@Module
@InstallIn(SingletonComponent::class)
class AppProvideModule {

    @Provides
    @Singleton // シングルトンとして提供
    fun provideRetrofitOpenAiNetwork(moshi: Moshi): OpenAiDataSource {
        return RetrofitOpenAiNetwork(moshi)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideIoDispatchers(): CoroutineDispatcher = Dispatchers.IO
}