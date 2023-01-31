package uz.itschool.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var im0 : ImageView
    private lateinit var im1 : ImageView
    private lateinit var im2 : ImageView
    private lateinit var im3 : ImageView
    private lateinit var im4 : ImageView
    private lateinit var im5 : ImageView
    private lateinit var im6 : ImageView
    private lateinit var im7 : ImageView
    private lateinit var im8 : ImageView

    private lateinit var reset : Button

    private var x = R.drawable.x
    private var o = R.drawable.o

    private var currentPlayer = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        im0 = findViewById(R.id.im0)
        im1 = findViewById(R.id.im1)
        im2 = findViewById(R.id.im2)
        im3 = findViewById(R.id.im3)
        im4 = findViewById(R.id.im4)
        im5 = findViewById(R.id.im5)
        im6 = findViewById(R.id.im6)
        im7 = findViewById(R.id.im7)
        im8 = findViewById(R.id.im8)

        reset = findViewById(R.id.reset_button)

        reset.setOnClickListener { reset() }

        im0.setOnClickListener(this)
        im1.setOnClickListener(this)
        im2.setOnClickListener(this)
        im3.setOnClickListener(this)
        im4.setOnClickListener(this)
        im5.setOnClickListener(this)
        im6.setOnClickListener(this)
        im7.setOnClickListener(this)
        im8.setOnClickListener(this)


    }

    private fun reset(){
        Log.d("TAG", "reset: ")
        im0.visibility = View.INVISIBLE
        im1.visibility = View.INVISIBLE
        im2.visibility = View.INVISIBLE
        im3.visibility = View.INVISIBLE
        im4.visibility = View.INVISIBLE
        im5.visibility = View.INVISIBLE
        im6.visibility = View.INVISIBLE
        im7.visibility = View.INVISIBLE
        im8.visibility = View.INVISIBLE
    }

    override fun onClick(v: View?) {
        Log.d("TAG", "onClick: ")
        val view = v as ImageView
        if (view.isVisible) return

        var image = x
        if (currentPlayer == 0) image = o
        view.setImageResource(image)

        view.visibility = View.VISIBLE
        currentPlayer = if (currentPlayer == 1) 0 else 1

    }
}