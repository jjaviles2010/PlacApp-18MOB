package br.com.fiap.ui.game

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import br.com.fiap.R
import br.com.fiap.model.Game
import br.com.fiap.ui.game.awayteam.AwayTeamFragment
import br.com.fiap.ui.game.event.EventFragment
import br.com.fiap.ui.game.hometeam.HomeTeamFragment
import br.com.fiap.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    private lateinit var gameViewModel : GameViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        showEventFragment()

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        registerBroadcastReceiver()

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun registerBroadcastReceiver() {
        val intentFilter = IntentFilter("FILTER_EVENT")
        intentFilter.addAction("FILTER_HOME_TEAM")
        intentFilter.addAction("FILTER_AWAY_TEAM")
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, intentFilter)
    }

    public override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver)
    }


    private fun showEventFragment() {
        val ft = supportFragmentManager.beginTransaction()
        if(supportFragmentManager.findFragmentByTag("EventFragment") == null) {
            ft.add(R.id.containerGame, EventFragment(), "EventFragment")
            ft.commit()
        }
    }


    private val mMessageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.hasExtra("event_name")) {
                gameViewModel.eventName = intent.getStringExtra("event_name")
                showHomeTeamFragment()

            }

            if (intent.hasExtra("home_team")) {
                gameViewModel.homeTeam = intent.getStringExtra("home_team")
                showAwayTeamFragment()

            }

            if (intent.hasExtra("away_team")) {
                gameViewModel.awayTeam = intent.getStringExtra("away_team")
                showScoreActivity()
            }
        }
    }

    private fun showHomeTeamFragment() {
        nextFragment(HomeTeamFragment())
    }

    private fun showAwayTeamFragment() {
        nextFragment(AwayTeamFragment())
    }

    private fun nextFragment(fragment: Fragment) {
        val ft = supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        ft?.replace(R.id.containerGame, fragment)
        ft?.addToBackStack(null)
        ft?.commit()
    }

    private fun showScoreActivity() {

        val game = Game(gameViewModel.eventName, gameViewModel.homeTeam, gameViewModel.awayTeam)

        val nextScreen = Intent(this@GameActivity, ScoreActivity::class.java)
        nextScreen.putExtra("game", game)
        startActivity(nextScreen)

        finish()
    }


}
