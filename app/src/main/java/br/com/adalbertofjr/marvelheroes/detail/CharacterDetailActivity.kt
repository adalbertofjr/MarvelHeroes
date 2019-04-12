package br.com.adalbertofjr.marvelheroes.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.adalbertofjr.marvelheroes.R
import kotlinx.android.synthetic.main.activity_detail.*

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailContract.View {
    val character: String by lazy { intent.getStringExtra(EXTRA_CHARACTER) }
    val presenter: CharacterDetailPresenter = CharacterDetailPresenter(this)

    override fun showCharacterData(name: String) {
        txv_nome.text = name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.loadDataCharacter(character)
    }

    companion object {
        private val EXTRA_CHARACTER = "extra_character"
        fun open(context: Context, name: String) {
            context.startActivity(
                Intent(context, CharacterDetailActivity::class.java)
                    .apply { putExtra(EXTRA_CHARACTER, name) })
        }
    }
}