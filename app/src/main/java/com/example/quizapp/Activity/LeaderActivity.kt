package com.example.quizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.quizapp.Adapter.LeaderAdapter
import com.example.quizapp.Domain.UserModel
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window: Window = this.window
        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)

        val data = loadData()

        // Top 3 Text
        binding.scoreTop1Txt.text = data[0].score.toString()
        binding.scoreTop2Txt.text = data[1].score.toString()
        binding.scoreTop3Txt.text = data[2].score.toString()

        binding.titleTop1Txt.text = data[0].name
        binding.titleTop2Txt.text = data[1].name
        binding.titleTop3Txt.text = data[2].name

        // Top 3 Images
        Glide.with(this).load(getImage(data[0].pic)).into(binding.pic1)
        Glide.with(this).load(getImage(data[1].pic)).into(binding.pic2)
        Glide.with(this).load(getImage(data[2].pic)).into(binding.pic3)

        // Set selected menu item
        binding.bottomNav.selectedItemId = R.id.board

// Handle clicks
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.board -> true

                else -> false
            }
        }


        // Remaining list for RecyclerView
        val listForAdapter = data.drop(3)
        leaderAdapter.differ.submitList(listForAdapter)

        // RecyclerView
        binding.leaderView.layoutManager = LinearLayoutManager(this)
        binding.leaderView.adapter = leaderAdapter
    }

    private fun getImage(name: String): Int {
        return resources.getIdentifier(name, "drawable", packageName)
    }
}

private fun loadData(): MutableList<UserModel> {
    val users = mutableListOf<UserModel>()
    users.add(UserModel(1, "Sophia", "person1", 4850))
    users.add(UserModel(2, "Daniel", "person2", 45460))
    users.add(UserModel(3, "James", "person3", 3873))
    users.add(UserModel(4, "John Smith", "person4", 3250))
    users.add(UserModel(5, "Emily Johnson", "person5", 3015))
    users.add(UserModel(6, "David Brown", "person6", 2970))
    users.add(UserModel(7, "Sarah Wilson", "person7", 2870))
    users.add(UserModel(8, "Michael Davis", "person8", 2670))
    users.add(UserModel(9, "Sarah Wilson", "person9", 2380))
    users.add(UserModel(10, "Sarah Wilson", "person10", 2380))
    return users
}
