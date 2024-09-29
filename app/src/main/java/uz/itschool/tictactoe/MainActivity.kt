package uz.itschool.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_main.PlayerO
import kotlinx.android.synthetic.main.activity_main.PlayerX
import kotlinx.android.synthetic.main.activity_main.gamescreen
import kotlinx.android.synthetic.main.activity_main.im0
import kotlinx.android.synthetic.main.activity_main.im1
import kotlinx.android.synthetic.main.activity_main.im2
import kotlinx.android.synthetic.main.activity_main.im3
import kotlinx.android.synthetic.main.activity_main.im4
import kotlinx.android.synthetic.main.activity_main.im5
import kotlinx.android.synthetic.main.activity_main.im6
import kotlinx.android.synthetic.main.activity_main.im7
import kotlinx.android.synthetic.main.activity_main.im8
import kotlinx.android.synthetic.main.activity_main.playerImage
import kotlinx.android.synthetic.main.activity_main.redline
import kotlinx.android.synthetic.main.activity_main.replay_button
import kotlinx.android.synthetic.main.activity_main.scoreO
import kotlinx.android.synthetic.main.activity_main.scoreScreen
import kotlinx.android.synthetic.main.activity_main.scoreX
import kotlinx.android.synthetic.main.activity_main.toMainMenu
import kotlinx.android.synthetic.main.activity_main.winnerText


class MainActivity : AppCompatActivity(), OnClickListener {
    private var gameMode = true
    private var x = if (gameMode) R.drawable.x else R.drawable.x2
    private var o = if (gameMode) R.drawable.o else R.drawable.o2

    private var currentPlayer = 1
    private var xScore = 0
    private var oScore = 0

    private var gameAnim = R.anim.xo_animation1

    private lateinit var animation: Animation
    private var matrix = Array(3) { Array(3) { -1 } }

    private lateinit var parent: ConstraintLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parent = findViewById(R.id.parent)

        if (intent.getStringExtra("mode") == "mode2") {
            parent.setBackgroundResource(R.drawable.woodbg)
            gameMode = false
            x = R.drawable.x2
            o = R.drawable.o2
            gameAnim = R.anim.xo_apper_anim

        }

        replay_button.setOnClickListener { reset() }
        toMainMenu.setOnClickListener{ onBackPressed() }


        val name1 = intent.getStringExtra("name1")
        PlayerX.text = if (name1!!.isNotEmpty()) name1 else "Player X"
        val name2 = intent.getStringExtra("name2")
        PlayerO.text = if (name2!!.isNotEmpty()) name2 else "Player O"

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

    private fun reset() {
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.disapperanim)
        scoreScreen.startAnimation(animation)
        gamescreen.visibility = View.VISIBLE
        scoreScreen.visibility = View.GONE
        winnerText.text = ""
        for (i in matrix) {
            i[0] = -1

            i[1] = -1
            i[2] = -1
        }

        redline.setImageDrawable(null)

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

        val xCor = view.tag.toString().toInt() % 3
        val yCor = view.tag.toString().toInt() / 3
        matrix[yCor][xCor] = currentPlayer

        view.isEnabled = false


        var image = x
        if (currentPlayer == 0) image = o
        view.setImageResource(image)

        animation = AnimationUtils.loadAnimation(applicationContext, gameAnim)
        view.startAnimation(animation)

        val check = checkWinner()
        currentPlayer = if (currentPlayer == 1) 0 else 1
        if (!check) setPlayer()
    }

    private fun setPlayer() {
        var player = R.drawable.x_shadowless
        if (gameMode) {
            if (currentPlayer == 0) player = R.drawable.o_shadowless
        } else {
            player = if (currentPlayer == 1) R.drawable.x2 else R.drawable.o2
        }

        playerImage.setImageResource(player)


        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.player_anim)
        playerImage.startAnimation(animation)
    }

    private fun checkWinner(): Boolean {
        val c = currentPlayer
        for (j in matrix.indices) {
            val i = matrix[j]
            if (i[0] == c && i[1] == c && i[2] == c) {
                drawLine(3 + j)
                playerHasWon(c)
                return true
            }
        }
        for (i in 0..2) {
            if (matrix[0][i] == c && matrix[1][i] == c && matrix[2][i] == c) {
                drawLine(i + 1 + 5)
                playerHasWon(c)
                return true
            }
        }
        if (matrix[0][0] == c && matrix[1][1] == c && matrix[2][2] == c) {
            drawLine(1)
            playerHasWon(c)
            return true
        }
        if (matrix[0][2] == c && matrix[1][1] == c && matrix[2][0] == c) {
            drawLine(2)
            playerHasWon(c)
            return true
        }
        var count = 0
        for (i in matrix) {
            for (j in i) {
                if (j == -1) {
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
    private fun playerHasWon(player: Int) {
        if (player == -1) {
            winnerText.text = "DRAW"
            showScoreScreen()
            return
        }

        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.bounce)
        if (player == 0) {
            oScore++
            scoreO.text = oScore.toString()
            scoreO.startAnimation(animation)
        } else {
            xScore++
            scoreX.text = xScore.toString()
            scoreX.startAnimation(animation)
        }
        var t = "Winner is "
        t += if (player == 1) intent.getStringExtra("name1") else intent.getStringExtra("name2")
        winnerText.text = t
        disableImages()

    }

    private fun enableImages() {
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

    private fun disableImages() {
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

    private fun showScoreScreen() {
        gamescreen.visibility = View.GONE
        scoreScreen.visibility = View.VISIBLE
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.apperwithbounceanim)
        scoreScreen.startAnimation(animation)
    }

    private fun drawLine(a: Int) {
        var image = R.drawable.dioganal1
        var anim = R.anim.redlineanimdiog1
        when (a) {
            2 -> {
                image = R.drawable.dioganal2
                anim = R.anim.redlineanimdiog2
            }

            3 -> {
                image = R.drawable.gorizontal1
                anim = R.anim.redlineanimgoriz
            }

            4 -> {
                image = R.drawable.gorizontal2
                anim = R.anim.redlineanimgoriz
            }

            5 -> {
                image = R.drawable.gorizontal3
                anim = R.anim.redlineanimgoriz
            }

            6 -> {
                image = R.drawable.vertikal1
                anim = R.anim.redlineanimver
            }

            7 -> {
                image = R.drawable.vertikal2
                anim = R.anim.redlineanimver
            }

            8 -> {
                image = R.drawable.vertikal3
                anim = R.anim.redlineanimver
            }
        }
        if (!gameMode) {
            when (a) {
                1 -> image = R.drawable.dioganal1_mode
                2 -> image = R.drawable.dioganal2_mode
                3 -> image = R.drawable.gorizontal1_mode
                4 -> image = R.drawable.gorizontal2_mode
                5 -> image = R.drawable.gorizontal3_mode
                6 -> image = R.drawable.vertikal1_mode
                7 -> image = R.drawable.vertikal2_mode
                8 -> image = R.drawable.vertikal3_mode

            }
            anim = gameAnim
        }
        animation = AnimationUtils.loadAnimation(applicationContext, anim)
        redline.setImageResource(image)
        redline.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                showScoreScreen()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })
    }
}