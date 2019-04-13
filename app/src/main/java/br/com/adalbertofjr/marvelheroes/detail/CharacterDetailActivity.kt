package br.com.adalbertofjr.marvelheroes.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class CharacterDetailActivity : AppCompatActivity(), CharacterDetailContract.View {
    val character by lazy { intent.getParcelableExtra(EXTRA_CHARACTER) as CharacterViewModel }
    val presenter: CharacterDetailPresenter = CharacterDetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        presenter.loadDataCharacter(character)
    }

    override fun showCharacterData(character: CharacterViewModel) {
        Glide.with(this).load(character.thumbnailLandscape).into(imv_character)
        Glide.with(this).load(character.thumbnail).into(imv_card)
        txv_name.text = character.name
        txv_description.text =
            if (!character.description.isNullOrEmpty()) character.description else "Macacos me mordam Batman!\n\nParece que alguém esqueceu de escrever minha descrição."
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