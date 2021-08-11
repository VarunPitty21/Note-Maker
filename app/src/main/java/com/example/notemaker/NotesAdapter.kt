package com.example.notemaker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Context, private val listner: MainActivity): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Notes>()


    class NotesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val text: TextView = itemView.findViewById(R.id.text)
        val delete: ImageView  = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent,false)
        val viewHolder = NotesViewHolder(view)
        viewHolder.delete.setOnClickListener{
            listner.OnItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val current = allNotes[position]
        holder.text.text = current.text

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList : List<Notes>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun OnItemClicked(notes: Notes)
}