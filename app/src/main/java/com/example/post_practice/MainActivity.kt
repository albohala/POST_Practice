package com.example.post_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var rvAdapter: RVAdapter

    private lateinit var clMain: ConstraintLayout
    private lateinit var enterName: EditText
    private lateinit var enterLocation: EditText
    private lateinit var addButton: Button

    private lateinit var users: Users
    private lateinit var userItems: UsersItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        users = Users()
        //userItems = UsersItem()

        rvMain = findViewById(R.id.rvMain)
        rvAdapter = RVAdapter(users)
        rvMain.adapter = rvAdapter


        clMain = findViewById(R.id.clMain)
        enterName = findViewById(R.id.etName)
        enterLocation = findViewById(R.id.etLocation)
        addButton = findViewById(R.id.btnAdd)

        addButton.setOnClickListener {
            addData()
            //displayData()
        }

        displayData()
    }

    private fun addData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.postUsers(UsersItem(enterName.text.toString(), enterLocation.text.toString(), 0))?.enqueue(object: Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                Toast.makeText(this@MainActivity, "User added", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                Toast.makeText(this@MainActivity, "User failed to be added", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayData() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getUsers()?.enqueue(object: Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                try {
                    users = response.body()!!
                    rvAdapter.update(users)

//                    val responseBody = response.body()!!
//                    val userName = responseBody[users]
//                    println("user: $users")
                    Log.d("ADD_USER", "onResponse: $users")
                } catch (e: Exception) {
                    Log.d("DISPLAY_DATA", "onResponse: Did not display data")
                    Log.d("EXCEPTION", "onResponse: $e")
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.d("FAILED", "onResponse: Did not display data")
            }

        })
    }
}