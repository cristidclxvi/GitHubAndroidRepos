package com.mready.githubandroidrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mready.githubandroidrepos.fragments.RepositoriesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) fragmentSearchRepos()
    }

    private fun fragmentSearchRepos(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RepositoriesFragment())
            .commit()
    }
}
