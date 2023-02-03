package uz.itschool.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import kotlinx.android.synthetic.main.activity_start_screen.*

class StartScreen : AppCompatActivity() {
    private var mode = "mode1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)
        startGame.setOnClickListener{
            startGame()
        }
    }

    private fun startGame(){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name1", playerName1.text.toString())
        intent.putExtra("name2", playerName2.text.toString())
        intent.putExtra("mode", mode)
        startActivity(intent)

        mode1.setOnClickListener{
            changeSizes(mode2, mode1)
            mode = "mode1"
        }
        mode2.setOnClickListener{
            changeSizes(mode1, mode2)
            mode = "mode2"
        }
    }

    private fun changeSizes(v1:ImageView, v2:ImageView) {
        v1.layoutParams.height = 200
        v1.layoutParams.width = 200
        v1.requestLayout()

        v2.layoutParams.height = 240
        v2.layoutParams.width = 240
        v2.requestLayout()

    }


}