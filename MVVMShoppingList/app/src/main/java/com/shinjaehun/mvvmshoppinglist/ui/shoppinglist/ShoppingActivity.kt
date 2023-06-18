package com.shinjaehun.mvvmshoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shinjaehun.mvvmshoppinglist.R
import com.shinjaehun.mvvmshoppinglist.data.db.ShoppingDatabase
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.data.repositories.ShoppingRepository
import com.shinjaehun.mvvmshoppinglist.databinding.ActivityShoppingBinding
import com.shinjaehun.mvvmshoppinglist.other.ShoppingItemAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()
    private val factory : ShoppingViewModelFactory by instance()

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ㅇㅒ네를 Activity에서 생성하면... Activity를 삭제했을때 얘네 다 사라짐
        // 그래서 kodein을 이용해서 생성하도록 했는데, 이거보다 dagger hilt가 더 나은거라서 최신 버전에서는 dagger를 쓰고있겠지?
        // 나름 혼자 적용해볼려고 했는데 실패, 언젠가는 해봐야겠다!!!
//        val database = ShoppingDatabase(this)
//        val repository = ShoppingRepository(database)
//        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(), viewModel)

//        val rvShoppingItems = findViewById<RecyclerView>(R.id.rvShoppingItems)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

//        val fab = findViewById<FloatingActionButton>(R.id.fab)
        binding.fab.setOnClickListener {
            AddShoppingItemDialog(this, object: AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}