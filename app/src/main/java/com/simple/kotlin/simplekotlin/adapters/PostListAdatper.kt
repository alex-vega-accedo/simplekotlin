package com.simple.kotlin.simplekotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.simple.kotlin.simplekotlin.R
import com.simple.kotlin.simplekotlin.model.Post
import kotlinx.android.synthetic.main.item_title.view.*

class PostListAdatper : RecyclerView.Adapter<PostListAdatper.ViewHolder> {

    interface ItemClick{
        fun onClick(position : Int)
    }

    private var postList : List<Post>
    private val listener : ItemClick

    constructor(postList: List<Post>, listener : ItemClick){
        this.postList = postList
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = postList?.get(position)?.title
        holder.titleTextView.setOnClickListener { view -> listener.onClick(position) }
    }

    override fun getItemCount(): Int {
        return postList?.size?: 0
    }

    fun updatePostList(postList : List<Post>){
        this.postList = postList
        notifyDataSetChanged()
    }

    fun getItemValue(position : Int) : String {
        return postList.get(position).body
    }

    class ViewHolder: RecyclerView.ViewHolder {

        lateinit var titleTextView : TextView

        constructor(view : View) : super(view) {
            titleTextView = view.findViewById(R.id.title_text)
        }
    }
}