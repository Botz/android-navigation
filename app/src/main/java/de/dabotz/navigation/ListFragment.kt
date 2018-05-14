package de.dabotz.navigation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_view.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = ItemListAdapter {
            val action = ListFragmentDirections.detail()
            action.setItemId(it)
            findNavController().navigate(action)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}

class ItemListAdapter(val clickListener: (Int) -> Unit): RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount() = 8

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener { clickListener(position) }
    }

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            itemView.item_title.text = "Item $position"
        }
    }
}
