package br.com.adalbertofjr.marvelheroes.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.adalbertofjr.marvelheroes.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_cards_item.view.*

class CardsAdapter(val context: Context, val characters: List<CharacterViewModel>, val listener: OnCharacterListener) :
    RecyclerView.Adapter<CardsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_cards_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters.get(position)
        holder.txtName.text = character.name
        Glide.with(context).load(character.thumbnail).into(holder.imvCharacter)
        holder.itemView.setOnClickListener { v ->
            listener.onCharacterSelected(character)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.txv_name
        val imvCharacter: ImageView = itemView.imv_thumbnail
    }

    interface OnCharacterListener {
        fun onCharacterSelected(character: CharacterViewModel)
    }
}
