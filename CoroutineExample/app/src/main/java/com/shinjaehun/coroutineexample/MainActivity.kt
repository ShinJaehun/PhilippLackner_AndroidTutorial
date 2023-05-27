package com.shinjaehun.coroutineexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.w3c.dom.Text
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.system.measureTimeMillis

data class Person(
    val name: String = "",
    val age: Int = -1
)

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Starting our First Coroutine - Kotlin Coroutines

//        GlobalScope.launch {
//            // coroutine
//            delay(3000L) // main 3초 후에
//            Log.d(TAG, "Hello from global scope thread ${Thread.currentThread().name}")
//            // coroutine이 종료되기 전에 main thread가 종료되면 coroutine도 종료되는 거지 머
//        }
//        Log.d(TAG, "Hello from thread ${Thread.currentThread().name}")
//        // 얘가 main thread

//        Suspend Functions - Kotlin Coroutines

//        delay(1000L) // 얘는 suspendable이기 때문에 coroutine 문맥에서만 쓸 수 있음
//        doNetworkCall() // 얘도 마찬가지

//        GlobalScope.launch {
//            //  delay(1000L)
//            val networkCallResult1 = doNetworkCall1()
//            val networkCallResult2 = doNetworkCall2()
//            Log.d(TAG, "Result1: " + networkCallResult1)
//            Log.d(TAG, "Result2: " + networkCallResult2)
//            // 6초 후에 두 결과가 동시에 출력됨. 왜냐하면 둘다 같은 coroutine에서 실행되고 있기 때문이지!
//        }

//        GlobalScope.launch(Dispatchers.Default) {
//            // Dispatchers에 대해 알아둘 필요가 있음!
//        GlobalScope.launch(newSingleThreadContext("MyThread")) {
//            // my own thread
//        }

//        Coroutine Contexts - Kotlin Coroutines

//        val tvHello = findViewById<TextView>(R.id.tvHello)
//
//        GlobalScope.launch(Dispatchers.IO) {
//            // network call이기 때문에...
//            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
//
//            val answer = doNetworkCall()
//            withContext(Dispatchers.Main) {
//                Log.d(TAG, "Setting text in thread ${Thread.currentThread().name}")
//                tvHello.text = answer
//                // 얘를 Dispatchers.IO에서 실행하면 Exception!
//            }
//        }

//        runBlocking - Kotlin Coroutines

        // 결국 얘랑
//        Log.d(TAG, "Before runBlocking")
//
//        runBlocking {
//            // 이렇게 하면 main thread를 delay시킴
//            Log.d(TAG, "Start of runBlocking")
//            delay(5000L)
//            Log.d(TAG, "End of runBlocking")
//        }
//
//        Log.d(TAG, "After runBlocking")

        // 얘랑 같은 결과
//        Log.d(TAG, "Before runBlocking")
//
//        Log.d(TAG, "Start of runBlocking")
//        Thread.sleep(5000L)
//        Log.d(TAG, "End of runBlocking")
//
//
//        Log.d(TAG, "After runBlocking")

//        Log.d(TAG, "Before runBlocking")
//
//        runBlocking {
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d(TAG, "Finished IO Coroutine1")
//            }
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d(TAG, "Finished IO Coroutine2")
//            }
//            // 얘네 둘은 6초 후에 같이 끝나지 않음(둘 다 다른 thread이기 때문!)
//            // 3초 후에 거의 비슷하게 먼저 끝남
//            // 그리고 이후의 과정을 재개
//            Log.d(TAG, "Start of runBlocking")
//            delay(5000L)
//            Log.d(TAG, "End of runBlocking")
//        }
//
//        Log.d(TAG, "After runBlocking")

//        Jobs, Waiting, Cancelation - Kotlin Coroutines

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5){
//                Log.d(TAG, "coroutine is still working...")
//                delay(1000L)
//            }
//        }

//        job.join() // suspend

//        runBlocking {
//            job.join() // coroutine이 모두 종료되는 것을 기다렸다가 이후 실행
//            Log.d(TAG, "main thread is continuing...")
//        }

//        runBlocking {
//            delay(2000L)
//            job.cancel() // 2초 후에 coroutine 종료?
//            Log.d(TAG, "main thread is continuing...")
//        }

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d(TAG, "Starting long running calculation...")
//            for(i in 30..40) {
//                Log.d(TAG, "Result for i = $i: ${fib(i)}")
//            }
//            Log.d(TAG, "Ending long running calculation...")
//        }

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d(TAG, "Starting long running calculation...")
//            for(i in 30..40) {
//                if(isActive) {
//                    // cancel 여부를 확인하는 장치, 이제 원하는대로 2초 후 계산 중지 가능
//                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
//                }
//            }
//            Log.d(TAG, "Ending long running calculation...")
//        }

//        runBlocking {
//            delay(2000L)
//            job.cancel() // 분명히 cancel()했는데 어째서 coroutine이 진행되는거지?
//            // 현재 coroutine 내 for 문이 너무 바빠서 cancel 여부를 확인할 수 없음?
//            Log.d(TAG, "canceled job!")
//        }

//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d(TAG, "Starting long running calculation...")
//            withTimeout(3000L) {
//                // runBlocking 없이 3초 후 종료
//                for(i in 30..40) {
//                    if(isActive) {
//                        Log.d(TAG, "Result for i = $i: ${fib(i)}")
//                    }
//                }
//            }
//            Log.d(TAG, "Ending long running calculation...")
//        }

//        Async and Await - Kotlin Coroutines

//        GlobalScope.launch(Dispatchers.IO) {
            // 같은 thread에 있기 때문에 6초 후에 동시에 출력
//            val time = measureTimeMillis {
//                val answer1 = networkCall1()
//                val answer2 = networkCall2()
//                Log.d(TAG, "answer1 is $answer1")
//                Log.d(TAG, "answer2 is $answer2")
//            }
//            Log.d(TAG, "Requests took $time ms.") // 당연히 지금은 6초

//            val time = measureTimeMillis {
//                var answer1: String? = null
//                var answer2: String? = null
//                val job1 = launch {
//                    answer1 = networkCall1()
//                }
//                val job2 = launch {
//                    answer2 = networkCall2()
//                }
//                job1.join()
//                job2.join()
//                Log.d(TAG, "answer1 is $answer1")
//                Log.d(TAG, "answer2 is $answer2")
//            }
//            Log.d(TAG, "Requests took $time ms.") // 각각 다른 thread에서 실행되기 때문에 3초, 근데 구현은 엉망!

//            val time = measureTimeMillis {
//                val answer1 = async { networkCall1() }
//                val answer2 = async { networkCall2() }
//                Log.d(TAG, "answer1 is ${answer1.await()}") // answer1이 available할때까지 기다렸다가 결과를 냄
//                Log.d(TAG, "answer2 is ${answer2.await()}")
//            }
//            Log.d(TAG, "Requests took $time ms.") // 3초!
//        }

//        lifecycleScope and viewModelScope - Kotlin Coroutines

//        val btnStartActivity = findViewById<Button>(R.id.btStartActivity)
//        btnStartActivity.setOnClickListener {
//            GlobalScope.launch {
//                while (true) {
//                    delay(1000L)
//                    Log.d(TAG, "Still running...")
//                }
//            }
//            lifecycleScope.launch {
//                while (true) {
//                    delay(1000L)
//                    Log.d(TAG, "Still running...")
//                }
//            }
//            GlobalScope.launch {
//                // 5초 후에 second activity가 시작되지만 still running은 계속됨
//                // globalscope에 선언했기 때문!
//                // 앱이 종료되기 전까지는 계속 될 것이다.
//                delay(5000L)
//                Intent(this@MainActivity, SecondActivity::class.java).also {
//                    startActivity(it)
//                    finish() // lifecyclescope에서는 5초후에 still running이 취소됨(activity가 끝났으니까?)
//                }
//            }
//        }

//        Coroutines with Firebase Firestore - Kotlin Coroutines

//        getUser1 { user1 ->
//            getUser2 { user2 ->
//                getMessages { messages ->
//                    // construct chat object
//                }
//            }
//        }

//        val tvData = findViewById<TextView>(R.id.tvData)
//        val tutorialDocument = FirebaseFirestore.getInstance()
//            .collection("coroutines")
//            .document("tutorial")
//        val peter = Person("Perter", 25)
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(3000L)
//            tutorialDocument.set(peter).await() // untile data successfully set the document
//            val person = tutorialDocument.get().await().toObject(Person::class.java)
//            withContext(Dispatchers.Main) {
//                tvData.text = person.toString()
//            }
//        }

//        Coroutines with Retrofit - Kotlin Coroutines

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)

//        api.getComments().enqueue(object: Callback<List<Comment>> {
//            // enqueue() 이게 효율적이지 않다고 함...
//            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        for(comment in it) {
//                            Log.d(TAG, comment.toString())
//                        }
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
//                Log.e(TAG, "Error: $t")
//            }
//        })

//        GlobalScope.launch(Dispatchers.IO) {
//            // coloutine을 이용해서 훨씬 더 쉽게 작성할 수 있다!
//            val comments = api.getComments().await()
//            for(comment in comments) {
//                Log.d(TAG, comment.toString())
//            }
//        }

//        GlobalScope.launch(Dispatchers.IO) {
//            // response를 이렇게 받아와서 할 수 있음...
//            val response = api.getComments().awaitResponse()
//            if (response.isSuccessful) {
//                for(comment in response.body()!!) {
//                    Log.d(TAG, comment.toString())
//                }
//            }
//        }

        GlobalScope.launch(Dispatchers.IO) {
            // api가 response object를 직접 받아온다면...
            val response = api.getComments()
            if (response.isSuccessful) {
                for(comment in response.body()!!) {
                    Log.d(TAG, comment.toString())
                }
            }
        }

    }

//    suspend fun networkCall1(): String {
//        delay(3000L)
//        return "Answer1"
//    }
//
//    suspend fun networkCall2(): String {
//        delay(3000L)
//        return "Answer2"
//    }
//
//    fun fib(n: Int): Long {
//        return if (n==0) 0
//        else if (n==1) 1
//        else fib(n-1) + fib(n-2)
//    }
//
//    suspend fun doNetworkCall1(): String {
//        delay(3000L)
//        return "Done"
//    }
//
//    suspend fun doNetworkCall2(): String {
//        delay(3000L)
//        return "Done"
//    }
//
//    suspend fun doNetworkCall(): String {
//        delay(3000L)
//        return "Done"
//    }

}