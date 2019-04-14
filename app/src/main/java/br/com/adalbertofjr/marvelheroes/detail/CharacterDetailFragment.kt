package br.com.adalbertofjr.marvelheroes.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.characters.CharacterViewModel
import br.com.adalbertofjr.marvelheroes.detail.injection.CharacterDetailModule
import br.com.adalbertofjr.marvelheroes.root.App
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class CharacterDetailFragment : Fragment(), CharacterDetailContract.View {
    //Inject
    val Fragment.app: App get() = requireActivity().application as App
    val component by lazy {
        app.component.inject(
            CharacterDetailModule(
                this
            )
        )
    }

    @Inject
    lateinit var presenter: CharacterDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val character = arguments?.getParcelable<CharacterViewModel>(EXTRA_CHARACTER)
        character?.let { presenter.loadDataCharacter(it) }
    }

    override fun showCharacterData(character: CharacterViewModel) {
        Glide.with(this).load(character.thumbnailLandscape).into(imv_character)
        Glide.with(this).load(character.thumbnail).into(imv_card)
        txv_name.text = character.name
        txv_description.text =
            if (!character.description.isNullOrEmpty()) character.description else "Macacos me mordam Batman!\n\nParece que alguém esqueceu de escrever minha descrição."
    }

    companion object {
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_CHARACTER = "extra_character_detail"

        fun newInstance(character: CharacterViewModel) = CharacterDetailFragment().apply {
            arguments = Bundle().apply { putParcelable(EXTRA_CHARACTER, character) }
        }
    }
}