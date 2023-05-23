package com.mitdetroja.servicedemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mitdetroja.servicedemo.databinding.ActivityMainBinding
import java.lang.NumberFormatException

const val NUMBER_OF_ROWS = "numOfRows"

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next.setOnClickListener {

            if(binding.buttonInput.text.isNotEmpty()) {
                try {
                    val numberOfButtons =  binding.buttonInput.text.toString().toInt()
                    startNextActivity(numberOfButtons)
                }catch (e:NumberFormatException){
                    Toast.makeText(this@MainActivity, R.string.enter_valid_number, Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun startNextActivity(numberOfButtons:Int) {
        val intent = Intent(this@MainActivity, RecyclerActivity::class.java)
        intent.putExtra(NUMBER_OF_ROWS, numberOfButtons)
        startActivity(intent)
        finish()
    }
}