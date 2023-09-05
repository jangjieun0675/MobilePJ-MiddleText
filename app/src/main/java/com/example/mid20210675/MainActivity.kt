package com.example.mid20210675

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mid20210675.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar : ActionBarDrawerToggle

    class MyFragmentAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments:List<Fragment>
        init{
            fragments = listOf(OneFragment(),TwoFragment(),ThreeFragment())//스와이프할 순서대로 작성
        }
        override fun getItemCount(): Int {//프래그먼트 갯수 리턴
            return fragments.size
        }
        override fun createFragment(position: Int): Fragment {//프래그먼트를 하나씩 리턴
            return fragments[position]
        }
    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        toolbar = ActionBarDrawerToggle(this,binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.syncState()

        binding.viewpager2.adapter =MyFragmentAdapter(this)

        TabLayoutMediator(binding.tabs,binding.viewpager2){
                tab,position -> tab.text = "TAB ${position+1}"
        }.attach()
        binding.mainDrawer.setNavigationItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 ->{
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ducksung.ac.kr"))
                startActivity(intent)
            }
            R.id.item2 ->{
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:/119"))
                startActivity(intent)
            }
            R.id.item3 ->{
                var intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.6513783, 127.0163402"))
                startActivity(intent)
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actionbar,menu)

        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("mobile","검색어 : $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toolbar.onOptionsItemSelected(item)){
            return true
        }
        when(item.itemId){
            R.id.menu_search->{ Log.d("mobile","menu_search") }
            R.id.menu1->{
                val toast = Toast.makeText(this,"개발 중 입니다.",Toast.LENGTH_SHORT)
                toast.addCallback(object :Toast.Callback(){
                    override fun onToastHidden() {
                        Log.d("mobile","Toast Hidden")
                    }
                    override fun onToastShown() {
                        Log.d("mobile","Toast Shown")

                    }
                })
                toast.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }



}