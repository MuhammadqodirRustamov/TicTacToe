package uz.itschool.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

    private lateinit var winnerText : TextView

    private lateinit var reset : Button

    private var x = R.drawable.x
    private var o = R.drawable.o

    private var currentPlayer = 1
    private var x_score = 0
    private var o_score = 0

    private lateinit var scoreX : TextView
    private lateinit var scoreO : TextView

    private lateinit var animation : Animation
    private var matrix = Array(3){Array(3){-1} }


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
        winnerText = findViewById(R.id.winnerText)
        scoreX = findViewById(R.id.scoreX)
        scoreO = findViewById(R.id.scoreO)

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
        winnerText.text = ""
        for (i in matrix){
            i[0] = -1
            i[1] = -1
            i[2] = -1
        }

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


        enableImages()

    }

    override fun onClick(v: View?) {
        winnerText.text = ""

        val view = v as ImageView
        if (!view.isEnabled) return

        val xCor = view.tag.toString().toInt()%3
        val yCor = view.tag.toString().toInt()/3
        matrix[yCor][xCor] = currentPlayer

        view.isEnabled = false


        var image = x
        if (currentPlayer == 0) image = o
        view.setImageResource(image)

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.xo_animation)
        view.startAnimation(animation)

        val check = checkWinner()
        currentPlayer = if (currentPlayer == 1) 0 else 1
        if (!check)setPlayer()
    }

    private fun setPlayer(){
        var player = R.drawable.x_shadowless
        if (currentPlayer == 0) player = R.drawable.o_shadowless
        playerImage.setImageResource(player)


        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.player_anim)
        playerImage.startAnimation(animation)
    }

    private fun checkWinner(): Boolean {
        val c = currentPlayer
        for (i in matrix){
            if (i[0] == c && i[1] == c && i[2] == c){
                playerHasWon(c)
                return true
            }
        }
        for (i in 0..2){
            if (matrix[0][i] == c && matrix[1][i] == c && matrix[2][i] == c){
                playerHasWon(c)
                return true
            }
        }
        if (matrix[0][0] == c && matrix[1][1] == c && matrix[2][2] == c ){
            playerHasWon(c)
            return true
        }
        if (matrix[0][2] == c && matrix[1][1] == c && matrix[2][0] == c ){
            playerHasWon(c)
            return true
        }
        var count = 0
        for (i in matrix){
            for (j in i){
                if (j == -1){
                    count++
                }
            }
        }
        if (count == 0) {
            playerHasWon(-1)
            return true
        }
        return false
    }

    @SuppressLint("SetTextI18n")
    private fun playerHasWon(player:Int){
        if (player == -1){
            winnerText.text = "DRAW"
            return
        }
        if (player == 0){
            o_score++
            scoreO.text = o_score.toString()
        }else{
            x_score++
            scoreX.text = x_score.toString()
        }
        var t = "Winner is "
        t += if (player == 1) "X" else "O"
        winnerText.text = t
        disableImages()

    }
    private fun enableImages(){
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
    private fun disableImages(){
        im0.isEnabled = false
        im1.isEnabled = false
        im2.isEnabled = false
        im3.isEnabled = false
        im4.isEnabled = false
        im5.isEnabled = false
        im6.isEnabled = false
        im7.isEnabled = false
        im8.isEnabled = false
    }
}