package br.com.fiap.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.R
import br.com.fiap.model.Game
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        setExtras()

        scoreViewModel = ViewModelProviders.of(this).get(ScoreViewModel::class.java)

        registerObserver()


        btGoalHome.setOnClickListener {
            scoreViewModel.goalHome()
        }

        btAwayGoal.setOnClickListener {
            scoreViewModel.goalAway()
        }

        btRestart.setOnClickListener {
            scoreViewModel.restartGame()
        }

        btFinish.setOnClickListener {
            finish()
        }
    }

    private fun registerObserver() {
        scoreViewModel.goalHome.observe(this, Observer {
            tvGoalHome.text = it.toString()
        })

        scoreViewModel.goalAway.observe(this, Observer {
            tvAwayGoal.text = it.toString()
        })
    }

    private fun setExtras() {

        /*val game = intent.extras?.getParcelable<Game>("game") // as? Game --> Outra forma de fazer

        tvEventName.text = game?.event
        tvHomeName.text = game?.homeTeam
        tvAwayName.text = game?.awayTeam ?: ""*/

        intent.extras?.getParcelable<Game>("game")?.apply {
            tvEventName.text = event
            tvHomeName.text = homeTeam
            tvAwayName.text = awayTeam
        }
    }

}
