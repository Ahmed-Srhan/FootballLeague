package com.srhan.footballleague.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.srhan.footballleague.databinding.CompetitionItemBinding
import com.srhan.footballleague.domain.model.CompetitionDetailsModel

class CompetitionAdapter : RecyclerView.Adapter<CompetitionAdapter.CompetitionVIewHolder>() {

    //var onCompetitionClick: ((CompetitionDetailsModel) -> Unit)? = null
    var onCompetitionClick: OnCompetitionClickListener? = null
    val differ = AsyncListDiffer(this, ItemDiffCallback())

    fun setOnCompetitionClickListener(listener: OnCompetitionClickListener) {
        onCompetitionClick = listener
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<CompetitionDetailsModel>() {
        override fun areItemsTheSame(
            oldItem: CompetitionDetailsModel,
            newItem: CompetitionDetailsModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CompetitionDetailsModel,
            newItem: CompetitionDetailsModel
        ): Boolean {
            return oldItem == newItem
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionVIewHolder {
        return CompetitionVIewHolder(
            CompetitionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: CompetitionVIewHolder, position: Int) {
        val competition = differ.currentList[position]
        holder.bind(competition)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    inner class CompetitionVIewHolder(private val itemBinding: CompetitionItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(competition: CompetitionDetailsModel) {
            itemBinding.competitionDetails = competition
            itemBinding.clickListener = onCompetitionClick
            itemBinding.executePendingBindings()

        }

    }

    interface OnCompetitionClickListener {
        fun onCompetitionClick(competition: CompetitionDetailsModel)
    }
}