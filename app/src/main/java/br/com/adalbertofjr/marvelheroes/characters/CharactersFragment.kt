package br.com.adalbertofjr.marvelheroes.characters

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailActivity
import br.com.adalbertofjr.marvelheroes.repository.Repository

class CharactersFragment : ListFragment(), CharactersContract.View {
    private val presenter: CharactersPresenter = CharactersPresenter(this, Repository())

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadCharacters()
    }

    override fun showLoading(b: Boolean) {
        Log.i("MHAFJR", "showLoading: $b")
    }

    override fun showCharacters(characters: List<CharacterViewModel>) {
        Log.i("MHAFJR", "showCharacters")
        val adapter = ArrayAdapter<CharacterViewModel>(
            requireContext(),
            R.layout.simple_list_item_1, characters
        )

        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val character = l?.getItemAtPosition(position) as CharacterViewModel
        Log.i("MHAFJR", "onListItemClick: ${character.name}")

        presenter.onClickCharacterDetail(character)
    }

    override fun showCharacterDetail(character: CharacterViewModel) {
        Log.i("MHAFJR", "showCharacterDetail: ${character.name}")
        CharacterDetailActivity.open(requireContext(), character)
    }

    override fun showMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}