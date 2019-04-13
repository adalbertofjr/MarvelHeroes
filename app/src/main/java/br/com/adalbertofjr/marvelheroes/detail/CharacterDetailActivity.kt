package br.com.adalbertofjr.marvelheroes.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailContract.View {
    val character by lazy { intent.getParcelableExtra(EXTRA_CHARACTER) as CharacterViewModel }
    val presenter: CharacterDetailPresenter = CharacterDetailPresenter(this)

    override fun showCharacterData(character: CharacterViewModel) {
        txv_nome.text = character.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter.loadDataCharacter(character)
    }

    companion object {
        private val EXTRA_CHARACTER = "extra_character"
        fun open(context: Context, character: CharacterViewModel) {
            context.startActivity(
                Intent(context, CharacterDetailActivity::class.java)
                    .apply { putExtra(EXTRA_CHARACTER, character) })
        }
    }
}