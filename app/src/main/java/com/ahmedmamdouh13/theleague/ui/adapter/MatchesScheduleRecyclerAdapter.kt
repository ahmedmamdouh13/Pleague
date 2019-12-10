package com.ahmedmamdouh13.theleague.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmedmamdouh13.theleague.R
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
            date: String
        ) {
            itemView.date_textview_item_matchschedule.text = date
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
