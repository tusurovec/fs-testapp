package com.example.fsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		var rf = Retrofit.Builder()
			.baseUrl(RetrofitInterface.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create()).build()

		var API = rf.create(RetrofitInterface::class.java)
		var call = API.posts

		call?.enqueue(object : Callback<List<PostModel?>?> {
			override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {

			}

			override fun onResponse(
				call: Call<List<PostModel?>?>,
				response: Response<List<PostModel?>?>
			) {
				var postlist: List<PostModel>? = response.body() as List<PostModel>
				var post = arrayOfNulls<String>(postlist!!.size)

				for(i in postlist!!.indices)
					post[i] = postlist!![i]!!.currentValue

				var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_dropdown_item_1line,post)
				list_view.adapter = adapter
			}
		})
	}
}