package com.acePlus.codeTest.di

import android.app.Activity
import com.acePlus.codeTest.base.BaseActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object ActivityContextProvider {

    @Singleton
    @Provides
    fun providerBaseActivity(activity: Activity): BaseActivity {
        check(activity is BaseActivity)
        return activity as BaseActivity
    }
}