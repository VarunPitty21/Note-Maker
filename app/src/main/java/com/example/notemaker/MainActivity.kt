package com.example.notemaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),INotesAdapter {

    private lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesAdapter(this,this)
        notesRecyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })


    }

    override fun OnItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show()
    }

    fun SubmitData(view: View) {
        val noteText = editTextTextPersonName.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Notes(noteText))
            editTextTextPersonName.setText("")
            Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
        }
    }
}