package com.shinjaehun.mvvmshoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shinjaehun.mvvmshoppinglist.data.db.entities.ShoppingItem
import com.shinjaehun.mvvmshoppinglist.databinding.ActivityShoppingBinding
import com.shinjaehun.mvvmshoppinglist.other.ShoppingItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {
    // kodein() 필요 없음!!!
//    override val kodein by kodein()
//    private val factory : ShoppingViewModelFactory

    // 근데 이렇게 살펴보면... 굳이 ShoppingViewModelFactory() 생성할 필요가 없었는데...
    // viewmodelfactory는 뭐 어디에 사용되는거여?
//    private val viewModel: ShoppingViewModel by viewModels()

    // 이것도 일단 adapter에 뭐 다른 기능을 넣을게 아니니까 굳이 context를 넘길 필요가 없을듯
//    private val adapter by lazy {
//        ShoppingItemAdapter(this@ShoppingActivity)
//    }

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
        val viewModel = ViewModelProvider(this).get(ShoppingViewModel::class.java)
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
                    viewModel.upsertItem(item)
                }
            }).show()
        }
    }
}