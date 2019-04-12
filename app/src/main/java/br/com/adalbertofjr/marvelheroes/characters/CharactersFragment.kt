package br.com.adalbertofjr.marvelheroes.characters

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment
import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailActivity

class CharactersFragment : ListFragment(), CharactersContract.View {
    private val presenter: CharactersPresenter = CharactersPresenter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadCharacters()
    }

    override fun showLoading(b: Boolean) {
        Log.i("MHAFJR", "showLoading: $b")
    }

    override fun showCharacters() {
        Log.i("MHAFJR", "showCharacters")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_list_item_1, listOf("Wolwerine", "Homem-Aranha", "Ciclope")
        )

        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        val name = l?.getItemAtPosition(position) as String
        Log.i("MHAFJR", "onListItemClick: $name")

        presenter.onClickCharacterDetail(name)
    }

    override fun showCharacterDetail(name: String) {
        Log.i("MHAFJR", "showCharacterDetail: $name")
        CharacterDetailActivity.open(requireContext(), name)
    }

    override fun showMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}