package uz.itschool.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


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
    private lateinit var playerImage : ImageView

    private lateinit var reset : Button

    private var x = R.drawable.x
    private var o = R.drawable.o

    private var currentPlayer = 1

    private lateinit var animation : Animation

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
        playerImage = findViewById(R.id.player_image)

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
//        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.xo_animation_reverse)
//
//        im0.startAnimation(animation)
//        im1.startAnimation(animation)
//        im2.startAnimation(animation)
//        im3.startAnimation(animation)
//        im4.startAnimation(animation)
//        im5.startAnimation(animation)
//        im6.startAnimation(animation)
//        im7.startAnimation(animation)
//        im8.startAnimation(animation)

        currentPlayer = 1
        setPlayer()

        im0.setImageDrawable(null)
        im1.setImageDrawable(null)
        im2.setImageDrawable(null)
        im3.setImageDrawable(null)
        im4.setImageDrawable(null)
        im5.setImageDrawable(null)
        im6.setImageDrawable(null)
        im7.setImageDrawable(null)
        im8.setImageDrawable(null)


        im0.isEnabled = true
        im1.isEnabled = true
        im2.isEnabled = true
        im3.isEnabled = true
        im4.isEnabled = true
        im5.isEnabled = true
        im6.isEnabled = true
        im7.isEnabled = true
        im8.isEnabled = true

    }

    override fun onClick(v: View?) {
        val view = v as ImageView
        if (!view.isEnabled) return
        view.isEnabled = false


        var image = x
        if (currentPlayer == 0) image = o
        view.setImageResource(image)

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.xo_animation)
        view.startAnimation(animation)


        currentPlayer = if (currentPlayer == 1) 0 else 1
        setPlayer()

    }

    private fun setPlayer(){
        var player = R.drawable.x_shadowless
        if (currentPlayer == 0) player = R.drawable.o_shadowless
        playerImage.setImageResource(player)


        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.player_anim)
        playerImage.startAnimation(animation)
    }
}