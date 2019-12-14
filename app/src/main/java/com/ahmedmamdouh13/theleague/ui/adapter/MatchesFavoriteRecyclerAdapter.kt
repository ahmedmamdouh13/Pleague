package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import kotlinx.android.synthetic.main.item_match_favorite.view.*

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
    var checkToggleListener = MutableLiveData<MatchScheduleModel>()
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
        checkListener: MutableLiveData<MatchScheduleModel>,
        unCheckListener: MutableLiveData<Int>
    ){
        checkToggleListener = checkListener
        unCheckToggleListener = unCheckListener
    }
    var positionRemoved = 0

    inner class MatchesFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var isChecked = false

        init {

            itemView.favorite_lottieview_itemmatch_favorite.setOnClickListener {
                    if(adapterPosition != -1) {
                        positionRemoved = adapterPosition
                        unCheckToggleListener.value = list[adapterPosition].id
                    }
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

            when {
                matchScheduleModel.days == "Ended" -> {
                    itemView.today_textview_matchfavorite.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.lighterdarkBackground
                        )
                    )
                    itemView.today_textview_matchfavorite.text = itemView.context.getString(R.string.expired_date)
                }
                matchScheduleModel.days == "TODAY" -> {
                    itemView.today_textview_matchfavorite.text = matchScheduleModel.days
                    itemView.today_textview_matchfavorite.setTextColor(ContextCompat.getColor(itemView.context,R.color.colorAccent))

                }
                else -> {
                    itemView.today_textview_matchfavorite.text = "${matchScheduleModel.days} Days"
                    itemView.today_textview_matchfavorite.setTextColor(ContextCompat.getColor(itemView.context,android.R.color.white))
                }
            }


            itemView.match_scheduleview_itemmatch_favorite.team1Name = matchScheduleModel.homeTeam
            itemView.match_scheduleview_itemmatch_favorite.team2Name = matchScheduleModel.awayTeam
            itemView.match_scheduleview_itemmatch_favorite.isLongVersion = false
            itemView.match_scheduleview_itemmatch_favorite.teamsgroup = matchScheduleModel.group
            itemView.date_textview_item_matchfavorite.text = matchScheduleModel.date

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
        }

    }
}