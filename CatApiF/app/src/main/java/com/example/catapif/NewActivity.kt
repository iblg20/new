package com.example.catapif

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.catapif.api.RetrofitApi
import com.example.catapif.model.Vote
import com.example.catapif.model.VoteResponse
import kotlinx.android.synthetic.main.activity_new.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewActivity : AppCompatActivity() {

    var clickPos : Int = 0
    var clickNeg : Int = 0
    private val msgToastPositive = "You rated this cat as positive"
    private val msgToastNegative = "You rated this cat as negative"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val catImageLarge : ImageView = image_large
        val catIdLarge : TextView = id_large
        val ratePositive = button_rate_positive
        val rateNegative = button_rate_negative

        val bundle : Bundle?= intent.extras
        val catImage = bundle!!.getString("url")
        val catId = bundle!!.getString("id")

        Glide.with(this)
            .load(catImage)
            .centerCrop()
            .error(R.drawable.ic_baseline_pets_24)
            .into(catImageLarge)
        catIdLarge.text = "Image ID : " + catId

        // Rating buttons disabled after 3 attempts
        ratePositive.setOnClickListener {
            Toast.makeText(this, msgToastPositive , Toast.LENGTH_SHORT).show()
            when {
                clickPos < 2 -> clickPos++
                else -> ratePositive.isEnabled = false
            }
            RetrofitApi.service.sendData(Vote(catId, 1)).enqueue(object : Callback<VoteResponse>{
                override fun onResponse(
                    call: Call<VoteResponse>,
                    response: Response<VoteResponse>
                ) {
                    Toast.makeText(this@NewActivity, "YES" , Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                    Toast.makeText(this@NewActivity, "NO" , Toast.LENGTH_SHORT).show()
                }
            })
        }

        rateNegative.setOnClickListener {
            Toast.makeText(this, msgToastNegative , Toast.LENGTH_SHORT).show()
            when {
                clickNeg < 2 -> clickNeg++
                else -> rateNegative.isEnabled = false
            }
            RetrofitApi.service.sendData(Vote(catId, 1)).enqueue(object : Callback<VoteResponse>{
                override fun onResponse(
                    call: Call<VoteResponse>,
                    response: Response<VoteResponse>
                ) {
                    Toast.makeText(this@NewActivity, "YES" , Toast.LENGTH_SHORT).show()
                }
                override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                    Toast.makeText(this@NewActivity, "NO" , Toast.LENGTH_SHORT).show()
                }
            })
        }

        // Back to the 1st activity
        button_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}


