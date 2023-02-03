package uz.itschool.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_start_screen.*

class StartScreen : AppCompatActivity() {
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
        startActivity(intent)
    }


}