package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import kotlinx.android.synthetic.main.item_match.view.*

class MatchesRecyclerAdapter : RecyclerView.Adapter<MatchesRecyclerAdapter.MatchesViewHolder>() {

    var list: List<MatchScheduleModel> = listOf()
    var checkToggleListener = MutableLiveData<MatchScheduleModel>()
    var unCheckToggleListener = MutableLiveData<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view =   LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = lp

        return MatchesViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setFavoriteListeners(
        checkListener: MutableLiveData<MatchScheduleModel>,
        unCheckListener: MutableLiveData<Int>
    ){
        checkToggleListener = checkListener
        unCheckToggleListener = unCheckListener
    }

    inner class MatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        init {
            itemView.favorite_lottieview_itemmatch.setMaxProgress(0.5f)
            itemView.favorite_container_itemmatch.setOnClickListener {
                if (!list[adapterPosition].favorite) {
                    checkToggel()
                }
                else {
                   unCheckToggel()
                }
            }

        }

        fun checkToggel() {
            itemView.favorite_lottieview_itemmatch.speed = 1f
            itemView.favorite_lottieview_itemmatch.playAnimation()
            list[adapterPosition].favorite = true
            checkToggleListener.value = list[adapterPosition]
        }
        fun unCheckToggel(){
            itemView.favorite_lottieview_itemmatch.speed = -2f
            itemView.favorite_lottieview_itemmatch.playAnimation()
            list[adapterPosition].favorite = false
            unCheckToggleListener.value = list[adapterPosition].id
        }

        fun bind(matchScheduleModel: MatchScheduleModel) {


                if (matchScheduleModel.favorite) {
                    itemView.favorite_lottieview_itemmatch.speed = 1f
                    itemView.favorite_lottieview_itemmatch.playAnimation()
                }

            itemView.match_scheduleview_itemmatch.team1Name = matchScheduleModel.homeTeam
            itemView.match_scheduleview_itemmatch.team2Name = matchScheduleModel.awayTeam
            itemView.match_scheduleview_itemmatch.teamsgroup = matchScheduleModel.group


            when(matchScheduleModel.homeScore){
               -1 -> {
                   itemView.match_scheduleview_itemmatch.isMatchPlayed1 = false
                   itemView.match_scheduleview_itemmatch.matchTimeText = matchScheduleModel.time
               }

                else -> {
                    itemView.match_scheduleview_itemmatch.matchResult1 =
                        "${matchScheduleModel.homeScore} - ${matchScheduleModel.awayScore}"
                    itemView.match_scheduleview_itemmatch.isMatchPlayed1 = true
                }
            }
            itemView.match_scheduleview_itemmatch.invalidate()

//            itemView.match_scheduleview_itemmatch.teamsgroup = matchScheduleModel

        }

    }
}
