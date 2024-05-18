package com.example.a2020102527_main_project_2.di
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.example.a2020102527_main_project_2.core.data.db.RunningAppDB
import com.example.a2020102527_main_project_2.core.data.db.RunningAppDB.Companion.RUNNING_APP_DB_NAME
import com.example.a2020102527_main_project_2.core.tracking.location.DefaultLocationTrackingManager
import com.example.a2020102527_main_project_2.core.tracking.location.LocationTrackingManager
import com.example.a2020102527_main_project_2.core.tracking.location.LocationUtils
import com.example.a2020102527_main_project_2.core.tracking.notification.DefaultNotificationHelper
import com.example.a2020102527_main_project_2.core.tracking.notification.NotificationHelper
import com.example.a2020102527_main_project_2.core.tracking.service.DefaultTrackingServiceManager
import com.example.a2020102527_main_project_2.core.tracking.service.TrackingServiceManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.plus
import javax.inject.Singleton

/*
    The AppModule provides dependency injection configuration for various components:

    - Configures singleton instances of services like the Fused Location Provider and Room database.
    - Sets up data storage with DataStore for user preferences.
    - Binds implementations of location tracking manager, tracking service manager,
      and notification helper interfaces.
    - This module centralizes dependency setup, promoting modularity and testability in the
      application architecture.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {

        private const val USER_PREFERENCES_FILE_NAME = "user_preferences"

        @Singleton
        @Provides
        fun provideFusedLocationProviderClient(
            @ApplicationContext context: Context
        ) = LocationServices
            .getFusedLocationProviderClient(context)

        @Provides
        @Singleton
        fun provideRunningDB(
            @ApplicationContext context: Context
        ): RunningAppDB = Room.databaseBuilder(
            context,
            RunningAppDB::class.java,
            RUNNING_APP_DB_NAME
        ).build()

        @Singleton
        @Provides
        fun provideRunDao(db: RunningAppDB) = db.getRunDao()

        @Provides
        @Singleton
        fun providesPreferenceDataStore(
            @ApplicationContext context: Context,
            @ApplicationScope scope: CoroutineScope,
            @IoDispatcher ioDispatcher: CoroutineDispatcher
        ): DataStore<Preferences> =
            PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES_FILE_NAME) },
                scope = scope.plus(ioDispatcher + SupervisorJob())
            )

        @Singleton
        @Provides
        fun provideLocationTrackingManager(
            @ApplicationContext context: Context,
            fusedLocationProviderClient: FusedLocationProviderClient,
        ): LocationTrackingManager {
            return DefaultLocationTrackingManager(
                fusedLocationProviderClient = fusedLocationProviderClient,
                context = context,
                locationRequest = LocationUtils.locationRequestBuilder.build()
            )
        }

    }

    @Binds
    @Singleton
    abstract fun provideTrackingServiceManager(
        trackingServiceManager: DefaultTrackingServiceManager
    ): TrackingServiceManager

    @Binds
    @Singleton
    abstract fun provideNotificationHelper(
        notificationHelper: DefaultNotificationHelper
    ): NotificationHelper


}