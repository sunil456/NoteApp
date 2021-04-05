package com.sunil.noteapp.adapter

import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunil.noteapp.R
import com.sunil.noteapp.entities.Notes
import kotlinx.android.synthetic.main.note_row_layout.view.*

class NotesAdapter() : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    var onItemClickListener:OnItemClickListener? = null
    var notesList = ArrayList<Notes>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesViewHolder {
        return NotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_row_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.itemView.tvTitle.text = notesList[position].title
        holder.itemView.tvDesc.text = notesList[position].noteText
        holder.itemView.tvDateTime.text = notesList[position].dateTime

        if (notesList[position].color != null){
            holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(notesList[position].color))
        }else{
            try {
                holder.itemView.cardView.setCardBackgroundColor(Color.parseColor(R.color.ColorLightBlack.toString()))
            }
            catch (e :IllegalArgumentException ){
                holder.itemView.cardView.setCardBackgroundColor(Color.parseColor("#171C26"));
            }

        }

        if (notesList[position].imgPath != null){
            holder.itemView.imgNote.setImageBitmap(BitmapFactory.decodeFile(notesList[position].imgPath))
            holder.itemView.imgNote.visibility = View.VISIBLE
        }else{
            holder.itemView.imgNote.visibility = View.GONE
        }

        if (notesList[position].webLink != ""){
            holder.itemView.tvWebLink.text = notesList[position].webLink
            holder.itemView.tvWebLink.visibility = View.VISIBLE
        }else{
            holder.itemView.tvWebLink.visibility = View.GONE
        }

        holder.itemView.cardView.setOnClickListener {
            onItemClickListener!!.onClick(notesList[position].id!!)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(noteList: List<Notes>){
        notesList = noteList as ArrayList<Notes>
    }

    fun setOnCLickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view){}

    interface  OnItemClickListener{
        fun onClick(noteId: Int)
    }
}