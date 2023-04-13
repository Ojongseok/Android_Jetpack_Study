package com.example.searchbooksproject.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.searchbooksproject.data.model.Book
import com.example.searchbooksproject.databinding.ItemBookPreviewBinding

class BookSearchPagingAdapter: PagingDataAdapter<Book, BookSearchPagingAdapter.BookSearchViewHolder>(BookDiffCallback) {
    private lateinit var onItemClickListener: OnItemClickListener

    inner class BookSearchViewHolder(private val binding : ItemBookPreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book : Book) {
            val author = book.authors.toString().removeSurrounding("[","]")
            val publisher = book.publisher
            val date = if (book.datetime.isNotEmpty()) book.datetime.substring(0,10) else ""

            itemView.apply {
                binding.ivArticleImage.load(book.thumbnail)
                binding.tvTitle.text = book.title
                binding.tvAuthor.text = "$author ㅣ $publisher"
                binding.tvDatetime.text = date
            }
        }
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        val pageBook = getItem(position)
        pageBook?.let { book ->
            holder.bind(book)

            holder.itemView.setOnClickListener {
                onItemClickListener.onClick(it, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(
            ItemBookPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun getCurrentList(position: Int) : Book {
        return getItem(position)!!
    }

    companion object {
        private val BookDiffCallback = object  : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.isbn == newItem.isbn
            }
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

}