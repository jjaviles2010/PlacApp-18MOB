package br.com.fiap.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.fiap.R
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
        tvEventName.text = intent.extras?.getString("eventName")
        tvHomeName.text = intent.extras?.getString("homeTeam")
        tvAwayName.text = intent.extras?.getString("awayTeam")
    }

}
