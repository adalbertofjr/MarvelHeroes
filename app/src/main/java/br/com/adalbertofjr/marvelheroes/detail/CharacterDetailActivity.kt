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

class CharacterDetailActivity : AppCompatActivity() {
    private val character by lazy { intent.getParcelableExtra(EXTRA_CHARACTER) as CharacterViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        showHotelDetailsFragment()
    }

    private fun showHotelDetailsFragment() {
        val fragment = CharacterDetailFragment.newInstance(character)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.details, fragment, CharacterDetailFragment.TAG_DETAILS)
            .commit()
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