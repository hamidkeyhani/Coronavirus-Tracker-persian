package com.parassidhu.coronavirusapp

import android.app.Application
import android.graphics.Typeface
import com.facebook.stetho.Stetho
import com.parassidhu.coronavirusapp.di.ApplicationComponent
import com.parassidhu.coronavirusapp.di.ApplicationModule
import com.parassidhu.coronavirusapp.di.DaggerApplicationComponent
import com.parassidhu.coronavirusapp.di.DatabaseModule
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class CoronaApp: Application() {

    lateinit var iranSansFont: Typeface
    lateinit var iranSansFontBold: Typeface

    override fun onCreate() {
        super.onCreate()

        iranSansFont = Typeface.createFromAsset(assets, "fonts/IRANSansMobileFN.ttf")
        iranSansFontBold = Typeface.createFromAsset(assets, "fonts/IRANSansMobileBoldFN.ttf")

        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansMobileFN.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        )
        instance = this

        initDagger()
        initRest()
    }

    private fun initRest() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }

    private fun initDagger() {
        component =
            DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .databaseModule(DatabaseModule(this))
                .build()

        component.inject(this)
    }

    companion object {
        lateinit var instance: CoronaApp
            private set
        lateinit var component: ApplicationComponent
            private set
    }
}