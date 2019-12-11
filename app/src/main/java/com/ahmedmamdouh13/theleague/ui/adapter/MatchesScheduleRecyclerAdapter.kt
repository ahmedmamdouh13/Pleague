package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
import com.ahmedmamdouh13.theleague.ui.model.LottieAnimationsRaw
import com.ahmedmamdouh13.theleague.ui.model.MatchScheduleModel
import kotlinx.android.synthetic.main.item_matchschedule.view.*



class MatchesScheduleRecyclerAdapter : RecyclerView.Adapter<MatchesScheduleRecyclerAdapter.MatchesScheduleViewHolder>() {

    var list: Map<String,List<MatchScheduleModel>> = hashMapOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesScheduleViewHolder {

     val view =   LayoutInflater.from(parent.context).inflate(R.layout.item_matchschedule, parent, false)
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        view.layoutParams = lp

        return MatchesScheduleViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchesScheduleViewHolder, position: Int) {
        holder.bind(list[list.keys.toList()[position]],list.keys.toList()[position])
    }

    class MatchesScheduleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(
            matchScheduleModel: List<MatchScheduleModel>?,
            date1: String
        ) {
            val arr =  date1.split("-")
           val date = arr[0]+arr[1]+arr[2]
            for (c in date.indices){
                when(c) {
                   0 -> {itemView.number_1_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_1_lottieview_itemmatchschedule.playAnimation()

                   }
                   1 -> {itemView.number_2_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_2_lottieview_itemmatchschedule.playAnimation()

                   }
                   2 -> {itemView.number_3_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_3_lottieview_itemmatchschedule.playAnimation()

                   }
                   3 -> {itemView.number_4_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_4_lottieview_itemmatchschedule.playAnimation()

                   }
                   4 -> {itemView.number_5_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_5_lottieview_itemmatchschedule.playAnimation()

                   }
                   5 -> {itemView.number_6_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_6_lottieview_itemmatchschedule.playAnimation()

                   }
                   6 -> {itemView.number_7_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_7_lottieview_itemmatchschedule.playAnimation()

                   }
                   7 -> {itemView.number_8_lottieview_itemmatchschedule
                        .setAnimation(LottieAnimationsRaw.getRawFile(date[c].toString()))
                       itemView.number_8_lottieview_itemmatchschedule.playAnimation()

                   }
                }
            }
            itemView.matches_recyclerview_item_matchschedule.layoutManager = LinearLayoutManager(itemView.context,LinearLayoutManager.VERTICAL,false)
            itemView.matches_recyclerview_item_matchschedule.itemAnimator = DefaultItemAnimator()
            itemView.matches_recyclerview_item_matchschedule.adapter = MatchesRecyclerAdapter().apply {
                if (matchScheduleModel != null) {
                    list = matchScheduleModel
                    this.notifyDataSetChanged()
                    notifyDataSetChanged()
                }
            }
        }

    }

}
