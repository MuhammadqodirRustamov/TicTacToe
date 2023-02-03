package uz.itschool.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import kotlinx.android.synthetic.main.activity_start_screen.*

class StartScreen : AppCompatActivity() {
    private var mode = "mode1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        mode1.layoutParams.height = (mode1.layoutParams.height * 1.5).toInt()
        mode1.layoutParams.width = (mode1.layoutParams.width * 1.5).toInt()
        mode1.alpha = 1f
        mode2.alpha = 0.6f

        startGame.setOnClickListener{
            startGame()
        }
        mode1.setOnClickListener{
            if (mode != "mode1") changeSizes(mode2, mode1)
            mode = "mode1"
        }
        mode2.setOnClickListener{
            if (mode != "mode2")  changeSizes(mode1, mode2)
            mode = "mode2"
        }
    }

    private fun startGame(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name1", playerName1.text.toString())
        intent.putExtra("name2", playerName2.text.toString())
        intent.putExtra("mode", mode)
        startActivity(intent)


    }

    private fun changeSizes(v1:ImageView, v2:ImageView) {
//
//        var animation = AnimationUtils.loadAnimation(applicationContext, R.anim.getbigger)
//        v2.startAnimation(animation)
//
//
//        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.getsmaller)
//        v1.startAnimation(animation)



        v1.layoutParams.height = (v1.layoutParams.height * 2 /3)
        v1.layoutParams.width = (v1.layoutParams.width * 2/3)
        v1.alpha = 0.6f
        v1.requestLayout()

        v2.layoutParams.height = (v2.layoutParams.height * 1.5).toInt()
        v2.layoutParams.width = (v2.layoutParams.width * 1.5).toInt()
        v2.alpha = 1f
        v2.requestLayout()

    }


}