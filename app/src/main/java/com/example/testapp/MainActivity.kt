package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.testapp.base.OnProgressListener
import com.example.testapp.base.setVisibility
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.userRepos.UserReposFragment

class MainActivity : AppCompatActivity(), OnProgressListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            updateActiveFragment(UserReposFragment.newInstance())
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    override fun showProgress(isVisible: Boolean) {
        binding.viewProgress.root.setVisibility(isVisible)
    }

    fun updateActiveFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment).addToBackStack(null)
            .commit()
    }


}