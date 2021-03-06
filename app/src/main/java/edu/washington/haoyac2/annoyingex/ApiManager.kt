package edu.washington.haoyac2.annoyingex

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class ApiManager(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun fetchMessages(onMessageReady: (List<String>) -> Unit, onError: (() -> Unit)? = null) {
        val messageURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/ex_messages.json"
        val request = StringRequest(
            Request.Method.GET, messageURL,
            {response ->
                val gson = Gson()
                val data = gson.fromJson(response, Messages::class.java)
                onMessageReady(data.messages)
            },
            {
                onError?.invoke()
                Log.i("error", it.toString())
            }
        )

        request.setShouldCache(false)

        queue.add(request)
    }
}