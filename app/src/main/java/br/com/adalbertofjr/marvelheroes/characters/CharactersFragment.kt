package br.com.adalbertofjr.marvelheroes.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailActivity
import br.com.adalbertofjr.marvelheroes.repository.Repository
import kotlinx.android.synthetic.main.fragment_cards.*

class CharactersFragment : Fragment(), CharactersContract.View, CardsAdapter.OnCharacterListener {

    private val presenter: CharactersPresenter = CharactersPresenter(this, Repository)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadCharacters()
    }

    override fun showLoading(b: Boolean) {
        Log.i("MHAFJR", "showLoading: $b")
    }

    override fun showCharacters(characters: List<CharacterViewModel>) {
        val gridLayout = GridLayoutManager(requireContext(), 3)
        rv_list_cards.setHasFixedSize(true)
        rv_list_cards.layoutManager = gridLayout
        rv_list_cards.adapter = CardsAdapter(requireContext(), characters, this)
    }

    override fun onCharacterSelected(character: CharacterViewModel) {
        presenter.onClickCharacterDetail(character)
    }

    override fun showCharacterDetail(character: CharacterViewModel) {
        Log.i("MHAFJR", "showCharacterDetail: ${character.name}")
        CharacterDetailActivity.open(requireContext(), character)
    }

    override fun showMessage(message: String) {
        Log.i("MHAFJR", "showMessage: ${message}")
    }
}