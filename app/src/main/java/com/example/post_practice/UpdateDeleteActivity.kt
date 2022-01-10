package com.example.post_practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UpdateDeleteActivity : AppCompatActivity() {
    private lateinit var enterUserID: EditText
    private lateinit var enterName: EditText
    private lateinit var enterLocation: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    private var userID = 0

    val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

    val intentMain = Intent(this, MainActivity::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_delete)

        enterUserID = findViewById(R.id.etUserID)
        enterName = findViewById(R.id.etName)
        enterLocation = findViewById(R.id.etLocation)

        updateButton = findViewById(R.id.btnUpdate)
        deleteButton = findViewById(R.id.btnDelete)

        updateButton.setOnClickListener { updateUser() }

        deleteButton.setOnClickListener { deleteUser() }
    }

    private fun updateUser() {
        apiInterface?.updateUser(userID, UsersItem(enterName.text.toString(), enterLocation.text.toString(), userID))?.enqueue(object:
            Callback<UsersItem> {
            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                startActivity(intentMain)
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                Log.d("UPDATE", "onResponse: Did not update")
            }
        })
    }

    private fun deleteUser() {
        apiInterface?.deleteUser(userID)?.enqueue(object: Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                startActivity(intentMain)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("DELETE", "onResponse: Did not delete")
            }
        })
    }
}