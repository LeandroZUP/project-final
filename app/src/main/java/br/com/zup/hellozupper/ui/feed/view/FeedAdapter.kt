package br.com.zup.hellozupper.ui.feed.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.hellozupper.R
import br.com.zup.hellozupper.data.model.Feed
import br.com.zup.hellozupper.data.model.FeedEntity
import br.com.zup.hellozupper.databinding.FeedItemBinding
import com.squareup.picasso.Picasso

class FeedAdapter(
    private var feedList: MutableList<Feed> = mutableListOf(),
    private val saveReadNews: (news: Feed) -> Unit,
    private val modifyReadNewsToNotRead: (indexNews: FeedEntity) -> Unit
) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemFeed = feedList[position]
        holder.showDataFeed(itemFeed)
        holder.binding.ivReadNews.setOnClickListener {
            if(feedList.size != 0) {
                if(!itemFeed.read) {
                    saveReadNews(itemFeed)
                    feedList.remove(itemFeed)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, feedList.size)
                }else{
                    modifyReadNewsToNotRead(FeedEntity(itemFeed.id))
                    itemFeed.read = false
                    notifyItemChanged(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = feedList.size

    fun updateFeedList(newList: List<Feed>) {
        feedList = newList.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showDataFeed(feed: Feed) {
            binding.tvSenderFeed.text = feed.sender
            binding.tvTitleFeed.text = feed.title
            binding.tvDateFeed.text = feed.date
            binding.tvContentFeed.text = feed.content

            binding.ivReadNews.setImageDrawable(
                ContextCompat.getDrawable(
                    binding.root.context,
                    if (feed.read){
                        R.drawable.ic_bookmark_read
                    }else{
                        R.drawable.ic_bookmark
                    }
                )
            )
        }
    }
}