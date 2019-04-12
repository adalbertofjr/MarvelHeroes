package br.com.adalbertofjr.marvelheroes.characters

import android.R
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment

class CharactersFragment : ListFragment(), CharactersContract.View {
    private val presenter: CharactersPresenter = CharactersPresenter(this)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.loadCharacters()
    }

    override fun showLoading(b: Boolean) {
        Log.i("MHAFJR", "showLoading:$b")
    }

    override fun showCharacters() {
        Log.i("MHAFJR", "showCharacters")
        val adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_list_item_1, listOf("Wolwerine", "Homem-Aranha", "Ciclope")
        )

        listAdapter = adapter
    }

    override fun showCharacterDetail() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}