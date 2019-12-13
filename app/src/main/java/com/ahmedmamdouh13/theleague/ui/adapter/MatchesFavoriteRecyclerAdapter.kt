package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import kotlinx.android.synthetic.main.item_match.view.*
import kotlinx.android.synthetic.main.item_match.view.favorite_container_itemmatch
import kotlinx.android.synthetic.main.item_match.view.favorite_lottieview_itemmatch
import kotlinx.android.synthetic.main.item_match_favorite.view.*

class MatchesFavoriteRecyclerAdapter : RecyclerView.Adapter<MatchesFavoriteRecyclerAdapter.MatchesFavoriteViewHolder>() {

    var list: List<MatchScheduleModel> = listOf()
    var checkToggleListener = MutableLiveData<Int>()
    var unCheckToggleListener = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesFavoriteViewHolder {
        val view =   LayoutInflater.from(parent.context).inflate(R.layout.item_match_favorite, parent, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = lp

        return MatchesFavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchesFavoriteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setFavoriteListeners(
        checkListener: MutableLiveData<Int>,
        unCheckListener: MutableLiveData<Int>
    ){
        checkToggleListener = checkListener
        unCheckToggleListener = unCheckListener
    }

    inner class MatchesFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var isChecked = false

        init {
            itemView.favorite_lottieview_itemmatch.playAnimation()
            itemView.favorite_container_itemmatch.setOnClickListener {
                if (!isChecked) {
                    itemView.favorite_lottieview_itemmatch.visibility = View.VISIBLE
                    itemView.favorite_lottieview_itemmatch.playAnimation()
                    checkToggleListener.value = list[adapterPosition].id
                    isChecked = true
                }
                else {
                    itemView.favorite_lottieview_itemmatch.visibility = View.INVISIBLE
                    unCheckToggleListener.value = list[adapterPosition].id
                    isChecked = false
                }
            }
        }

        fun bind(matchScheduleModel: MatchScheduleModel) {

            if (matchScheduleModel.favorite) {
                itemView.favorite_lottieview_itemmatch.visibility = View.VISIBLE
                isChecked = true
            }
            else {
                itemView.favorite_lottieview_itemmatch.visibility = View.INVISIBLE
                isChecked = false
            }

            itemView.away_team.text = matchScheduleModel.awayTeam
            itemView.home_team.text = matchScheduleModel.homeTeam

            when(matchScheduleModel.homeScore){
               -1 -> {
                   itemView.match_result.text = matchScheduleModel.time
               }

                else -> {
                    itemView.match_result.text = "${matchScheduleModel.awayScore} - ${matchScheduleModel.homeScore}"
                }
            }

//            itemView.match_scheduleview_itemmatch.teamsgroup = matchScheduleModel

        }

    }
}