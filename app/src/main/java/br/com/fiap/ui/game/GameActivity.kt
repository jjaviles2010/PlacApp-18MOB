package br.com.fiap.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.R
import br.com.fiap.ui.game.event.EventFragment
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        showEventFragment()

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showEventFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.containerGame, EventFragment())
        ft.commit()
    }
}
