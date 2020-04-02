package com.parassidhu.coronavirusapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.github.zawadz88.materialpopupmenu.popupMenu
import com.parassidhu.coronavirusapp.R
import com.parassidhu.coronavirusapp.base.BaseActivity
import com.parassidhu.coronavirusapp.ui.overview.OverviewFragment
import com.parassidhu.coronavirusapp.util.SortEnum
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {



    private var activeFragment = Fragment()

    private val overviewFragment by lazy { OverviewFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        setupBottomBar()
        showInitialFragment()
    }

    private fun showInitialFragment() {
        activeFragment = overviewFragment

        supportFragmentManager.commit {
            add(R.id.homeContainer, overviewFragment, OVERVIEW)
        }
    }

    private fun showFabOptions() {
        val popupMenu = popupMenu {
            section {
                item {
                    label = SortEnum.ALPHABETICAL.name
                    callback = {}
                }

                item {
                    label = SortEnum.ASCENDING.name
                    callback = {}
                }

                item {
                    label = SortEnum.DESCENDING.name
                    callback = {}
                }
            }
        }
    }

    private fun setupBottomBar() {
        bottomNavBar.setOnNavigationItemSelectedListener { item: MenuItem ->
            when(item.itemId) {
                R.id.action_overview -> handleOverviewAction()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun handleOverviewAction() {
        when (activeFragment) {
            overviewFragment -> {
                overviewFragment.scrollToTop()
                return
            }
        }
        activeFragment = overviewFragment
    }

    private fun handleIndiaAction() {
        when (activeFragment) {
            overviewFragment -> {
                val frag = supportFragmentManager.findFragmentByTag(INDIA)

                if (frag == null) {
                    supportFragmentManager.commit {
                        hide(activeFragment)
                    }
                } else {
                    supportFragmentManager.commit {
                        hide(activeFragment)
                        show(frag)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (activeFragment == overviewFragment) {
            if (overviewFragment.handleBackPress()) {

            } else {
                finish()
            }
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val OVERVIEW = "overview"
        private const val INDIA = "india"
    }

//    todo : fix bug on calligraphy
//    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
//    }
}