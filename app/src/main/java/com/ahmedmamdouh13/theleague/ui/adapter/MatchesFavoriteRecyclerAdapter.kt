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
import kotlin.math.roundToInt

class MatchesFavoriteRecyclerAdapter : RecyclerView.Adapter<MatchesFavoriteRecyclerAdapter.MatchesFavoriteViewHolder>() {

    var list: List<MatchScheduleModel> = listOf()
    set(value) {
        val list = field
        field = value

        if (value.size > list.size){
                notifyDataSetChanged()
        }else if (value.size < list.size){

            notifyItemRemoved(positionRemoved)
        }

    }
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
    var positionRemoved = 0

    inner class MatchesFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var isChecked = false

        init {

            itemView.favorite_container_itemmatch_favorite.setOnClickListener {
//                if (!isChecked) {
//
////                    itemView.favorite_lottieview_itemmatch.speed = 1f
////                    itemView.favorite_lottieview_itemmatch.playAnimation()
//                    isChecked = true
//                    checkToggleListener.value = list[adapterPosition].id
//                }
//                else {
////                    itemView.favorite_lottieview_itemmatch.speed = -2f
////                    itemView.favorite_lottieview_itemmatch.playAnimation()
                    if(adapterPosition != -1) {
                        positionRemoved = adapterPosition
                        unCheckToggleListener.value = list[adapterPosition].id
                    }
//                }
            }
        }

        fun bind(matchScheduleModel: MatchScheduleModel) {

            if (matchScheduleModel.favorite) {
                itemView.favorite_lottieview_itemmatch_favorite.visibility = View.VISIBLE
                isChecked = true
            }
            else {
                itemView.favorite_lottieview_itemmatch_favorite.visibility = View.INVISIBLE
                isChecked = false
            }

//            itemView.away_team.text = matchScheduleModel.awayTeam
//            itemView.home_team.text = matchScheduleModel.homeTeam


            itemView.match_scheduleview_itemmatch_favorite.team1Name = matchScheduleModel.homeTeam
            itemView.match_scheduleview_itemmatch_favorite.team2Name = matchScheduleModel.awayTeam
            itemView.match_scheduleview_itemmatch_favorite.teamsgroup = matchScheduleModel.group

            when(matchScheduleModel.homeScore){
                -1 -> {
                    itemView.match_scheduleview_itemmatch_favorite.isMatchPlayed1 = false
                    itemView.match_scheduleview_itemmatch_favorite.matchTimeText = matchScheduleModel.time
                }

                else -> {
                    itemView.match_scheduleview_itemmatch_favorite.matchResult1 =
                        "${matchScheduleModel.homeScore} - ${matchScheduleModel.awayScore}"
                    itemView.match_scheduleview_itemmatch_favorite.isMatchPlayed1 = true
                }
            }
            itemView.match_scheduleview_itemmatch_favorite.invalidate()

//            itemView.match_scheduleview_itemmatch.teamsgroup = matchScheduleModel

        }

    }
}