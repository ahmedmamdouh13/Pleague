package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import kotlinx.android.synthetic.main.item_match.view.*
import kotlinx.android.synthetic.main.item_matchschedule.view.*

class MatchesRecyclerAdapter : RecyclerView.Adapter<MatchesRecyclerAdapter.MatchesViewHolder>() {

    var list: List<MatchScheduleModel> = listOf()

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

    class MatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.favorite_lottieview_itemmatch.setOnClickListener {
                itemView.favorite_lottieview_itemmatch.playAnimation()
            }
        }

        fun bind(matchScheduleModel: MatchScheduleModel) {

            itemView.match_scheduleview_itemmatch.team1Name = matchScheduleModel.homeTeam
            itemView.match_scheduleview_itemmatch.team2Name = matchScheduleModel.awayTeam
            itemView.match_scheduleview_itemmatch.matchResult1 =
                "${matchScheduleModel.homeScore} - ${matchScheduleModel.awayScore}"
            itemView.match_scheduleview_itemmatch.invalidate()

//            itemView.match_scheduleview_itemmatch.teamsgroup = matchScheduleModel

        }

    }
}