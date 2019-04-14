package br.com.adalbertofjr.marvelheroes.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.adalbertofjr.marvelheroes.R
import br.com.adalbertofjr.marvelheroes.characters.injection.CharactersModule
import br.com.adalbertofjr.marvelheroes.detail.CharacterDetailActivity
import br.com.adalbertofjr.marvelheroes.root.App
import kotlinx.android.synthetic.main.fragment_cards.*
import javax.inject.Inject

class CharactersFragment : Fragment(), CharactersContract.View, CardsAdapter.OnCharacterListener {
    private val characters = mutableListOf<CharacterViewModel>()
    private lateinit var gridLayout: GridLayoutManager
    private var isScrolling = false

    //Inject
    val Fragment.app: App get() = requireActivity().application as App
    val component by lazy { app.component.inject(
        CharactersModule(
            this
        )
    ) }

    @Inject
    lateinit var presenter: CharactersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cards, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gridLayout = GridLayoutManager(requireContext(), 3)
        rv_list_cards.setHasFixedSize(true)
        rv_list_cards.layoutManager = gridLayout
        rv_list_cards.adapter = CardsAdapter(requireContext(), this.characters, this)
        rv_list_cards.addOnScrollListener(this.onScrollListener())

        presenter.loadCharacters()
    }

    override fun showLoading(show: Boolean) {
        Log.i("MHAFJR", "showLoading: $show")
        if (show) {
            pb_loading.visibility = View.VISIBLE
            return
        }
        pb_loading.visibility = View.GONE
    }

    override fun showCharacters(characters: List<CharacterViewModel>) {
        this.characters.addAll(characters)
        rv_list_cards.adapter?.notifyDataSetChanged()
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

    /**
     * Endless Scroll
     */
    private fun onScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var currentCharacters = 0
                var totalCharacters = 0
                var scrollOutItems = 0

                with(gridLayout) {
                    currentCharacters = childCount
                    totalCharacters = itemCount
                    scrollOutItems = this.findFirstVisibleItemPosition()
                }

                if (isScrolling && currentCharacters + scrollOutItems == totalCharacters && dy > 0) {
                    isScrolling = false

                    presenter.loadCharacters()
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }
        }
    }
}